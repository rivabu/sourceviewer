package com.rients.org.sourceviewer.executables;

import java.io.IOException;

import com.rients.org.sourceviewer.domain.Project;
import com.rients.org.sourceviewer.domain.Tree;

public class UnzipAndUploadProcessor {
	String zipFile = null;
	String root = "E://UPLOAD";
	
	public static void main(String[] args) {
		UnzipAndUploadProcessor processor = new UnzipAndUploadProcessor();
		processor.proces();
	}

	private void proces() {
		try {
			Initializer init = new Initializer();
			zipFile = init.getZipfile(root);
			
			DirCleaner dirCleaner = new DirCleaner();
			dirCleaner.cleanup(root);
			Unzip unzip = new Unzip(zipFile, root);
			Project project = unzip.unzipFunction();
			RestClient client = new RestClient();
			int projectId = client.store(project);
			System.out.println("project id: " + projectId);
			ProjectTree projectTree = new ProjectTree();
			Tree tree = projectTree.process(projectId, project.getName(), root);
			
			FileUploader fileUploader = new FileUploader(root);
			fileUploader.uploadFiles(tree, projectId);
			
			int treeId = client.store(tree);
			System.out.println("tree id: " + treeId);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
