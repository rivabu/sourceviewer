package com.rients.org.sourceviewer.executables;

import java.io.File;
import java.io.IOException;

public class Initializer {

	public String getZipfile(String root) throws IOException {
		String zipFilename= "";
		File directory = new File(root + "//input");
		if(directory.exists()) {
		    File[] files = directory.listFiles();
		    if(files != null) { //some JVMs return null for empty dirs
			    if (files.length > 1) {
	            	System.out.println("multiple files found in input directory. Exit");
					System.exit(-1);
			    }
		        for(File f: files) {
		            if(f.isDirectory()) {
		            	System.out.println("directory found in input, but zip file expected.");
						System.exit(-1);
		            } else {
		            	zipFilename = f.getCanonicalPath();
		            }
		        }
		    } else {
				System.out.println("no zip file found!");
				System.exit(-1);
		    }

		} else {
			System.out.println("input directory missing!");
			System.exit(-1);
		}
		return zipFilename;
	}

}
