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

	Created: Mar 9, 2008 6:37:13 PM by ricardocabral
 */
package recfwk.model;

/**
 * @author ricardocabral
 * 
 */
public class RecommendedItem extends Item {

	public float rate = 0;

	public RecommendedItem(final String id) {
		super(id);

	}

	@Override
	public boolean equals(final Object obj) {
		return ((RecommendedItem) obj).getId().equals(getId());
	}
}
