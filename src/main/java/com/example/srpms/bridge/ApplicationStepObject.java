package com.example.srpms.bridge;

import com.example.srpms.applicationstep.ApplicationStep;

import java.util.NoSuchElementException;

public abstract class ApplicationStepObject {
    public abstract boolean hasNext();
    public abstract boolean hasPrevious();
    public abstract ApplicationStep next();
    public abstract ApplicationStep previous();
    public abstract ApplicationStep current();
}
