package pt.iade.unimage.controllers;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.unimage.models.Student;
import pt.iade.unimage.models.StudentRepository;

@RestController
@RequestMapping(path="/api/students")
public class StudentController {
    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    
    @RequestMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents(){
        logger.info("Sending all students");
        return StudentRepository.getStudents();
    }

    @RequestMapping(path="{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("number") int number) throws NotFoundException{
        logger.info("Sending the student with the number "+number);
        Student student = StudentRepository.getStudent(number);
        if (student != null) return student;
        else throw new NotFoundException(""+number, "Student", "number");
    }

    @RequestMapping(path="{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean deleteStudent(@PathVariable("number") int number){
        logger.info("Deleting the student with the number "+number);
        if(StudentRepository.deleteStudent(number))
            return new Response(number+" was deleted.",null);
        else return new Response(number+" not found.",null);
    }

    @RequestMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student addStudent(@PathVariable("student") Student student){
        logger.info("Adding the student "+student);
        return StudentRepository.addStudent(student);
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public class NotFoundException extends RuntimeException{
        private static final long serialVersionUID = 5405519104069955535L;
        public NotFoundException(String id, String elemType, String idName){
            super(elemType+" with "+idName+" "+id+" not found.");
        }
    }
}