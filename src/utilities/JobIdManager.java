package utilities;

import java.util.HashMap;

public class JobIdManager {
	public static JobIdManager mInstance;
	private HashMap<String, String> activeJobIds = new HashMap<String, String>();

	private JobIdManager() {
		// hiding constructor for direct instantiation
	}

	public static JobIdManager getInstance() {
		if (JobIdManager.mInstance == null) {
			synchronized (JobIdManager.class) {
				if (JobIdManager.mInstance == null) {
					JobIdManager.mInstance = new JobIdManager();
				}
			}
		}
		return JobIdManager.mInstance;
	}

	public void putJobId(String key, String jobId) {
		activeJobIds.put(key, jobId);
	}

	public String findJobId(String key) {
		return activeJobIds.get(key);
	}
}
