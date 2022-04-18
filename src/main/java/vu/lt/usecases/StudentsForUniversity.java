package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Student;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.StudentsDAO;
import vu.lt.entities.University;
import vu.lt.persistence.UniversitiesDAO;

@Model
public class StudentsForUniversity implements Serializable {

    @Inject
    private UniversitiesDAO universitiesDAO; // UniversitiesDAO?

    @Inject
    private StudentsDAO studentsDAO;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @Getter @Setter
    private University university;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer universityId = Integer.parseInt(requestParameters.get("universityId"));
        this.university = universitiesDAO.findOne(universityId);
    }

    @Transactional
    @LoggedInvocation
    public String createStudent() {
        studentToCreate.setUniversity(this.university);
        studentsDAO.persist(studentToCreate);
        return "/students.xhtml?faces-redirect=true&universityId=" + this.university.getId();
    }

}