package com.rients.org.sourceviewer.executables;

import java.io.File;
import java.io.IOException;

public class DirCleaner {

	public void cleanup(String root) throws IOException {
		File directory = new File(root + "//processing");
        
		// if the output directory doesn't exist, create it
		if(directory.exists()) {
			deleteFolder(directory);
			directory.mkdirs();
		}
	}
	
	public void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files != null) { //some JVMs return null for empty dirs
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}

}
