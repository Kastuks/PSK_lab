package vu.lt.services;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.usecases.UpdateStudentDetails;
import vu.lt.persistence.StudentsDAO;
import vu.lt.entities.Student;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.io.Serializable;
import java.util.Random;
import javax.inject.Named;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;


@SessionScoped
@Named
public class YearNumberGenerator implements Serializable {

//    private Student student;
//    Integer number = 1;
    @Inject
    StudentsDAO studentsDAO;
    private transient CompletableFuture<Integer> randomNumberGenerationTask = null;
    UpdateStudentDetails updateDetails;
    Integer studentId = 0;

    @PostConstruct
    private void init() {
//        System.out.println("UpdateStudentDetails INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.studentId = Integer.parseInt(requestParameters.get("studentId"));
    }

    @LoggedInvocation
    @Transactional
    public String generateYearNumber() {
//        Map<String, String> requestParameters =
//                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        Integer studentId = Integer.parseInt(requestParameters.get("studentId"));
//        System.out.println(studentId);
        randomNumberGenerationTask = CompletableFuture.supplyAsync(() -> {

        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        Integer generatedYearNumber = new Random().nextInt(4);
        if (generatedYearNumber == 0)
        {
            generatedYearNumber = 1;
        }
        System.out.println("Number generated = " + generatedYearNumber);


//        number = generatedYearNumber;
        return generatedYearNumber;
    });
//        Student student = studentsDAO.getStudentById(studentId);
//        student.setYear(number);
//
//        studentsDAO.update(student);
        return  "/studentDetails.xhtml?faces-redirect=true&studentId=" + studentId; //Integer.parseInt(requestParameters.get("studentId"));
//        return "index?faces-redirect=true";
    }

    public boolean isYearNumberGeneratorRunning() {
        return randomNumberGenerationTask != null && !randomNumberGenerationTask.isDone();
    }

    @Transactional
    public String getYearNumberGeneratorStatus() throws ExecutionException, InterruptedException {
            if (randomNumberGenerationTask == null) {
            return null;
            } else if (isYearNumberGeneratorRunning()) {
            return "Year number generation in progress";
            }

            Student student = studentsDAO.getStudentById(studentId);
            student.setYear(randomNumberGenerationTask.get());
//
//            studentsDAO.update(student);
            try{

                studentsDAO.update(student);
            } catch (OptimisticLockException e) {
            e.printStackTrace();
            return "/studentDetails.xhtml?faces-redirect=true&studentId=" + student.getId() + "&error=optimistic-lock-exception";
            }
            return "Year number generated: " + randomNumberGenerationTask.get();
            }
}