package com.intrasoft.git.helper;

import java.util.List;

import com.intrasoft.git.helper.entities.ContributionInfo;

public class EmailHelper {

	public String createContent(List<ContributionInfo> contributionInfo){
		ContributionInfoHelper contributionInfoHelper = new ContributionInfoHelper();
		String JenkinsBuildUrl = contributionInfo.get(0).getJenkinsBuild();
		StringBuilder builder = new StringBuilder();
		builder.append(System.getProperty("line.separator"));
		builder.append("JENKINS BUILD: ");
		builder.append(JenkinsBuildUrl);
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		builder.append("Last commiters mails of failed tests: ");
		builder.append(System.getProperty("line.separator"));
		builder.append(contributionInfoHelper.getResponsibleMailList(contributionInfo));
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		builder.append("Failures: ");
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		builder.append(contributionInfoHelper.getResponsibleOutputString(contributionInfo));
		return builder.toString();
	}
	
}
