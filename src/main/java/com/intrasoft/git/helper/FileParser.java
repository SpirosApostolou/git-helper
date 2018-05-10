package com.intrasoft.git.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.intrasoft.git.helper.constants.AppConstants;

public class FileParser implements ContributionInfoFinder {
	
	private File inputMailFile;
	
	public FileParser() {
		URL inputMailFileUrl = Thread.currentThread().getContextClassLoader().getResource(AppConstants.INPUT_FROM_MAIL_FILE.value());
		this.inputMailFile = new File(inputMailFileUrl.getPath());
	}

	@Override
	public List<String> getFilesInError() throws Exception {
		List<String> urlsOfTestsInError = new ArrayList<String>(); 
		FileReader fileReader = new FileReader(this.inputMailFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if(line.indexOf("Failed: com.") != -1){
				urlsOfTestsInError.add(getFileUrlFromPackage(line));
			}
		}
		fileReader.close();
		return urlsOfTestsInError;
	}
	
	public String getFileUrlFromPackage(String fileline){
		int beginIndex = fileline.indexOf("com.");
		int endIndex = fileline.lastIndexOf(".");
		String testPackage = fileline.substring(beginIndex, endIndex);
		return AppConstants.TEST_FILES_PATH.value() + testPackage.replace(".", "/") + ".java";
	}
	
	public String getJenkinsBuildUrl() throws IOException{
		String jenkinsbuildUrl = null; 
		FileReader fileReader = new FileReader(this.inputMailFile);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			if(line.indexOf("Build URL") != -1){
				int beginIndex = line.indexOf("Build URL") + "Build URL".length();
				jenkinsbuildUrl = line.substring(beginIndex).trim();
				break;
			}
		}
		fileReader.close();
		return jenkinsbuildUrl;
		
	}

}
