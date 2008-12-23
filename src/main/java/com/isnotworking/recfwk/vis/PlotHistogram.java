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

	Created: Dec 16, 2008 11:44:36 PM by ricardocabral
 */
package com.isnotworking.recfwk.vis;

/**
 * Plots frequency histograms of given a series of entities or events and their
 * associated count of occurrence.
 * 
 * @author ricardocabral
 * 
 */
public interface PlotHistogram extends BasicPlot {

	/**
	 * Sets histogram data to be the frequency of occurrence of the given event
	 * count or entities identified by integers.
	 * 
	 * @param counts
	 *            all counts
	 */
	void setOccurrences(int[] counts);

	/**
	 * Sets histogram data to be the frequency of occurrence of the given
	 * entities, identified by strings.
	 * 
	 * @param entities
	 *            all entity occurrences
	 */
	void setOccurrences(String[] entities);

}
