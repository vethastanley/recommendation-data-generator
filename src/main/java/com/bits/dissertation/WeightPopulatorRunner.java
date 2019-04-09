package com.bits.dissertation;

import com.bits.dissertation.generators.ActionWeightGenerator;

public class WeightPopulatorRunner {
    public static void main(String[] args) {
        System.out.println("About to populate the weights");
        ActionWeightGenerator.INSTANCE.generate();
        System.out.println("All the weights populated");
    }
}
