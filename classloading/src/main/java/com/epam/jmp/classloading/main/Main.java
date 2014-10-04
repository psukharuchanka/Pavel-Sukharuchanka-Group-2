/**
 * 
 */
package com.epam.jmp.classloading.main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.epam.jmp.classloading.loaders.JarClassLoader;
import com.epam.jmp.classloading.util.Util;

/**
 * @author SIVASHKOFF
 *
 */
public class Main {
	static final Logger logger = Logger.getLogger(Main.class); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		int choice = 0;
		Scanner scanner = new Scanner(System.in);
		
		try {
			do {
				//System.out.println("[1] load functionality");
				//System.out.println("[2] exit");
				logger.info("[1] load functionality");
				logger.info("[2] exit");
				choice = scanner.nextInt();
				switch (choice) {
					case 1:
						String dirToJars = "d:/test/";
						logger.info("enter path to dir with jar : ");
						dirToJars = scanner.next();
						
						ClassLoader loader = new JarClassLoader(dirToJars);						
						List<File> jars = Util.getListJars(dirToJars);
						for(File jar : jars) {
							List<String> listClassNames = Util.getListClassNamesFromJar(jar);
							for(String className : listClassNames) {
								Class clazz = Class.forName(className, true, loader);
								if(clazz != null ) {
									Object obj = clazz.newInstance();
									logger.info("instance : " + obj.toString());
									logger.info(clazz.getMethod("demo").invoke(obj));
								}
							}
						}
						break;
					case 2:
						//System.out.println("----------------------");
						logger.info("----------------------");
						break;			
				}
			} while(choice != 2);
		} catch(Exception e) {
			logger.error("Error due processing...", e);
		}
	}
		
}
