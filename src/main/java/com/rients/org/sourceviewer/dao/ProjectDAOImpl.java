package com.rients.org.sourceviewer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.rients.org.sourceviewer.service.ProjectBo;

@Service
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private MongoOperations mongoOps;
	private static final String PROJECT_COLLECTION = "Projects";
	
//	public ProjectDAOImpl(MongoOperations mongoOps){
//		this.mongoOps=mongoOps;
//	}
	
	
	public void create(ProjectBo p) {
		this.mongoOps.insert(p, PROJECT_COLLECTION);
	}

	
	public ProjectBo readById(int id) {
		Query query = new Query(Criteria.where("id").is(id));
		return this.mongoOps.findOne(query, ProjectBo.class, PROJECT_COLLECTION);
	}

	
	public ProjectBo readByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		return this.mongoOps.findOne(query, ProjectBo.class, PROJECT_COLLECTION);
	}
	
	public void update(ProjectBo p) {
		this.mongoOps.save(p, PROJECT_COLLECTION);
	}

	
	public int deleteById(int id) {
		Query query = new Query(Criteria.where("id").is(id));
		WriteResult result = this.mongoOps.remove(query, ProjectBo.class, PROJECT_COLLECTION);
		return result.getN();
	}
	
	public int getMaxId() {
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "id"));
		
		ProjectBo project = this.mongoOps.findOne(query, ProjectBo.class, PROJECT_COLLECTION);
		int returnValue = 0;
		if (project != null) {
			returnValue =  project.getId();
		}
		return returnValue;

	}


	@Override
	public List<ProjectBo> getAll() {
		List<ProjectBo> projects = this.mongoOps.findAll(ProjectBo.class, PROJECT_COLLECTION);
		return projects;
	}

}
