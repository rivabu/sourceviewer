package com.rients.org.sourceviewer;

import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rients.org.sourceviewer.domain.FileContent;
import com.rients.org.sourceviewer.domain.ReturnIdString;
import com.rients.org.sourceviewer.service.FileBo;
import com.rients.org.sourceviewer.service.FileService;

@RestController
public class FileRestController {
	@Autowired
	private FileService fileService;

	
    
	
	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	public ResponseEntity<FileContent> getFileById(@PathVariable("id") String id) {
		FileBo fileBo = fileService.getFileById(id);
		
		FileContent file = convertToFile(fileBo);
		ResponseEntity<FileContent> response = new ResponseEntity<FileContent>(file, OK);
		return response;
	}

	private FileContent convertToFile(FileBo fileBo) {
		FileContent file = new FileContent();
		file.setEncodedContent(fileBo.getEncodedContent());
		file.setId(fileBo.getId());
		file.setName(fileBo.getName());
		file.setProjectId(fileBo.getProjectId());
		file.setBinary(fileBo.isBinary());
		return file;
	}

	
	
	@RequestMapping(value = "/file", method = RequestMethod.POST)
	public ResponseEntity<ReturnIdString> addFile( @Valid @RequestBody FileContent fileContent) throws MethodArgumentNotValidException {
		FileBo fileBo = convertToFileBo(fileContent);
		fileService.addFile(fileBo);
		return new ResponseEntity<>(new ReturnIdString(fileBo.getId()), OK);

	}
	
	private FileBo convertToFileBo(FileContent fileContent) {
		FileBo fileBo = new FileBo();
		fileBo.setEncodedContent(fileContent.getEncodedContent());
		fileBo.setId(fileContent.getId());
		fileBo.setName(fileContent.getName());
		fileBo.setProjectId(fileContent.getProjectId());
		fileBo.setBinary(fileContent.isBinary());
		return fileBo;
	}

 
}

