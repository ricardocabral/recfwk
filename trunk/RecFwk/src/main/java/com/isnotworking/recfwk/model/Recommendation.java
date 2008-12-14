/*  This file is part of {RecAnalysis}.

    {RecAnalysis} is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    {RecAnalysis} is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with {RecAnalysis}.  If not, see <http://www.gnu.org/licenses/>.

         Created: Mar 9, 2008 6:56:04 PM by ricardocabral
 */
package com.isnotworking.recfwk.model;

import java.util.List;

/**
 * represents a recommendation made
 * 
 * @author ricardocabral
 * 
 */
public class Recommendation {
	private final String targetItemId;
	private final List<RecommendedItem> recdItems;

	/**
	 * @param targetItemId
	 *            the target item these recommendations were made to
	 * @param recdItems
	 *            list of recommended items
	 */
	public Recommendation(final String targetItemId,
			final List<RecommendedItem> recdItems) {
		super();
		this.targetItemId = targetItemId;
		this.recdItems = recdItems;
	}

	/**
	 * items recommended
	 * 
	 * @return
	 */
	public List<RecommendedItem> getRecdItems() {
		return recdItems;
	}

	/**
	 * target item id to which these recommendations were made to
	 * 
	 * @return item id
	 */
	public String getTargetItemId() {
		return targetItemId;
	}
}
