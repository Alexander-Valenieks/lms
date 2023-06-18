# lms-backend
Sda final project - **Learning Management System**
This repository containts back-end development of lms.

**Project structure:**
com.backend.lms
  - configuration
    - SecurityConfig
  - controller
    - CourseController
    - LessonController
    - UserController
  - dto
    - LoginDTO
    - UserDTO
  - entity
    - Course
    - Lesson
    - User
  - exception
    - CourseNotFoundException
    - LessonNotFoundException
    - UserNotFoundException   
  - payload
    - LoginMessage
  - service
    - UserImpl
    - UserService
    
**Usage:**

1. Download repository 

2. Change/add configurations for database connection in resources/application properties:
//
_spring.datasource.url=jdbc:mysql://localhost:3306/lms_final?createDatabaseIfNotExist=true 
spring.datasource.username=root
spring.datasource.password=12345678_
//

3. Run LmsApplication.

