package com.rients.org.sourceviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rients.org.sourceviewer.dao.TreeDAO;

@Service
public class TreeService {

	@Autowired
	TreeDAO treeDao;
	
	public TreeBo getTreeById(int id) {
		return treeDao.readById(id);
	}

	public void removeTree(int id) {
		treeDao.deleteById(id);
	}

	public int addTree(TreeBo p) {
		treeDao.create(p);
		return p.getId();
	}

	public int updateTree(TreeBo p) {
		treeDao.update(p);
		return p.getId();
		
	}

}
