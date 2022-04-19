package vu.lt.persistence;

import vu.lt.entities.Subject;
import vu.lt.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class SubjectsDAO {
    @Inject
    private EntityManager em;

    public void persist(Subject subject){

        this.em.persist(subject);
    }

    public Subject findOne(Integer id){

        return em.find(Subject.class, id);
    }

    public Subject findName(String name){

        return em.find(Subject.class, name);
    }

    public Subject update(Subject subject){
        return em.merge(subject);
    }
    public List<Subject> loadAll() {
        return em.createNamedQuery("Subject.findAll", Subject.class).getResultList();
    }
}