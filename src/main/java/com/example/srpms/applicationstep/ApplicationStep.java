package com.example.srpms.applicationstep;

import java.util.List;

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



    public static final List<ApplicationStep> STEPS = List.of(
            DESCRIPTION,
            COORDINATOR,
            PROJECT_INFORMATION,
            ACTIVITY_AREAS,
            KEYWORDS,
            TECHNOLOGY_LEVEL,
            PERSONNEL,
            FILES,
            MACHINERY,
            SUBMIT
    );
}
