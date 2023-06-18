package com.backend.lms.exception;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(int id) {
        super("Could not find course with" + id + "id");
    }

}

