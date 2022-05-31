package vu.lt.persistence;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import vu.lt.entities.Student;

//Specialization happens at development time as well as at runtime.
// If you declare that one bean specializes another, it extends the other bean class,
// and at runtime the specialized bean completely replaces the other bean.
//Specialization has a function similar to that of alternatives,
// in that it allows you to substitute one bean for another.
@Specializes
@ApplicationScoped
public class NewStudentsDAO extends StudentsDAO{

    public void persist(Student student) {
        super.persist(student);
    }

    public List<Student> getAllStudents() {
        return super.getAllStudents();
    }

    public Student getStudentById(Integer id) {
        return super.getStudentById(id);
    }

    public Student update(Student student){
        System.out.println("Sita klase veikia");
        return super.update(student);
    }

}
