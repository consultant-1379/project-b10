package com.example.cloudnative;

import com.example.cloudnative.view.GraphView;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class GraphViewListMatcher extends TypeSafeMatcher<List<GraphView>> {

    private float[] xCoordinateValues;

    public GraphViewListMatcher(float[] xCoordinateValues){this.xCoordinateValues = xCoordinateValues;}

    @Override
    protected boolean matchesSafely(List<GraphView> graphViews) {

        int length = graphViews.size();

        for(int i=0;i<length;i++){
            if(graphViews.get(i).getxCoordinate() != xCoordinateValues[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public void describeTo(Description description) {
    }
}
