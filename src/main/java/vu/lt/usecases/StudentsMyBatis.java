package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.StudentMapper;
import vu.lt.mybatis.dao.SubjectMapper;
import vu.lt.mybatis.dao.SubjectsMapper;
import vu.lt.mybatis.model.Student;
import vu.lt.mybatis.model.Subject;
import vu.lt.mybatis.model.Subjects;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Model
public class StudentsMyBatis {
    @Inject
    private StudentMapper studentMapper;

    @Inject
    private SubjectsMapper subjectsMapper;

    @Inject
    private SubjectMapper subjectMapper;

    @Getter
    private List<Subjects> allSubjects;

    @Getter
    private List<Subject> selectedSubjects = new ArrayList<Subject>();

    @Getter
    private Student student;

    @Getter
    private List<Student> allStudents;

    @Getter @Setter
    private Student studentToCreate = new Student();

    @PostConstruct
    public void init() {
        this.loadAllTeams();
        this.loadAllSubjects();
        System.out.println("StudentsMyBatis INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
        this.student = studentMapper.selectByPrimaryKey(studentId);
        for (Subjects subjects : allSubjects) {
            if (subjects.getStudentId() == studentId)
            {
                this.selectedSubjects.add(subjectMapper.selectByPrimaryKey(subjects.getSubjectId()));
            }
        }
//        this.loadStudent();
    }

//    private void loadStudent() {this.student = studentMapper.}
    private void loadAllSubjects() {this.allSubjects =  subjectsMapper.selectAll(); }
    private void loadAllTeams() {
        this.allStudents = studentMapper.selectAll();
    }

    @Transactional
    public String createStudent() {
        studentMapper.insert(studentToCreate);
        return "/myBatis/studentDets?faces-redirect=true";
    }

}
