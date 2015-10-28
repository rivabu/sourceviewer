package com.rients.org.sourceviewer.dao;

import com.rients.org.sourceviewer.service.FileBo;

public interface FileDAO {

	public void create(FileBo fileBo);

	public FileBo readById(String id);
	
	public int deleteById(int projectId);

}
