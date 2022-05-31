package vu.lt.usecases;


import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Student;
import vu.lt.entities.Subject;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.StudentsDAO;
import vu.lt.persistence.SubjectsDAO;
import vu.lt.persistence.StudentsInterface;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateStudentDetails implements Serializable {

    private Student student;

    private Student studentToEdit = new Student();

    private Subject subject;

    private Integer number;

    private List<Subject> subjectList =  new ArrayList<>();

    @Inject
    private StudentsInterface studentsDAO;

    @Inject
    private SubjectsDAO subjectsDAO;

    @PostConstruct
    private void init() {
        System.out.println("UpdateStudentDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentsDAO.findOne(studentId);
    }

    @Transactional
    @LoggedInvocation
    public String updateStudentSubjects() {
        this.subject = subjectsDAO.findOne(number);

        System.out.println(number + subject.getName());
        subjectList = student.getSubjects();

        subjectList.add(this.subject);
//        subjectList.add(student.getSubjects)
        student.setSubjects(this.subjectList);

        try{
            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "studentDetails.xhtml?studentId=" + this.student.getId();
    }
    @Transactional
    @LoggedInvocation
    public String updateStudentYear(int year) {
        this.student.setYear(year);
        try{

            studentsDAO.update(this.student);
        } catch (OptimisticLockException e) {
            e.printStackTrace();
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId() + "&error=optimistic-lock-exception";
        }
        return "studentDetails.xhtml?studentId=" + this.student.getId() + "&faces-redirect=true";
    }

    @Transactional
    public String updateStudentYear1() {
        String redirect = "studentDetails.xhtml?faces-redirect=true&studentId=" + this.student.getId();
        try {
            studentsDAO.update(student);
        } catch (OptimisticLockException e) {
            e.printStackTrace();
            return redirect + "&error=optimistic-lock-exception";
        }
        return redirect;
    }

}
