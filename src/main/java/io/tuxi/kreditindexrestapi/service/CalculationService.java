package io.tuxi.kreditindexrestapi.service;

import io.tuxi.kreditindexrestapi.model.Subject;

import java.util.List;

public interface CalculationService {
    double calculateCreditIndex(List<Subject> subjects);
    double calculateAverage(List<Subject> subjects);
    int calculateNumberOfCredits(List<Subject> subjects);
    double calculateWeightedAverage(List<Subject> subjects);
    int calculateMoney(List<Subject> subjects);
    boolean checkIsCatalogFree(List<Subject> subjects);
    boolean checkIsStateFunded(List<Subject> subjects);
}
