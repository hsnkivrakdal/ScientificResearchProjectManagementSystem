package com.example.srpms.bridge;

import com.example.srpms.applicationstep.ApplicationStep;

import java.util.NoSuchElementException;

public class BapApplicationStepBase {
    public ApplicationStepObject AppStepObject;

    public boolean hasNext() {
        return AppStepObject.hasNext();
    }

    public boolean hasPrevious() {
        return AppStepObject.hasPrevious();
    }

    public ApplicationStep next() {
        return AppStepObject.next();
    }

    public ApplicationStep previous() {
         return AppStepObject.previous();
    }

    public ApplicationStep current() {
        return AppStepObject.current();
    }

}
