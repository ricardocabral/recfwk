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

         Created: Mar 9, 2008 4:39:01 PM by ricardocabral
 */
package com.isnotworking.recfwk.io;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.isnotworking.recfwk.model.ItemTupleWriter;

/**
 * writes a list of tuples to disk as comma-separated text files
 * 
 * @author ricardocabral
 * 
 */
public class CSVItemTupleWriter implements ItemTupleWriter {

	private static String join(final Collection s, final String delimiter) {
		StringBuffer buffer = new StringBuffer();
		Iterator iter = s.iterator();
		if (iter.hasNext()) {
			buffer.append(iter.next());
			while (iter.hasNext()) {
				buffer.append(delimiter);
				buffer.append(iter.next());
			}
		}
		return buffer.toString();
	}

	private final PrintWriter out;
	private final String delimiter;

	/**
	 * @param filename
	 *            output filename (absolute path)
	 * @param delimiter
	 *            field separator
	 * @throws IOException
	 */
	public CSVItemTupleWriter(final String filename, final String delimiter)
			throws IOException {
		super();

		this.delimiter = delimiter;
		out = new PrintWriter(new FileWriter(filename));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.isnotworking.recfwk.model.ItemTupleWriter#finish()
	 */
	public void finish() {
		out.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.isnotworking.recfwk.model.ItemTupleWriter#writeTuple(java.util.List)
	 */
	public boolean writeTuple(final List tuple) {
		out.println(join(tuple, delimiter));
		return true;
	}

}