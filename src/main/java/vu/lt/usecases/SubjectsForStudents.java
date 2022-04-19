package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Student;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.StudentsDAO;
import vu.lt.entities.Subject;
import vu.lt.persistence.SubjectsDAO;

@Model
public class SubjectsForStudents implements Serializable {

    @Inject
    private StudentsDAO studentsDAO; // UniversitiesDAO?

    @Inject
    private SubjectsDAO subjectsDAO;

    @Getter @Setter
    private Subject subjectToCreate = new Subject();

    @Getter @Setter
    private Student student;

    @Getter @Setter
    private List<Student> students;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    @LoggedInvocation
    public String createSubject() {
        subjectToCreate.setStudents(this.students);
        subjectsDAO.persist(subjectToCreate);
        return "/subjects.xhtml?faces-redirect=true&studentId=" + this.student.getId();
    }

}