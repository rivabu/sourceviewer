package com.rients.org.sourceviewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rients.org.sourceviewer.dao.FileDAO;

@Service
public class FileService {
	@Autowired
	FileDAO fileDao;

	public FileBo getFileById(String id) {
		return fileDao.readById(id);
	}

	public String addFile(FileBo fileBo) {
		fileDao.create(fileBo);
		return fileBo.getId();
		
	}

	public void deleteByProjectId(int projectId) {
		fileDao.deleteById(projectId);
	}
}
