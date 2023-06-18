package com.backend.lms.controller;

import com.backend.lms.entity.Lesson;
import com.backend.lms.exception.LessonNotFoundException;
import com.backend.lms.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/")
public class LessonController {
    @Autowired
    private LessonRepository lessonRepository;

    @PostMapping("/lesson")
    Lesson createLesson(@RequestBody Lesson newLesson) {
        return lessonRepository.save(newLesson);
    }

    @GetMapping("/lessons")
    public Iterable<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @GetMapping("/lesson/{id}")
    public Lesson getLessonById(@PathVariable int id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new LessonNotFoundException(id));
    }

    @PutMapping("/lesson/{id}")
    Lesson updateLesson(@RequestBody Lesson newLesson, @PathVariable int id){
        return lessonRepository.findById(id)
                .map(lesson -> {
//                    lesson.setCourseId(newLesson.getCourseId());
                    lesson.setName(newLesson.getName());
                    lesson.setContent(newLesson.getContent());
                    return lessonRepository.save(lesson);
                }).orElseThrow(() -> new LessonNotFoundException(id));
    }

    @DeleteMapping("/lesson/{id}")
    String deleteLesson(@PathVariable int id) {
        if(!lessonRepository.existsById(id)) {
            throw new LessonNotFoundException(id);
        }
        lessonRepository.deleteById(id);
        return "Lecture with id " + id + " has been deleted successfully";
    }


}
