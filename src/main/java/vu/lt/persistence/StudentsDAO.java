package vu.lt.persistence;

import vu.lt.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class StudentsDAO implements StudentsInterface{
    @Inject
    private EntityManager em;

    public void persist(Student student){

        this.em.persist(student);
    }

    public List<Student> getAllStudents() {
        return this.em.createNamedQuery("Student.findAll", Student.class).getResultList();
    }

    public Student findOne(Integer id){

        return em.find(Student.class, id);
    }

    public Student getStudentById(Integer id) {
        TypedQuery<Student> namedQuery = this.em.createNamedQuery("Student.findById", Student.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    public Student update(Student student){
        return em.merge(student);
    }

    public void updateStudent(Student studentToEdit) {
        Student studentById = getStudentById(studentToEdit.getId());
        studentToEdit.setVersion(studentById.getVersion());
        this.em.merge(studentToEdit);
    }

}