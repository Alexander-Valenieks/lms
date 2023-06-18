package com.backend.lms.controller;

import com.backend.lms.entity.Course;
import com.backend.lms.exception.CourseNotFoundException;
import com.backend.lms.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/course")
    public Course createCourse(@RequestBody Course newCourse) {
        return courseRepository.save(newCourse);
    }

    @GetMapping("/courses")
    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/course/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @PutMapping("/course/{id}")
    Course updateCourse(@RequestBody Course newCourse, @PathVariable int id){
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setDescription(newCourse.getDescription());
                    return courseRepository.save(course);
                }).orElseThrow(() -> new CourseNotFoundException(id));
    }

    @DeleteMapping("/course/{id}")
    String deleteCourse(@PathVariable int id) {
        if(!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
        return "Course with id " + id + " has been deleted successfully";
    }

}