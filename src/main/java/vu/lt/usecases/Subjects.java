package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Subject;
import vu.lt.persistence.SubjectsDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Subjects {

    @Inject
    private SubjectsDAO subjectsDAO;

    @Getter @Setter
    private Subject subjectToCreate = new Subject();

    @Getter
    private List<Subject> allSubjects;

    @PostConstruct
    public void init(){
        loadAllSubjects();
    }

    @Transactional
    public String createSubject(){
        this.subjectsDAO.persist(subjectToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllSubjects(){
        this.allSubjects = subjectsDAO.loadAll();
    }
}