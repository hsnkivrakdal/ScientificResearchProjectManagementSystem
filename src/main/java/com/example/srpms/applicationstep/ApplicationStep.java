package com.example.srpms.applicationstep;

public enum ApplicationStep {
    DESCRIPTION,
    COORDINATOR,
    PROJECT_INFORMATION,
    ACTIVITY_AREAS,
    KEYWORDS,
    TECHNOLOGY_LEVEL,
    PERSONNEL,
    FILES,
    MACHINERY,
    SUBMIT;


    public ApplicationStep next() {
        int ord = this.ordinal();
        ApplicationStep[] steps = ApplicationStep.values();
        return ord < steps.length ? steps[ord + 1] : SUBMIT;
    }

}
