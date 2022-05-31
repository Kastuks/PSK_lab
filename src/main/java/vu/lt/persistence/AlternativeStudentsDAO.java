package vu.lt.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import vu.lt.entities.Student;

//For example, you might want to create a full version
// of a bean and also a simpler version that you use
// only for certain kinds of testing.
@Alternative
@ApplicationScoped
public class AlternativeStudentsDAO implements StudentsInterface {
    @Inject
    private EntityManager em;

    public void persist(Student student) {
        this.em.persist(student);
    }

    public Student getStudentById(Integer id) { // ar tikrai Long id?
        TypedQuery<Student> namedQuery = this.em.createNamedQuery("Student.findById", Student.class);
        namedQuery.setParameter("id", id);
        return namedQuery.getSingleResult();
    }

    public Student update(Student studentToEdit) {
        Student studentById = getStudentById(studentToEdit.getId());
        studentToEdit.setVersion(studentById.getVersion());
        this.em.merge(studentToEdit);
        return this.em.merge(studentToEdit);
    }

    public Student findOne(Integer id){

        return em.find(Student.class, id);
    }


}
