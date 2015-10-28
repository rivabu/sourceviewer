package com.rients.org.sourceviewer.executables;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.rients.org.sourceviewer.domain.Project;
import com.rients.org.sourceviewer.domain.ReturnId;
import com.rients.org.sourceviewer.domain.Tree;
import com.rients.org.sourceviewer.domain.TreeElement;
import com.rients.org.sourceviewer.domain.Type;

public class Uploader {
	public static void main(String[] args) {
		int projectId = sendProject();
		sendTree(projectId);
	}
	
	private static int sendProject()
	{
	    final String uri = "http://localhost:8081/sourceviewer-rest/project";
	    Project project = new Project();
	    project.setName("test project");
	    project.setDescription("this is a sample project");
	    project.setId(-1);
	    RestTemplate restTemplate = new RestTemplate();
	    ReturnId returnId = restTemplate.postForObject( uri, project, ReturnId.class);
	 
	    System.out.println(returnId.getId());
	    return returnId.getId();
	}
	
	private static int sendTree(int projectId)
	{
	    final String uri = "http://localhost:8081/sourceviewer-rest/tree";
		Tree tree = new Tree();
		tree.setId(projectId);
		List<TreeElement> list = tree.getElements();
		TreeElement e1 = new TreeElement();
		e1.setId(1212);
		e1.setType(Type.root);
		e1.setName("Rients Test ProjectId = " + projectId);
		list.add(e1);
		TreeElement e2 = new TreeElement();
		e2.setId(1212);
		e2.setType(Type.node);
		e2.setName("pom.xml");
		e2.setExtension("xml");
		e2.setFileId("dfksfldfg");
		list.add(e2);
		TreeElement e3 = new TreeElement();
		e3.setId(1212);
		e3.setType(Type.node);
		e3.setName("readme.rd");
		e3.setExtension("rd");
		e3.setFileId("5yjjyojyoi");
		list.add(e3);
		TreeElement e4 = new TreeElement();
		e4.setId(1212);
		e4.setType(Type.endroot);
		list.add(e4);
	    RestTemplate restTemplate = new RestTemplate();
	    ReturnId returnId = restTemplate.postForObject( uri, tree, ReturnId.class);
	 
	    System.out.println(returnId.getId());
	    return returnId.getId();
	}

}
