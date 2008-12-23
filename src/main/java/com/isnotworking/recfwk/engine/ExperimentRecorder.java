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

         Created: Aug 26, 2008 5:34:06 PM by ricardocabral
 */
package com.isnotworking.recfwk.engine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ho.yaml.Yaml;

import com.isnotworking.recfwk.util.StringUtils;

/**
 * Records experiment results to disk
 * 
 * @author ricardocabral
 * 
 */
public class ExperimentRecorder {
	private final String name;
	private static final Logger log = Logger
			.getLogger(ExperimentRecorder.class);

	File outFile;
	private List<Map> results;

	/**
	 * @param name
	 */
	public ExperimentRecorder(final String name) {
		super();
		this.name = name;
		results = new ArrayList<Map>();

		outFile = new File(BaseConfig.dataDir + StringUtils.slugify(name)
				+ ".yml");
	}

	/**
	 * record a single result instance
	 * 
	 * @param res
	 *            keys are experiment variables. Normally one of the entries are
	 *            the experiment result or performance
	 */
	public void record(final Map res) {
		if (outFile.exists()) {
			try {
				results = (List<Map>) Yaml.load(outFile);
			} catch (FileNotFoundException e) {
				log.error(e);
			}
		}

		// normalize everything to a string
		for (Object key : res.keySet()) {
			res.put(key, res.get(key).toString());
		}

		results.add(res);

		try {
			Yaml.dump(results, outFile, true);
		} catch (FileNotFoundException e) {
			log.error(e);
		}

	}
}
