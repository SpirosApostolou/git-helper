package com.intrasoft.git.helper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import com.intrasoft.git.helper.constants.AppConstants;
import com.intrasoft.git.helper.constants.EmailConstants;
import com.intrasoft.git.helper.entities.ContributionInfo;
import com.intrasoft.git.helper.entities.EmailInfo;

/**
 *
 */
public class AppContributorFinder 
{
    public static void main( String[] args ) throws IOException, NoHeadException, GitAPIException
    {
    	FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
    	Repository repository = repositoryBuilder.setGitDir(new File(AppConstants.ICI_PSRM_PATH.value()))
    	                .readEnvironment() // scan environment GIT_* variables
    	                .findGitDir() // scan up the file system tree
    	                .build();
    	System.out.println(repository.getBranch());
    	Git git = new Git(repository);
    	
    	GitHepler gitHepler = new GitHepler(repository, git);
    	
    	
    	ContributionInfoFinder fileParser = new FileParser();
    	List<String> testsInError = null;
    	try {
    		testsInError = fileParser.getFilesInError();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	List<ContributionInfo> allContributionInfo = gitHepler.getLastFileCommiterInfo(testsInError);
    	//set the jenkins build url
    	for(ContributionInfo info : allContributionInfo){
    		String jenkinsBuildUrl = null;
			try {
				jenkinsBuildUrl = fileParser.getJenkinsBuildUrl();
			} catch (Exception e) {
				e.printStackTrace();
			}
    		info.setJenkinsBuild(jenkinsBuildUrl);
    	}
    	
    	ContributionInfoHelper contributionInfoHelper = new ContributionInfoHelper();
    	List<ContributionInfo> greekTeamsContributionInfo = contributionInfoHelper.getGreekTeamsContributionInfo(allContributionInfo);
    	contributionInfoHelper.consoleOutContributionInfo(greekTeamsContributionInfo);
    	
    	List<ContributionInfo> otherTeamsContributionInfo = contributionInfoHelper.getOtherTeamsContributionInfo(allContributionInfo);
    	contributionInfoHelper.consoleOutContributionInfo(otherTeamsContributionInfo);
    	
    	//send mail for testing for everybody failures to me
    	EmailHelper emailHelper = new EmailHelper();
    	EmailInfo emailInfo = new EmailInfo();
    	emailInfo.setMailFrom(EmailConstants.ADMIN_MAIL.value());
    	emailInfo.setMailTo(EmailConstants.ADMIN_MAIL.value());
    	emailInfo.setMailcc("apostolouspiros.ist@gmail.com");
    	emailInfo.setSubject(EmailConstants.MESSAGE_SUBJECT.value());
    	emailInfo.setMessageContent(emailHelper.createContent(allContributionInfo));
    	EmailSender sender = new EmailSender();
    	sender.setEmailInfo(emailInfo);
    	
    	sender.sendEmail();
    	
    	
    }
}
