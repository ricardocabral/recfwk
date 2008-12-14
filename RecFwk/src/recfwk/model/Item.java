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

	Created: Mar 9, 2008 4:26:07 PM by ricardocabral
 */
package recfwk.model;

/**
 * Holds basic info about a content item
 * 
 * 
 * @author ricardocabral
 * 
 */
public class Item {
	/**
	 * unique global id
	 * 
	 */
	private final String id;

	/**
	 * @param id
	 *            new item id
	 */
	public Item(final String id) {
		super();
		this.id = id;
	}

	/**
	 * @return item unique id
	 */
	public String getId() {
		return id;
	}
}
