package io.tuxi.kreditindexrestapi.service;

import io.tuxi.kreditindexrestapi.model.Subject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CalculationServiceImpl implements CalculationService{
    @Override
    public double calculateCreditIndex(List<Subject> subjects) {
        double productOfCreditsAndMarks = subjects.stream().mapToInt((s) -> s.getCredit() * s.getMark()).reduce(0, (acc, curr) -> acc += curr);
        return productOfCreditsAndMarks / 30;
    }

    @Override
    public double calculateAverage(List<Subject> subjects) {
        double sumOfMarks = subjects.stream().mapToInt(Subject::getMark).reduce(0, (acc, cur) -> acc += cur);
        double numOfSubjects = subjects.size();
        return sumOfMarks / numOfSubjects;
    }

    @Override
    public int calculateNumberOfCredits(List<Subject> subjects) {
        return subjects.stream().mapToInt(Subject::getCredit).reduce(0, (acc, curr) -> acc += curr);
    }

    @Override
    public double calculateWeightedAverage(List<Subject> subjects) {
        double weights = calculateNumberOfCredits(subjects);
        double weightsXMarks = subjects.stream().mapToInt(s -> s.getCredit() * s.getMark()).reduce(0, (acc, cur) -> acc += cur);
        return weightsXMarks / weights;
    }

    @Override
    public int calculateMoney(List<Subject> subjects) {
        double creditIndex = calculateCreditIndex(subjects);
        if (creditIndex < 4.00) {
            return 0;
        }
        try {
            Optional<String> money = Files.lines(Path.of("src/main/resources/static/export.csv")).filter(e -> {
              String ci = e.split(",")[0];
              double roundOff = Math.round(creditIndex*100)/100D;
              String rounded = String.format("%.2f",roundOff);
              return ci.equals(rounded);
            }).findFirst();



            return money.map(s -> Integer.parseInt(s.split(",")[1])).orElse(0);
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return 0;
    }

    @Override
    public boolean checkIsCatalogFree(List<Subject> subjects) {
        double creditIndex = calculateCreditIndex(subjects);
        return creditIndex >= 4.00;
    }

    @Override
    public boolean checkIsStateFunded(List<Subject> subjects) {
        double weightedAverage = calculateWeightedAverage(subjects);
        return weightedAverage >= 3.00;
    }
}
