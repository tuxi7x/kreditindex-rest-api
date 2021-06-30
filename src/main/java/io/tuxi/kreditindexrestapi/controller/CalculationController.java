package io.tuxi.kreditindexrestapi.controller;

import io.tuxi.kreditindexrestapi.model.CalculationResult;
import io.tuxi.kreditindexrestapi.model.SubjectWrapper;
import io.tuxi.kreditindexrestapi.service.CalculationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/calc")
@CrossOrigin(origins = {"http://localhost:3000"})
public class CalculationController {

    private final CalculationService calculationService;

    @Autowired
    public CalculationController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CalculationResult calculate(@RequestBody SubjectWrapper subjects) {
        CalculationResult result = new CalculationResult();
        if (subjects.getSubjects() != null && subjects.getSubjects().size() > 0) {
            double creditIndex = calculationService.calculateCreditIndex(subjects.getSubjects());
            double average = calculationService.calculateAverage(subjects.getSubjects());
            int numberOfCredits = calculationService.calculateNumberOfCredits(subjects.getSubjects());
            double weightedAverage = calculationService.calculateWeightedAverage(subjects.getSubjects());
            boolean isCatalogFree = calculationService.checkIsCatalogFree(subjects.getSubjects());
            boolean isStateFunded = calculationService.checkIsStateFunded(subjects.getSubjects());
            int money = calculationService.calculateMoney(subjects.getSubjects());
            result.setCreditIndex(creditIndex);
            result.setAverage(average);
            result.setNumberOfCredits(numberOfCredits);
            result.setWeightedAverage(weightedAverage);
            result.setCatalogFree(isCatalogFree);
            result.setStateFunded(isStateFunded);
            result.setMoney(money);

            return result;
        }

        return new CalculationResult();

    }
}
