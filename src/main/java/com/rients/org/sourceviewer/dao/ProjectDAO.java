package com.rients.org.sourceviewer.dao;

import java.util.List;

import com.rients.org.sourceviewer.service.ProjectBo;

public interface ProjectDAO {

	public void create(ProjectBo p);
	
	public ProjectBo readById(int id);
	
	public List<ProjectBo> getAll();

	public void update(ProjectBo p);
	
	public int deleteById(int id);

	public int getMaxId();

	public ProjectBo readByName(String name);
}
