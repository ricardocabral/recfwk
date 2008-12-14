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

         Created: Mar 9, 2008 4:25:03 PM by ricardocabral
 */
package com.isnotworking.recfwk.model;

/**
 * @author ricardocabral
 * 
 */
public interface Recommender {
	/**
	 * Recommend items to a target
	 * 
	 * @param target
	 *            the recommendation target (user id, group id etc)
	 * @param numItems
	 *            maximum number of recommended items to return (may return less
	 *            than this)
	 * @return
	 */
	Recommendation recommend(String target, int numItems);
}
