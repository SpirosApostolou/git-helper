package com.intrasoft.git.helper;

import java.util.List;

public interface ContributionInfoFinder {

	public List<String> getFilesInError() throws Exception;
	
	public String getJenkinsBuildUrl() throws Exception;
	
}
