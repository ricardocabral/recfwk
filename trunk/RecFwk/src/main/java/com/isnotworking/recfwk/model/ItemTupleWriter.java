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

         Created: Mar 9, 2008 4:37:52 PM by ricardocabral
 */
package com.isnotworking.recfwk.model;

import java.util.List;

/**
 * Persist data tuples
 * 
 * @author ricardocabral
 * 
 */
public interface ItemTupleWriter {

	/**
	 * should be called when all tuples have been fed through
	 * {@link #writeTuple(List)}
	 * 
	 */
	public void finish();

	/**
	 * appends a tuple to the write buffer
	 * 
	 * @param tuple
	 *            list of objects to be serialized as part of this tuple
	 * @return success or not
	 */
	public boolean writeTuple(List tuple);

}
