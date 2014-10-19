package com.epam.heap;

import org.apache.log4j.Logger;

public class RefValueTest extends Object {
    static final Logger logger = Logger.getLogger(RefValueTest.class);
    /*
     * results:
     *  0 40 - 'a' pass by value, 'b' by reference. 'a' from main not changed, to 'b' set value 40, because 'b' from main and 'b' from
     *  method 'f' reference to the same memory in heap.
     *  0 40 - the same, because in method 'g' the var 'b' is reference to new area on memory in heap, but when method is finished
     *  frame for 'g' is removed.
     *  42 40 - to 'a' returned new value, to 'b' used value from 'main', because 'b' from g1 is lost in method f1...
     * 
     * */
    public static void main(String[] args) {
        int a = 0;
        int[] b = {20};
        f(a, b);
        logger.info(a + " " + b[0]);
        g(a, b);
        logger.info(a + " " + b[0]);
        a = f1(a, g1(a, b));
        logger.info(a + " " + b[0]);
    }

    private static void f(int a, int[] b) {
        a += 30;
        b[0] = 40;
    }

    private static void g(int a, int[] b) {
        a = 50;
        b = new int[]{60};
    }
    
    private static int f1(int a, int[] b) {
        a += 30;
        b[0] = 40;
        return 42;
    }

    private static int[] g1(int a, int[] b) {
        a = 50;
        b = new int[]{60};
        return b;
    }
}


