package com.rients.org.sourceviewer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.rients.org.sourceviewer.service.TreeBo;

@Service
public class TreeDAOImpl implements TreeDAO {

	@Autowired
	private MongoOperations mongoOps;
	private static final String TREE_COLLECTION = "Trees";

	
	public void create(TreeBo p) {
		this.mongoOps.insert(p, TREE_COLLECTION);
	}

	
	public TreeBo readById(int id) {
		Query query = new Query(Criteria.where("id").is(id));
		return this.mongoOps.findOne(query, TreeBo.class, TREE_COLLECTION);
	}

	public void update(TreeBo p) {
		this.mongoOps.save(p, TREE_COLLECTION);
	}

	
	public int deleteById(int id) {
		Query query = new Query(Criteria.where("id").is(id));
		WriteResult result = this.mongoOps.remove(query, TreeBo.class, TREE_COLLECTION);
		return result.getN();
	}
	
	public int getMaxId() {
		
		Query query = new Query();
		query.with(new Sort(Sort.Direction.DESC, "id"));
		
		TreeBo tree = this.mongoOps.findOne(query, TreeBo.class, TREE_COLLECTION);
		int returnValue = 0;
		if (tree != null) {
			returnValue =  tree.getId();
		}
		return returnValue;

	}


}
