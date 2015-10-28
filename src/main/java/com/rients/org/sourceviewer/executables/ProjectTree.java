package com.rients.org.sourceviewer.executables;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.rients.org.sourceviewer.domain.Tree;
import com.rients.org.sourceviewer.domain.TreeElement;
import com.rients.org.sourceviewer.domain.Type;

public class ProjectTree {
	int counter = 1;
	

	public static void main(String args[]) throws IOException {
		ProjectTree pt = new ProjectTree();
		pt.generate(1, "TheCrudder", "E://UPLOAD");
	}
	
	public Tree process(int projectId, String projectName, String root) throws IOException {
		Tree tree = this.generate(projectId, projectName, root);
		removeEmptyDirs(tree.getElements());
		reorderDirs(tree.getElements());
		
		return tree;
		
	}
	
	public Tree generate(int projectId, String projectName, String root) throws IOException {
		File directory = new File(root + "//processing//" + projectName);
		Tree tree = new Tree();
		tree.setId(projectId);
		List<TreeElement> list = tree.getElements();
		TreeElement rootElem = new TreeElement();
		rootElem.setId(counter);
		counter++;
		rootElem.setType(Type.root);
		rootElem.setName(projectName);
		list.add(rootElem);
		
		generateStructure(directory, list);
		
		TreeElement endroot = new TreeElement();
		endroot.setId(counter);
		counter++;
		endroot.setType(Type.endroot);
		endroot.setName(projectName);
		list.add(endroot);
		
		return tree;
	}

	public void generateStructure(File folder, List<TreeElement> list) throws IOException {
		
	    File[] files = folder.listFiles();
	    if(files != null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	            	TreeElement dir = new TreeElement();
	            	dir.setId(counter);
		    		counter++;
		    		dir.setType(Type.dir);
		    		dir.setName(f.getName());
		    		dir.setExtension(null);
		    		dir.setFileId(null);
		    		dir.setStatus("open");
		    		list.add(dir);
		    		
	            	generateStructure(f, list);
	            	
	            	TreeElement enddir = new TreeElement();
	            	enddir.setId(counter);
		    		counter++;
		    		enddir.setType(Type.enddir);
		    		enddir.setName(null);
		    		enddir.setExtension(null);
		    		enddir.setFileId(null);
		    		list.add(enddir);
	            	
	            } else {
	            	if (f.length() < 5000) {
		            	TreeElement file = new TreeElement();
		        		file.setId(counter);
		        		counter++;
		        		file.setType(Type.node);
		        		file.setName(f.getName().substring(3)); // remove zzz
		        		file.setExtension(getExtension(f.getName()));
		        		file.setFileId(f.getCanonicalPath());
		        		list.add(file);
	            	}
	            }
	        }
	    }
	}
	
	private void removeEmptyDirs(List<TreeElement> list) {
		
		int counter = 0;
		TreeElement[] elems = list.toArray(new TreeElement[list.size()]);
		while (true) {
			if (elems[counter].getType().equals(Type.endroot) ) {
				break;
			}
			if (elems[counter].getType().equals(Type.dir) && (elems[counter + 1].getType().equals(Type.enddir))) {
				list.remove(elems[counter]);
				list.remove(elems[counter + 1]);
				counter = 0;
				elems = list.toArray(new TreeElement[list.size()]);
			} else {
				counter++;
			}
		}
	}

	private void reorderDirs(List<TreeElement> list) {
		
		int counter = 0;
		TreeElement[] elems = list.toArray(new TreeElement[list.size()]);
		while (true) {
			if (elems[counter].getType().equals(Type.endroot) ) {
				break;
			}
			// node should always come before dir
			if (elems[counter].getType().equals(Type.dir) && (elems[counter + 1].getType().equals(Type.node))) {
				// swap
				TreeElement first = elems[counter];
				TreeElement second = elems[counter + 1];
				elems[counter] = second;
				elems[counter + 1] = first;
				counter++;
				//elems = list.toArray(new TreeElement[list.size()]);
			} else {
				counter++;
			}
		}
	}

	private String getExtension(String filename) {
		String extension = "txt";
		//System.out.println(filename);
		if (filename.indexOf(".") > 0 && !filename.endsWith(".")) {
			extension = filename.substring(filename.indexOf(".") + 1);
		}
		return extension;
	}
}
