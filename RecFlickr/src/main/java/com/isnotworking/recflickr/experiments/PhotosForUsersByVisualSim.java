package com.isnotworking.recflickr.experiments;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.isnotworking.recflickr.recommenders.VisualPhotoRecommender;
import com.isnotworking.recfwk.engine.BaseConfig;
import com.isnotworking.recfwk.engine.ExperimentRecorder;
import com.isnotworking.recfwk.engine.SetRetrievalEvaluator;
import com.isnotworking.recfwk.io.CSVItemTupleReader;
import com.isnotworking.recfwk.model.Recommendation;
import com.isnotworking.recfwk.model.RecommendedItem;
import com.isnotworking.recfwk.model.Recommender;
import com.isnotworking.recfwk.util.RandomUtil;
import com.isnotworking.recfwk.util.StopWatch;

public class PhotosForUsersByVisualSim extends BaseFlickrExperiment {

	private static Random random = new Random();

	private static final Logger log = Logger
			.getLogger(PhotosForUsersByVisualSim.class);

	/**
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(final String[] args) throws FileNotFoundException {

		List<String[]> userfav = new CSVItemTupleReader(",", BaseConfig.dataDir
				+ "user-favorites.csv").readAll();

		ExperimentRecorder erVisual = new ExperimentRecorder(
				"PhotosForUsersByVisualSim");

		while (true) {
			log.info("Experiment started.");

			float testRatio = RandomUtil.randomFloat(0.2, 0.8);

			final HashMap<String, List<String>> testUF = new HashMap<String, List<String>>();
			final HashMap<String, List<String>> trainUF = new HashMap<String, List<String>>();

			for (int i = 0; i < userfav.size(); i++) {
				if (userfav.get(i).length != 2) {
					continue;
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

			int rank = RandomUtil.randomInt(5, 30);

			SetRetrievalEvaluator evalVisual = new SetRetrievalEvaluator(
					testRec, rank);

			Object[] users = trainUF.keySet().toArray();

			int numExperiments = RandomUtil.randomInt(100, 400);

			StopWatch s = new StopWatch();
			s.start();

			int numVisSim = RandomUtil.randomInt(2, 30);

			VisualPhotoRecommender visualRec = new VisualPhotoRecommender(
					trainUF, numVisSim);

			for (int i = 0; i < numExperiments; i++) {
				evalVisual.evaluate(visualRec.recommend((String) users[random
						.nextInt(users.length)], rank));

			}

			s.stop();

			HashMap res = new HashMap();
			res.put("timeElapsed", (int) s.getElapsedTime());
			res.put("numExperiments", numExperiments);
			res.put("rank", rank);
			res.put("usersKnown", testUF.keySet().size());
			res.put("score", evalVisual.getScore());
			res.put("numVisSim", numVisSim);
			res.put("numImages", visualRec.getNumImages());
			res.put("testRatio", testRatio);

			erVisual.record(res);
		}
	}
}
