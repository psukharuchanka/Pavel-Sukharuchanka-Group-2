package com.epam.permgen;

import javassist.ClassPool;

public class PermGenTest {
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < Long.MAX_VALUE; i++) {
			generate("com.epam.permgen" + i);
		}
	}

	public static Class generate(String name) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		return pool.makeClass(name).toClass();
	}
	
}
