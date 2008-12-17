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

	Created: Dec 16, 2008 11:49:45 PM by ricardocabral
 */
package com.isnotworking.recfwk.vis;

/**
 * Plots (line charts) experimental data where your have a series of performance
 * rates and the associated value of an experiment variable.
 * 
 * If several calls to <code>addPerformaceSeries()</code> are made, line charts
 * are stacked for comparison of one performance measure against each other.
 * 
 * If all series share the same variable, one can choose to only show the
 * variable labels once. (values must be fed multiple times though)
 * 
 * @author ricardocabral
 * 
 */
public interface PlotUnivariatePerformance extends BasicPlot {

	/**
	 * Adds a series of experimental values for a given variable and their
	 * associated performance.
	 * 
	 * @param variable
	 *            variable values. This array dimension should be equal to the
	 *            performance array.
	 * @param performance
	 *            performance values.
	 * @param performanceLabel
	 *            performance measure name
	 * @param variableLabel
	 *            variable name
	 */
	void addPerformanceSeries(float[] variable, float[] performance,
			String performanceLabel, String variableLabel);

	/**
	 * When stacking multiple one-variable plots where the influence of the same
	 * variable is compared against multiple performance meaures, only show the
	 * variable label once (on the lowest plot).
	 * 
	 * @param once
	 *            show only once.
	 */
	void showVariableLabelOnlyOnce(boolean once);

}
