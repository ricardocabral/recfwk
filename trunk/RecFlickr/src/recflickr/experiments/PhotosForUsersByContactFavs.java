package recflickr.experiments;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.log4j.Logger;

import recfwk.engine.BaseConfig;
import recfwk.engine.SetRetrievalEvaluator;
import recfwk.engine.ExperimentRecorder;
import recfwk.io.CSVItemTupleReader;
import recfwk.model.Recommendation;
import recfwk.model.RecommendedItem;
import recfwk.model.Recommender;
import recfwk.util.RandomUtil;
import recfwk.util.StopWatch;

public class PhotosForUsersByContactFavs extends BaseFlickrExperiment {

	private static Random random = new Random();

	private static final Logger log = Logger
			.getLogger(PhotosForUsersByContactFavs.class);

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(final String[] args) throws FileNotFoundException {

		List<String[]> useruser = new CSVItemTupleReader(",",
				BaseConfig.dataDir + "user-user.csv").readAll();
		List<String[]> userfav = new CSVItemTupleReader(",", BaseConfig.dataDir
				+ "user-favorites.csv").readAll();

		ExperimentRecorder erContactFavs = new ExperimentRecorder(
				"PhotosForUsersByContactFavs");

		ExperimentRecorder erPopPhotos = new ExperimentRecorder(
				"PhotosForUsersByPopularPhotos");

		final ArrayList<String> popularPhotos = new ArrayList<String>();

		final HashMap<String, List<String>> trainUU = new HashMap<String, List<String>>();

		for (int i = 0; i < useruser.size(); i++) {
			if (useruser.get(i).length != 2) {
				continue;
			}
			List<String> tmp = trainUU.get(useruser.get(i)[0]);

			if (tmp == null) {
				tmp = new ArrayList<String>();
				trainUU.put(useruser.get(i)[0], tmp);
			}

			tmp.add(useruser.get(i)[1]);
		}
		HashMap<String, Integer> photoPopularity = new HashMap<String, Integer>();
		boolean initedPop = false;
		while (true) {
			log.info("Experiment started.");

			float testRatio = RandomUtil.randomFloat(0.2, 0.8);

			final HashMap<String, List<String>> testUF = new HashMap<String, List<String>>();
			final HashMap<String, List<String>> trainUF = new HashMap<String, List<String>>();

			for (int i = 0; i < userfav.size(); i++) {
				if (userfav.get(i).length != 2) {
					continue;
				}
				if (!initedPop) {
					if (photoPopularity.get(userfav.get(i)[1]) == null) {
						photoPopularity.put(userfav.get(i)[1], 0);
					}
					photoPopularity.put(userfav.get(i)[1], photoPopularity
							.get(userfav.get(i)[1]) + 1);
				}
				List<String> tmpTestUF = testUF.get(userfav.get(i)[0]);
				List<String> tmpTrainUF = trainUF.get(userfav.get(i)[0]);

				if (tmpTestUF == null) {
					tmpTestUF = new ArrayList<String>();
					testUF.put(userfav.get(i)[0], tmpTestUF);
				}

				if (tmpTrainUF == null) {
					tmpTrainUF = new ArrayList<String>();
					trainUF.put(userfav.get(i)[0], tmpTrainUF);
				}

				if (random.nextFloat() < testRatio) {
					tmpTestUF.add(userfav.get(i)[1]);
				} else {
					tmpTrainUF.add(userfav.get(i)[1]);
				}
			}

			if (!initedPop) {
				initedPop = true;

				DualHashBidiMap bidiPhotopop = new DualHashBidiMap(
						photoPopularity);

				Object[] vals = photoPopularity.values().toArray();
				Arrays.sort(vals);

				for (int i = vals.length - 1; i >= 0; i--) {
					popularPhotos.add((String) bidiPhotopop.getKey(vals[i]));
				}
				log.info("learned popular photos");

			}

			Recommender popularPhotoRec = new Recommender() {

				public Recommendation recommend(final String target,
						final int numItems) {
					ArrayList<RecommendedItem> recs = new ArrayList<RecommendedItem>();

					for (String p : popularPhotos) {
						recs.add(new RecommendedItem(p));
						if (recs.size() >= numItems) {
							break;
						}
					}

					return new Recommendation(target, recs);
				}
			};

			Recommender testRec = new Recommender() {

				public Recommendation recommend(final String target,
						final int numItems) {
					ArrayList<RecommendedItem> recs = new ArrayList<RecommendedItem>();

					for (String p : testUF.get(target)) {
						recs.add(new RecommendedItem(p));
					}

					return new Recommendation(target, recs);
				}
			};

			Recommender contactFavRec = new Recommender() {

				public Recommendation recommend(final String target,
						final int numItems) {
					ArrayList<RecommendedItem> recs = new ArrayList<RecommendedItem>();

					HashMap<String, Integer> favHist = new HashMap<String, Integer>();

					if (trainUU.get(target) == null) {
						return new Recommendation(target, recs);
					}

					for (String uc : trainUU.get(target)) {
						if (trainUF.get(uc) == null) {
							continue;
						}
						for (String p : trainUF.get(uc)) {
							favHist.put(p, favHist.get(p) == null ? 1 : favHist
									.get(p) + 1);
						}
					}

					DualHashBidiMap bidiFavoritedHist = new DualHashBidiMap(
							favHist);

					Object[] vals = favHist.values().toArray();
					Arrays.sort(vals);

					for (int i = 0; i < numItems && vals.length - i - 1 >= 0; i++) {
						recs.add(new RecommendedItem((String) bidiFavoritedHist
								.getKey(vals[vals.length - i - 1])));
					}

					return new Recommendation(target, recs);
				}
			};

			int rank = RandomUtil.randomInt(5, 30);

			SetRetrievalEvaluator evalContactFavs = new SetRetrievalEvaluator(testRec, rank);
			SetRetrievalEvaluator evalPopularPhotos = new SetRetrievalEvaluator(testRec, rank);

			Object[] users = trainUF.keySet().toArray();

			int numExperiments = RandomUtil.randomInt(10000, 40000);

			StopWatch s = new StopWatch();
			s.start();

			for (int i = 0; i < numExperiments; i++) {
				evalContactFavs.evaluate(contactFavRec.recommend(
						(String) users[random.nextInt(users.length)], rank));
				evalPopularPhotos.evaluate(popularPhotoRec.recommend(
						(String) users[random.nextInt(users.length)], rank));

			}

			s.stop();

			HashMap res = new HashMap();
			res.put("timeElapsed", (int) s.getElapsedTime());
			res.put("numExperiments", numExperiments);
			res.put("rank", rank);
			res.put("usersKnown", testUF.keySet().size());
			res.put("score", evalContactFavs.getScore());
			res.put("testRatio", testRatio);

			erContactFavs.record(res);

			res.put("score", evalPopularPhotos.getScore());
			erPopPhotos.record(res);
		}
	}
}
