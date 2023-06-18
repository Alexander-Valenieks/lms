package com.backend.lms.exception;

public class LessonNotFoundException extends RuntimeException{
    public LessonNotFoundException(int id) {
        super("Could not find lesson with" + id + "id");
    }

}
