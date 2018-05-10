package com.intrasoft.git.helper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;

import com.intrasoft.git.helper.entities.ContributionInfo;

public class GitHepler {

	private Repository repository;
	private Git git;
	
	public Repository getRepository() {
		return repository;
	}
	
	public void setRepository(Repository repository) {
		this.repository = repository;
	}
	
	public Git getGit() {
		return git;
	}
	
	public void setGit(Git git) {
		this.git = git;
	}
	
	public GitHepler(Repository repository, Git git) {
		super();
		this.repository = repository;
		this.git = git;
	}
	
	public Iterable<RevCommit> getFileLogs(String filePath) throws NoHeadException, GitAPIException{
		return git.log().addPath(filePath).call();
	}
	
	public ContributionInfo getLastFileCommiterInfo(String filePath) throws NoHeadException, GitAPIException {
		Iterable<RevCommit> fileLogs = git.log().addPath(filePath).call();
		//get the first log entity
		RevCommit mostRecentCommit = fileLogs.iterator().next();
		ContributionInfo contributionInfo = new ContributionInfo(mostRecentCommit.getAuthorIdent().getName(), mostRecentCommit.getAuthorIdent().getEmailAddress(), filePath);
		return contributionInfo;
	}
	
	public List<ContributionInfo> getLastFileCommiterInfo(List<String> filePaths) throws NoHeadException, GitAPIException{
		List<ContributionInfo> contributionInfos = new ArrayList<ContributionInfo>();
		for(String filePath:filePaths){
			contributionInfos.add(getLastFileCommiterInfo(filePath));
		}
		return contributionInfos;
	}
	
	
}
