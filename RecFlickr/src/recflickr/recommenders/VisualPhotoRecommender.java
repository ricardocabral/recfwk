package recflickr.recommenders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.imgseek.imgdb.core.DoubleVector;
import net.imgseek.imgdb.core.ImgdbEngine;

import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.log4j.Logger;

import recfwk.engine.BaseConfig;
import recfwk.model.Recommendation;
import recfwk.model.RecommendedItem;
import recfwk.model.Recommender;

public class VisualPhotoRecommender implements Recommender {

	static ImgdbEngine engine;

	private static final Logger log = Logger
			.getLogger(VisualPhotoRecommender.class);

	static {
		engine = new ImgdbEngine();
		if (ImgdbEngine.loaddb(1, BaseConfig.dataDir + "imgdb/userfavs.imgdb") != 1) {
			log.error("error loading userfavs.imgdb");
		} else {
			log.info("imgdb img count = " + ImgdbEngine.getImgCount(1));
		}

	}
	private final HashMap<String, List<String>> userFavs;

	private final int numVisSim;

	/**
	 * 
	 */
	public VisualPhotoRecommender(HashMap<String, List<String>> userFavs,
			int numVisSim) {
		super();
		this.userFavs = userFavs;
		this.numVisSim = numVisSim;
	}

	public int getNumImages() {
		return ImgdbEngine.getImgCount(1);
	}

	public Recommendation recommend(String target, int numItems) {

		HashMap<String, Double> simHist = new HashMap<String, Double>();
		ArrayList<RecommendedItem> recs = new ArrayList<RecommendedItem>();

		List<String> targetFavs = userFavs.get(target);
		if (targetFavs == null)
			return new Recommendation(target, recs);

		for (String pids : userFavs.get(target)) {
			int pid = 0;
			try {
				pid = Integer.parseInt(pids);
			} catch (Exception e) {
				continue;
			}

			if (!ImgdbEngine.isImageOnDB(1, pid))
				continue;

			DoubleVector dv = ImgdbEngine.queryImgID(1, pid, numVisSim);
			for (int i = 0; i < numVisSim; i++) {
				if (targetFavs.contains("" + (int) dv.get(i * 2)))
					continue;
				simHist.put("" + (int) dv.get(i * 2), dv.get(i * 2 + 1));
			}
		}

		DualHashBidiMap bidiFavoritedHist = new DualHashBidiMap(simHist);

		Object[] vals = simHist.values().toArray();
		Arrays.sort(vals);

		for (int i = 0; i < numItems && vals.length - i - 1 >= 0; i++) {
			recs.add(new RecommendedItem((String) bidiFavoritedHist
					.getKey(vals[vals.length - i - 1])));
		}

		return new Recommendation(target, recs);

	}
}
