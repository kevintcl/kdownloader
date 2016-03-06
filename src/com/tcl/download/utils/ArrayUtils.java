package com.tcl.download.utils;
import android.util.ArraySet;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

/**
 * ArrayUtils contains some methods that you can call to find out
 * the most efficient increments by which to grow arrays.
 */
public class ArrayUtils {
    private static final int CACHE_SIZE = 73;
    private static Object[] sCache = new Object[CACHE_SIZE];

    private ArrayUtils() { /* cannot be instantiated */ }




    /**
     * Checks if given array is null or has zero elements.
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Checks if given array is null or has zero elements.
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Checks if given array is null or has zero elements.
     */
    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

}
