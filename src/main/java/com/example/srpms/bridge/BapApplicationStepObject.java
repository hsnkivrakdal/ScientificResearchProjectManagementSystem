package com.example.srpms.bridge;

import com.example.srpms.applicationstep.ApplicationStep;

import java.util.List;
import java.util.NoSuchElementException;

public class BapApplicationStepObject extends ApplicationStepObject {

    private final List<ApplicationStep> steps;
    private int currentIndex;

    public BapApplicationStepObject(ApplicationStep currentStep) {
        this.steps = ApplicationStep.STEPS;
        this.currentIndex = steps.indexOf(currentStep);
    }


    @Override
    public boolean hasNext() {
        return currentIndex < steps.size() - 1;
    }

    @Override
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    @Override
    public ApplicationStep next() {
        if (!hasNext()) throw new NoSuchElementException("No next step.");
        return steps.get(currentIndex + 1);
    }

    @Override
    public ApplicationStep previous() {
        if (!hasPrevious()) throw new NoSuchElementException("No previous step.");
        return steps.get(currentIndex - 1);
    }

    @Override
    public ApplicationStep current() {
        return steps.get(currentIndex);
    }
}
