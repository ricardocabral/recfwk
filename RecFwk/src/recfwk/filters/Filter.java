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

	Created: Jul 7, 2008 11:30:17 PM by ricardocabral
 */
package recfwk.filters;

import java.util.List;

/**
 * @author ricardocabral
 * 
 */
public interface Filter {
	/**
	 * where the actual filtering is done
	 * 
	 * @param tuple
	 * @return true if tuple is valid according to filter rules
	 */
	boolean isValidTuple(List tuple);
}
