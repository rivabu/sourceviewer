package com.rients.org.sourceviewer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rients.org.sourceviewer.dao.FileDAO;
import com.rients.org.sourceviewer.dao.ProjectDAO;
import com.rients.org.sourceviewer.dao.TreeDAO;

@Service
public class ProjectService {

	@Autowired
	ProjectDAO projectDao;
	
	@Autowired
	TreeDAO treeDao;
	
	@Autowired
	FileDAO fileDao;
	
	public List<ProjectBo> listProjects() {
		return projectDao.getAll();
	}

	public ProjectBo getProjectById(int id) {
		return projectDao.readById(id);
	}

	public ProjectBo getProjectByName(String name) {
		return projectDao.readByName(name);
	}

	
	public void removeProject(int projectId) {
		projectDao.deleteById(projectId);
		treeDao.deleteById(projectId);
		fileDao.deleteById(projectId);
	}

	public int addProject(ProjectBo p) {
		int id = projectDao.getMaxId() + 1;
		p.setId(id);
		projectDao.create(p);
		return id;
	}

	public int updateProject(ProjectBo p) {
		projectDao.update(p);
		return p.getId();
		
	}

}
