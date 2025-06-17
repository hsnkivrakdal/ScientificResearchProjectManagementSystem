package com.example.srpms.bridge;

import com.example.srpms.applicationstep.ApplicationStep;

import java.util.List;
import java.util.NoSuchElementException;

public class ApplicationStepIterator{
    private final List<ApplicationStep> steps;
    private int currentIndex;

    public ApplicationStepIterator(ApplicationStep currentStep) {
        this.steps = ApplicationStep.STEPS;
        this.currentIndex = steps.indexOf(currentStep);
    }

    public boolean hasNext() {
        return currentIndex < steps.size() - 1;
    }

    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    public ApplicationStep next() {
        if (!hasNext()) throw new NoSuchElementException("No next step.");
        return steps.get(currentIndex + 1);
    }

    public ApplicationStep previous() {
        if (!hasPrevious()) throw new NoSuchElementException("No previous step.");
        return steps.get(currentIndex - 1);
    }

    public ApplicationStep current() {
        return steps.get(currentIndex);
    }
}
