package vu.lt.persistence;

import vu.lt.entities.Student;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class StudentsDAO {
    @Inject
    private EntityManager em;

    public void persist(Student student){

        this.em.persist(student);
    }

    public Student findOne(Integer id){

        return em.find(Student.class, id);
    }

    public Student update(Student student){
        return em.merge(student);
    }
}