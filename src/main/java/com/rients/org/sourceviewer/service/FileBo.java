package com.rients.org.sourceviewer.service;

public class FileBo {
	private String id;
	private int projectId;
	private String encodedContent;
	private String name;
	private boolean binary;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getEncodedContent() {
		return encodedContent;
	}
	public void setEncodedContent(String encodedContent) {
		this.encodedContent = encodedContent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isBinary() {
		return binary;
	}
	public void setBinary(boolean binary) {
		this.binary = binary;
	}

}
