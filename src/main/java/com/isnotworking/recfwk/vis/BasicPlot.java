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

	Created: Dec 17, 2008 12:17:23 AM by ricardocabral
 */
package com.isnotworking.recfwk.vis;

/**
 * Provides basic plot functions.
 * 
 * @author ricardocabral
 * 
 */
public interface BasicPlot {
	/**
	 * Saves to an image file the plot with the current data and config parms.
	 * 
	 * @param filename
	 *            output image filename (absolute path). Image file format is
	 *            guessed from the file extension
	 */
	void save(String filename);
}
