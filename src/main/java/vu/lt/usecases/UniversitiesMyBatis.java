package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.UniversityMapper;
import vu.lt.mybatis.model.University;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class UniversitiesMyBatis {
    @Inject
    private UniversityMapper universityMapper;

    @Getter
    private List<University> allUniversities;

    @Getter @Setter
    private University universityToCreate = new University();

    @PostConstruct
    public void init() {
        this.loadAllTeams();
    }

    private void loadAllTeams() {
        this.allUniversities = universityMapper.selectAll();
    }

    @Transactional
    public String createUniversity() {
        universityMapper.insert(universityToCreate);
        return "/myBatis/universities?faces-redirect=true";
    }

}
