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

	Created: Jul 20, 2008 11:13:45 PM by ricardocabral
 */
package com.isnotworking;

import static org.junit.Assert.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.isnotworking.recfwk.io.CSVItemTupleReader;
import com.isnotworking.recfwk.io.CSVItemTupleWriter;

/**
 * @author ricardocabral
 * 
 */
public class CSVItemTupleTest {

	/**
	 * Test method for {@link recfwk.io.CSVItemTupleReader#readAll()}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testReadAll() throws IOException {

		String fileName = "tst.csv";

		FileWriter outFile = new FileWriter(fileName);
		PrintWriter out = new PrintWriter(outFile);

		// Also could be written as follows on one line
		// Printwriter out = new PrintWriter(new FileWriter(args[0]));

		// Write text to file
		out.println("1;1");
		out.println("2;4");
		out.println("3;8");
		out.close();

		List<String[]> tuples = new CSVItemTupleReader(";", fileName).readAll();
		assertEquals(3, tuples.size());
		assertEquals("4", tuples.get(1)[1]);

	}

	@Test
	public void testWrite() throws IOException {

		String fileName = "tst.csv";

		CSVItemTupleWriter citw = new CSVItemTupleWriter(fileName, ";");
		ArrayList al = new ArrayList<String>();
		al.add("1");
		al.add("1");
		citw.writeTuple(al);
		al.clear();
		al.add("2");
		al.add("4");
		citw.writeTuple(al);
		al.clear();
		al.add("3");
		al.add("8");
		citw.writeTuple(al);
		al.clear();

		citw.finish();

		List<String[]> tuples = new CSVItemTupleReader(";", fileName).readAll();

		assertEquals(3, tuples.size());
		assertEquals("4", tuples.get(1)[1]);

	}
}
