package recfwk.engine;

import java.util.List;

import recfwk.model.Recommendation;
import recfwk.model.RecommendedItem;
import recfwk.model.Recommender;

public class SetRetrievalEvaluator {

	private final Recommender tester;
	private final int rank;
	private float totalScore = 0;
	private int numEvals = 0;

	/**
	 * @param tester
	 */
	public SetRetrievalEvaluator(final Recommender tester, final int rank) {
		super();
		this.tester = tester;
		this.rank = rank;
	}

	public void evaluate(final Recommendation recd) {
		if (recd.getRecdItems().size() < 1) {
			return;
		}
		Recommendation recTest = tester.recommend(recd.getTargetItemId(), rank);
		List<RecommendedItem> recds = recd.getRecdItems();

		float thisScore = 0;
		for (int i = 0; i < recds.size() && i < rank; i++) {
			thisScore += recTest.getRecdItems().contains(recds.get(i)) ? 1 : 0;
		}
		totalScore += thisScore / Math.min(rank, recds.size());

		numEvals++;
	}

	public float getScore() {
		return totalScore / numEvals;
	}
}