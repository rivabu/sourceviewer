package com.rients.org.sourceviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.rients.org.sourceviewer.service.FileBo;

@Service
public class FileDAOImpl implements FileDAO {

	@Autowired
	private MongoOperations mongoOps;
	private static final String FILE_COLLECTION = "Files";
	
	
	public void create(FileBo p) {
		this.mongoOps.insert(p, FILE_COLLECTION);
		System.out.println("mongoId: "+ p.getId());
	}

	
	public FileBo readById(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		return this.mongoOps.findOne(query, FileBo.class, FILE_COLLECTION);
	}
	
	public int deleteById(int projectId) {
		Query query = new Query(Criteria.where("projectId").is(projectId));
		WriteResult result = this.mongoOps.remove(query, FileBo.class, FILE_COLLECTION);
		return result.getN();
	}

}
