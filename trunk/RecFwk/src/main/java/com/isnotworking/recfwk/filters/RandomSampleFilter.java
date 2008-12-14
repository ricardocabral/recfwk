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

         Created: Jul 7, 2008 11:33:20 PM by ricardocabral
 */
package com.isnotworking.recfwk.filters;

import java.util.List;
import java.util.Random;

/**
 * @author ricardocabral
 * 
 */
public class RandomSampleFilter implements Filter {

	private final float threshold;
	private static Random random;

	/**
	 * @param sampleSize
	 */
	public RandomSampleFilter(final float threshold) {
		super();
		this.threshold = threshold;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see recfwk.filters.Filter#isValidTuple(java.util.List)
	 */
	public boolean isValidTuple(final List tuple) {
		return random.nextFloat() > threshold;
	}
}
