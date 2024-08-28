package com.example.cloudnative;

public class MyMatchers {

    private MyMatchers(){}

    public static GraphViewListMatcher matchTheXCoordinates(float[] xValues){
        return new GraphViewListMatcher(xValues);
    }
}
