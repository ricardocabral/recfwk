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

         Created: Mar 9, 2008 4:38:32 PM by ricardocabral
 */
package com.isnotworking.recfwk.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.isnotworking.recfwk.model.ItemTupleReader;
import com.isnotworking.recfwk.util.IOUtils;

/**
 * reads data from text comma-separated files
 * 
 * @author ricardocabral
 * 
 */
public class CSVItemTupleReader implements ItemTupleReader {

	private final String separator;
	private final BufferedReader reader;
	private final String filename;

	private static final Logger log = Logger
			.getLogger(CSVItemTupleReader.class);

	/**
	 * @param separator
	 *            field values separator
	 * @param filename
	 *            input filename (absolute path)
	 * @throws FileNotFoundException
	 */
	public CSVItemTupleReader(final String separator, final String filename)
			throws FileNotFoundException {
		super();
		this.separator = separator;
		this.filename = filename;

		reader = new BufferedReader(new FileReader(filename));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.isnotworking.recfwk.model.ItemTupleReader#readAll()
	 */
	public List readAll() {
		log.info("Reading:" + filename);
		List<String[]> data = new ArrayList<String[]>();
		try {
			while (true) {
				final String line = reader.readLine();
				if (line == null) {
					break;
				}
				if (line.length() > 0) {
					data.add(line.split(separator));
				}

			}
		} catch (FileNotFoundException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} finally {
			IOUtils.quietClose(reader);
		}
		log.info("Lines read: " + data.size());
		return data;
	}
}