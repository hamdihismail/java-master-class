package com.hamdi.utility;

import java.util.Arrays;

public class ArrayHelper {
    //This method is used to make Arrays more dynamic, by creating a new array of double the size if the previous array has been filled.
    public <T>T[] add(T[] arr, T val, int lastIndex){
        T[] result;
        if((arr.length-1) <= lastIndex){
            result = Arrays.copyOf(arr,arr.length*2);
        } else{
            result = arr;
        }
        result[lastIndex] = val;
        return result;
    }
}
