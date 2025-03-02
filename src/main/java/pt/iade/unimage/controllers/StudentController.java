package pt.iade.unimage.controllers;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iade.unimage.models.Student;
import pt.iade.unimage.models.StudentRepository;
import pt.iade.unimage.models.exceptions.NotFoundException;

@RestController
@RequestMapping(path="/api/students")
public class StudentController {
    private Logger logger = LoggerFactory.getLogger(StudentController.class);
    
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents(){
        logger.info("Sending all students");
        return StudentRepository.getStudents();
    }

    @GetMapping(path="{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudent(@PathVariable("number") int number) throws NotFoundException{
        logger.info("Sending the student with the number "+number);
        Student student = StudentRepository.getStudent(number);
        if (student != null) return student;
        else throw new NotFoundException(""+number, "Student", "number");
    }

    @DeleteMapping(path="{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteStudent(@PathVariable("number") int number) {
        logger.info("Deleting the student with the number "+number);
        if(StudentRepository.deleteStudent(number))
            return new Response(number+" was deleted.",null);
        else return new Response(number+" not found.",null);
    }

    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student addStudent(@PathVariable("student") Student student){
        logger.info("Adding the student "+student);
        return StudentRepository.addStudent(student);
    }
}