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

         Created: Mar 9, 2008 5:44:41 PM by ricardocabral
 */
package com.isnotworking.recfwk.model;

/**
 * Stores the similarity between two content items
 * 
 * @author ricardocabral
 * 
 */
public interface ItemSimilarity {
	/**
	 * retrieves similarity between two items
	 * 
	 * @param i1
	 *            first item
	 * @param i2
	 *            second item
	 * @return float between 0 (items are as different as it gets) and 1 (items
	 *         are identical)
	 */
	public float getSimilarity(Item i1, Item i2);
}
