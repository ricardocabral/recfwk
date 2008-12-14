package com.isnotworking.recfwk.engine;

import java.util.List;

import com.isnotworking.recfwk.model.Recommendation;
import com.isnotworking.recfwk.model.RecommendedItem;
import com.isnotworking.recfwk.model.Recommender;

/**
 * Evaluates how well a recommender suggests items that should belong to a given
 * set, by verifying whether a recommended item is indeed on the training set
 * (repeated hold-out technique)
 * 
 * @author ricardocabral
 * 
 */
public class SetRetrievalEvaluator {

	private final Recommender tester;
	private final int rank;
	private float totalScore = 0;
	private int numEvals = 0;

	/**
	 * @param tester
	 *            the recommender that will validate recommendations made by the
	 *            recommender being evaluated. This tester recommender is
	 *            normally trained with test/hidden data.
	 * @param rank
	 *            the number of recommendations made by the recommender being
	 *            tested at each run. When the precision is reported, this
	 *            number is the equivalent of a precision rank
	 */
	public SetRetrievalEvaluator(final Recommender tester, final int rank) {
		super();
		this.tester = tester;
		this.rank = rank;
	}

	/**
	 * feeds into this evaluation session the results of an experiment
	 * 
	 * @param recd
	 *            the recommendation made by the recommender being evaluated
	 */
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

	/**
	 * the current precision for this evaluation session. This is the number of
	 * correct guesses divided by the number of recommendations submitted
	 * 
	 * @return
	 */
	public float getScore() {
		return totalScore / numEvals;
	}
}