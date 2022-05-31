package vu.lt.rest;

import lombok.*;
import vu.lt.entities.Subject;
import vu.lt.persistence.SubjectsDAO;
import vu.lt.rest.contracts.SubjectDto;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/subjects")
public class SubjectsController {

    @Inject
    @Setter @Getter
    private SubjectsDAO subjectsDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Subject subject = subjectsDAO.findOne(id);
        if (subject == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subject.getName());
//        studentDto.setJerseyNumber(student.getJerseyNumber());
//        subjectDto.setStudents(subject.getStudents().getName());

        return Response.ok(subjectDto).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response update(
            @PathParam("id") final Integer subjectId,
            SubjectDto subjectData) {
        try {
            Subject existingSubject = subjectsDAO.findOne(subjectId);
            if (existingSubject == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            existingSubject.setName(subjectData.getName());
//            existingStudent.setJerseyNumber(studentData.getJerseyNumber());
            subjectsDAO.update(existingSubject);
            return Response.ok().build();
        } catch (OptimisticLockException ole) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
