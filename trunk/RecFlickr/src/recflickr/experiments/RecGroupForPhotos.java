package recflickr.experiments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.planetj.taste.common.TasteException;
import com.planetj.taste.correlation.ItemCorrelation;
import com.planetj.taste.impl.correlation.GenericItemCorrelation;
import com.planetj.taste.impl.model.file.FileDataModel;
import com.planetj.taste.impl.recommender.CachingRecommender;
import com.planetj.taste.impl.recommender.GenericItemBasedRecommender;
import com.planetj.taste.model.DataModel;
import com.planetj.taste.recommender.RecommendedItem;
import com.planetj.taste.recommender.Recommender;

/**
 * @author ricardocabral
 * 
 */
public class RecGroupForPhotos {

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws TasteException
	 */
	public static void main(final String[] args) throws FileNotFoundException,
			TasteException {

		DataModel model = new FileDataModel(new File("data.txt"));

		Collection<GenericItemCorrelation.ItemItemCorrelation> correlations = new ArrayList<GenericItemCorrelation.ItemItemCorrelation>();

		ItemCorrelation itemCorrelation = new GenericItemCorrelation(
				correlations);

		Recommender recommender = new GenericItemBasedRecommender(model,
				itemCorrelation);
		Recommender cachingRecommender = new CachingRecommender(recommender);

		List<RecommendedItem> recommendations = cachingRecommender.recommend(
				"1234", 10);
	}
}
