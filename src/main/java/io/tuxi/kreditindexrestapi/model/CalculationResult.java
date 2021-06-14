package io.tuxi.kreditindexrestapi.model;

import lombok.Data;

@Data
public class CalculationResult {
    private double creditIndex;
    private double average;
    private int numberOfCredits;
    private double weightedAverage;
    private boolean isCatalogFree;
    private boolean isStateFunded;
    private int money;
}
