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

	Created: Dec 16, 2008 11:25:41 PM by ricardocabral
 */
package com.isnotworking.recfwk.vis;

/**
 * Plots experimental data where your have a series two variables and an
 * associated performance rate.
 * 
 * @author ricardocabral
 * 
 */
public interface PlotBivariatePerformance extends BasicPlot {

	/**
	 * Adds a series of experimental values for a two variables and their
	 * associated performance.
	 * 
	 * @param variable1
	 *            variable1 values. This array dimension should be equal to the
	 *            performance array and to the other variable array.
	 * @param variable2
	 *            variable1 values. This array dimension should be equal to the
	 *            performance array and to the other variable array.
	 * @param performance
	 *            performance values.
	 * @param performanceLabel
	 *            performance measure name
	 * @param variable1Label
	 *            variable1 name
	 * @param variable2Label
	 *            variable2 name
	 * @param plotType
	 *            the type of multivariate plot to draw. Should be one of:
	 *            <ul>
	 *            <li>"colorheatmap" - 2D scatterplot with a variable on each
	 *            axis and performance coded as the point color
	 *            <li>"grayheatmap" - 2D scatterplot with a variable on each
	 *            axis and performance coded as the point shade of gray
	 *            <li>"heightmap" - 3D plot, where performance is represented as
	 *            the Z (height) axis
	 *            </ul>
	 * 
	 */
	void addPerformanceSeries(float[] variable1, float[] variable2,
			float[] performance, String performanceLabel,
			String variable1Label, String variable2Label, String plotType);
}
