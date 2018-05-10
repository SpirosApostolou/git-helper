package com.intrasoft.git.helper.entities;

public class ContributionInfo {

	private String jenkinsBuild;
	private String name;
	private String email;
	private String filePath;
	
	public String getJenkinsBuild() {
		return jenkinsBuild;
	}

	public void setJenkinsBuild(String jenkinsBuild) {
		this.jenkinsBuild = jenkinsBuild;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public ContributionInfo(String name, String email, String filePath) {
		super();
		this.name = name;
		this.email = email;
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "ContributionInfo [jenkinsBuild=" + jenkinsBuild + ", name="
				+ name + ", email=" + email + ", filePath=" + filePath + "]";
	}
	

}
