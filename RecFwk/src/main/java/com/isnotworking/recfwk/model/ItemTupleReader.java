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

         Created: Mar 9, 2008 4:35:40 PM by ricardocabral
 */
package com.isnotworking.recfwk.model;

import java.util.List;

/**
 * Reads data tuples
 * 
 * @author ricardocabral
 * 
 */
public interface ItemTupleReader {

	/**
	 * get all available tuples from this data input
	 * 
	 * @return list of tuples as string arrays
	 */
	List<String[]> readAll();

}
