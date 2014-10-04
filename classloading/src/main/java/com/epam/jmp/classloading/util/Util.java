/**
 * 
 */
package com.epam.jmp.classloading.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 * @author SIVASHKOFF
 *
 */
public class Util {

	public static List<File> getListJars(String dirPath) {
		List<File> jars = null;
		File dir = new File(dirPath);
		if(dir.exists() && dir.isDirectory()) {
			jars = (List<File>) FileUtils.listFiles(dir, new WildcardFileFilter("*.jar"), TrueFileFilter.INSTANCE);
		}
		return jars;
	}
	
	public static List<String> getListClassNamesFromJar(File jarName) throws IOException {
		List<String> classes = new ArrayList<String>();
		JarFile jarFile = new JarFile(jarName);
		Enumeration<JarEntry> entries = jarFile.entries();
		while (entries.hasMoreElements()) {
			JarEntry jarEntry = (JarEntry) entries.nextElement();           	 
       	 	if(jarEntry.getName().contains(".class")) {
       	 		classes.add(stripClassName(normalize(jarEntry.getName())));
       	 	}
		}
		return classes;
	}
	
	public static String normalize(String className) {
        return className.replace('/', '.');
    }
	
    public static String stripClassName(String className) {
    	return className.substring(0, className.length() - 6);
    }
}
