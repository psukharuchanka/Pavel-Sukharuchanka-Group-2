package com.epam.heap;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class MemoryEater {
    static final Logger logger = Logger.getLogger(MemoryEater.class);
   
    /*
     * Object 'v' stored in heap. 
     * With each iteration his size is increased by adding new element that also stored in heap. 
     * And 'v' has references to each element in heap. 
     * That means that memory will not cleared by garbage collector and as a result we got java.lang.OutOfMemoryError: Java heap space. 
     */
    
    /* POSSIBLE FIXES :
     * 1. clear list depending on count of elements 
     * 2. remove loop;
     * 3. put v = new ArrayList() in to the loop
     */
    
    public static void main(String[] args) {
        List v = new ArrayList();
        try {
        	 while (true) {
             	byte b[] = new byte[1048576];
                 v.add(b);
                 Runtime rt = Runtime.getRuntime();

                 System.out.println("free memory: " + rt.freeMemory());
                 
                 if (v.size() > 970) {
                 	logger.info("clear list");
                 	v.clear();
                 }
                 /*
                 try {
                     Thread.sleep(250);
                 } catch (InterruptedException e) {
                 }*/
             }
        } catch (OutOfMemoryError e) {
        	logger.error("v.size = " + v.size(), e);
        }
       
    }
}
