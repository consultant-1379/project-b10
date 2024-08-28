package com.example.cloudnative.service;

public enum MainStages {
    WATERFALL(2f), BETWEEN_WATERFALL_AGILE(2.5f), AGILE(3f), CLOUD_NATIVE(4f);

    private float yAxisValue;

    MainStages(float yAxisValue){
        this.yAxisValue = yAxisValue;
    }

    public float getyAxisValue() {
        return yAxisValue;
    }
}
