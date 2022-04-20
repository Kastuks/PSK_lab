package vu.lt.mybatis.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.SubjectMapper;
import vu.lt.mybatis.model.Subject;

@Model
@Getter
@Setter
public class MyBatisSubjectController implements Serializable {
    private static final long serialVersionUID = -8485790918752453661L;

    @Inject
    private SubjectMapper subjectMapper;

    private Integer subjectToDeleteId;
    private Subject subjectToAdd = new Subject();
    private List<Subject> availableSubjects;


    @PostConstruct
    void init() {
        this.availableSubjects = subjectMapper.selectAll();
    }

    @Transactional
    public String deleteSubject() {
       subjectMapper.deleteByPrimaryKey(subjectToDeleteId);
        return "/myBatis/pirmas_lab?faces-redirect=true"; // patikrinti link
    }

    @Transactional
    public String addNewSubject() {
        subjectMapper.insert(subjectToAdd);
        return "/myBatis/pirmas_lab?faces-redirect=true"; // patikrinti link
    }
}