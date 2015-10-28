package com.rients.org.sourceviewer.dao;

import com.rients.org.sourceviewer.service.TreeBo;

public interface TreeDAO {

	public void create(TreeBo p);
	
	public TreeBo readById(int id);
	
	public void update(TreeBo p);
	
	public int deleteById(int id);

	public int getMaxId();
}
