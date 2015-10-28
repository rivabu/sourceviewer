package com.rients.org.sourceviewer.domain;

import java.util.ArrayList;
import java.util.List;

public class ProjectData {
	List<Project> projecten = new ArrayList<Project>();

	public List<Project> getProjecten() {
		return projecten;
	}

	public void setProjecten(List<Project> projecten) {
		this.projecten = projecten;
	}
}
