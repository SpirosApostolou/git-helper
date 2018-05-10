package com.intrasoft.git.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intrasoft.git.helper.constants.AppConstants;
import com.intrasoft.git.helper.entities.ContributionInfo;

public class ContributionInfoHelper {

	public List<ContributionInfo> getGreekTeamsContributionInfo(List<ContributionInfo> allContributionInfo){
		List<ContributionInfo> greekTeamsContributionInfos = new ArrayList<ContributionInfo>();
		for(ContributionInfo contributionInfo : allContributionInfo){
			if(contributionInfo.getEmail().indexOf(AppConstants.INTRASOFT_MAIL_PART.value()) != -1){
				greekTeamsContributionInfos.add(contributionInfo);
			}
		}
		return greekTeamsContributionInfos;
	}
	
	
	public List<ContributionInfo> getOtherTeamsContributionInfo(List<ContributionInfo> allContributionInfo){
		List<ContributionInfo> otherTeamsContributionInfos = new ArrayList<ContributionInfo>();
		for(ContributionInfo contributionInfo : allContributionInfo){
			if(contributionInfo.getEmail().indexOf(AppConstants.INTRASOFT_MAIL_PART.value()) == -1){
				otherTeamsContributionInfos.add(contributionInfo);
			}
		}
		return otherTeamsContributionInfos;
	}

	public void consoleOutContributionInfo(List<ContributionInfo> contributionInfo){
		for(ContributionInfo info : contributionInfo){
			System.out.println(getResponsibleOutputString(info));
		}
	}
	
	public String getResponsibleOutputString(List<ContributionInfo> contributionInfo){
		StringBuilder builder = new StringBuilder();
		for(ContributionInfo info : contributionInfo){
			builder.append(getResponsibleOutputString(info));
		}
		return builder.toString();
	}

	
	public String getResponsibleOutputString(ContributionInfo contributionInfo){
		StringBuilder builder = new StringBuilder();
		builder.append(contributionInfo.getFilePath());
		builder.append(System.getProperty("line.separator"));
		builder.append("Last commiter: ");
		builder.append(contributionInfo.getName());
		builder.append(System.getProperty("line.separator"));
		builder.append("Email: ");
		builder.append(contributionInfo.getEmail());
		builder.append(System.getProperty("line.separator"));
		builder.append(System.getProperty("line.separator"));
		return builder.toString();
	}
	
	public String getResponsibleMailList(List<ContributionInfo> contributionInfo){
		Set<String> uniqueMails = getResponsiblesMails(contributionInfo);
		StringBuilder builder = new StringBuilder();
		for(String mail : uniqueMails){
			builder.append(mail);
			builder.append(";");
		}
		return builder.toString();
	}
	
	public Set<String> getResponsiblesMails(List<ContributionInfo> contributionInfo){
		Set<String> responsilbeMails = new HashSet<String>();
		for(ContributionInfo info : contributionInfo){
			responsilbeMails.add(info.getEmail());
		}
		return responsilbeMails;
	}

}
