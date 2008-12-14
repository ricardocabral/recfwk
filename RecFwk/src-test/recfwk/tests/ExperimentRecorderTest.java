package recfwk.tests;

import java.io.File;
import java.util.HashMap;

import org.junit.Test;

import recfwk.engine.BaseConfig;
import recfwk.engine.ExperimentRecorder;
import recfwk.util.StringUtils;

public class ExperimentRecorderTest {

	@Test
	public void testRecord() {
		String expName = "test case 1";

		new File(BaseConfig.dataDir + StringUtils.slugify(expName) + ".yml")
				.delete();

		ExperimentRecorder er = new ExperimentRecorder(expName);
		HashMap res = new HashMap();
		res.put("parm1", 1);
		res.put("parm2", 0.00002000000002);
		res.put("parm3", "3");

		er.record(res);

		res = new HashMap();
		res.put("parm1", 1.000000000000000003f);
		res.put("parm2", 0.000002f);
		res.put("parm3", "3");

		er.record(res);

		res = new HashMap();
		res.put("parm1", 1);
		res.put("parm2", 2);
		res.put("parm3", "3");

		er.record(res);
	}
}
