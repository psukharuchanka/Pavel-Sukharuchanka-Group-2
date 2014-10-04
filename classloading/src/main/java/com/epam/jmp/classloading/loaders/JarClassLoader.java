/**
 * 
 */
package com.epam.jmp.classloading.loaders;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.log4j.Logger;

/**
 * @author SIVASHKOFF
 *
 */
public class JarClassLoader extends ClassLoader {
	static final Logger logger = Logger.getLogger(JarClassLoader.class);
		
	private static HashMap<String, Class> cache = new HashMap<String, Class>(); 
	
	private String dirPath = "d:/test";
	
	public JarClassLoader(String path) {
		this(JarClassLoader.class.getClassLoader());
		this.dirPath = path;
	}
	
	public JarClassLoader(ClassLoader parent) {
		super(parent);
	}
	
	protected Class findClass(final String name) throws ClassNotFoundException {
		byte[] classByte;
		Class result = null;
		
		if (cache.containsKey(name)) {
			logger.info("Class " + name + " found in cache");
			result = cache.get(name);
			return result;
		}
		
		result = findLoadedClass(name);
		if (result != null) {
            logger.info("Class " + name + " found in cache");
            return result;
        }
		
		try {
			List<File> jars = getListJars(dirPath);
			for(File jar : jars) {
				JarFile jarFile = new JarFile(jar);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
	            	JarEntry jarEntry = (JarEntry) entries.nextElement();           	 
	            	if(jarEntry.getName().contains(".class")) {
	            		String className = stripClassName(normalize(jarEntry.getName()));
	            		if(name.equalsIgnoreCase(className)) {
	            			byte[] classData = loadClassData(jarFile, jarEntry);
	            			if(classData != null ) {
				            	result = defineClass(name, classData, 0, classData.length);
				            	cache.put(name, result);
				            	logger.info("class " + name + " is defined");
				            	return result;
			            	}
	            		}
	            	}
	            }
			}
		} catch(IOException ioe) {
			logger.error("Can't define class ", ioe);
		}
		return null;
	}
	
	private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry) throws IOException {
        long size = jarEntry.getSize();     
        if (size == -1 || size == 0)
            return null;
        byte[] data = new byte[(int)size];
        InputStream in = jarFile.getInputStream(jarEntry);
        in.read(data);

        return data;
    }
	
	private List<File> getListJars(String dirPath) {
		List<File> jars = null;
		File dir = new File(dirPath);
		if(dir.exists() && dir.isDirectory()) {
			jars = (List<File>) FileUtils.listFiles(dir, new WildcardFileFilter("*.jar"), TrueFileFilter.INSTANCE);
		}
		return jars;
	}

	private String normalize(String className) {
        return className.replace('/', '.');
    }
	
    private String stripClassName(String className) {
    	return className.substring(0, className.length() - 6);
    }
        
}
