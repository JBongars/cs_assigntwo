package com.cs.assigntwo.dependencies;


public class csArray {

    public static String[] swap(String[] items, int from, int to){
        String item = items[from];
        items[to] = items[from];
        items[from] = item;
        return items;
    }

    public static int[] swap(int[] items, int from, int to){
        int item = items[from];
        items[to] = items[from];
        items[from] = item;
        return items;
    }

    public static float[] swap(float[] items, int from, int to){
        float item = items[from];
        items[to] = items[from];
        items[from] = item;
        return items;
    }

    public static double[] swap(double[] items, int from, int to){
        double item = items[from];
        items[to] = items[from];
        items[from] = item;
        return items;
    }


    public static String[] sort(String[] items){
        return items;
    }

    public static int sort(int items){
        return items;
    }

    public static float sort(float items){
        return items;
    }

    public static double sort(double items){
        return items;
    }

}
