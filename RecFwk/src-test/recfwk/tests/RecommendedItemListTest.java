package recfwk.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import recfwk.model.RecommendedItem;

public class RecommendedItemListTest {
	@Test
	public void testContains() {
		List<RecommendedItem> recds = new ArrayList<RecommendedItem>();
		recds.add(new RecommendedItem("1"));
		assertTrue(recds.contains(new RecommendedItem("1")));
		assertFalse(recds.contains(new RecommendedItem("12")));
	}
}
