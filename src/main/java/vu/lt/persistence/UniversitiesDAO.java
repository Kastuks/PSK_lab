package vu.lt.persistence;

import vu.lt.entities.University;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class UniversitiesDAO {

    @Inject
    private EntityManager em;

    public List<University> loadAll() {
        return em.createNamedQuery("University.findAll", University.class).getResultList();
    }

    public void setEm(EntityManager em) {

        this.em = em;
    }

    public void persist(University university){

        this.em.persist(university);
    }

    public University findOne(Integer id) {
        return em.find(University.class, id); }
}