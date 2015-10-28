package com.rients.org.sourceviewer.executables;

import org.springframework.web.client.RestTemplate;

import com.rients.org.sourceviewer.domain.FileContent;
import com.rients.org.sourceviewer.domain.Project;
import com.rients.org.sourceviewer.domain.ReturnId;
import com.rients.org.sourceviewer.domain.ReturnIdString;
import com.rients.org.sourceviewer.domain.Tree;

public class RestClient {

	public int store(Project project) {
		final String uri = "http://localhost:8081/sourceviewer-rest/project";
	    project.setId(-1);
	    RestTemplate restTemplate = new RestTemplate();
	    ReturnId returnId = restTemplate.postForObject( uri, project, ReturnId.class);
	 
	    System.out.println(returnId.getId());
	    return returnId.getId();
	}

	public int store(Tree tree) {
		final String uri = "http://localhost:8081/sourceviewer-rest/tree";
		RestTemplate restTemplate = new RestTemplate();
	    ReturnId returnId = restTemplate.postForObject( uri, tree, ReturnId.class);
	 
	    System.out.println(returnId.getId());
	    return returnId.getId();
	}

	public String store(FileContent fileContent) {
		final String uri = "http://localhost:8081/sourceviewer-rest/file";
	    RestTemplate restTemplate = new RestTemplate();
	    ReturnIdString returnId = restTemplate.postForObject( uri, fileContent, ReturnIdString.class);
	 
	    System.out.println(returnId.getId());
	    return returnId.getId();
	}

}
