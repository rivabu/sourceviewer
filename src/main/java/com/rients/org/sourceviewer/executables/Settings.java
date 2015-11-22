package com.rients.org.sourceviewer.executables;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by rivabu on 8-11-15.
 */
public class Settings   {
    Properties prop = new Properties();


    private static Settings instance = null;
    private Settings() {
        // Exists only to defeat instantiation.
    }
    public static Settings getInstance(String env) {
        if (instance == null) {
            instance = new Settings();
            instance.load(env);
        }
        return instance;
    }


    public static Settings getInstance() {
        if (instance == null) {
            System.out.println("no settings found");
            return null;
        }
        return instance;
    }




    private void load(String env) {
    	//Properties prop = new Properties();
    	InputStream input = null;
    	
    	try {
        
    		String filename = env + ".properties";
    		input = Settings.class.getClassLoader().getResourceAsStream(filename);
    		if(input==null){
    	            System.out.println("Sorry, unable to find " + filename);
    		    return;
    		}

            // load a properties file
            prop.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getProperty(String name) {
        String value = prop.getProperty(name);
        System.out.println("key: " + name + " value: " + value);
        return value;

    }

    public static void main(String[] args) {
        Settings settings = Settings.getInstance("unix");
        System.out.println(settings.getProperty("rootdir"));


    }
}
