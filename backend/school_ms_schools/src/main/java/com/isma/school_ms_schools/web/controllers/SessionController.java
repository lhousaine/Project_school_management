package com.isma.school_ms_schools.web.controllers;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.DateFormatException;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SessionDTO;
import com.isma.school_ms_schools.service.iservices.ISessionService;
import io.swagger.annotations.Api;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(path ="sessions")
@Api(value="Session API", description="Operations for Courses")
public class SessionController {

    private final ISessionService sessionService;

    public SessionController(ISessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SessionDTO> getAll() throws NoDataFoundException {
        return sessionService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public SessionDTO getById(@PathVariable Long id) throws NoDataFoundException {
        return sessionService.getById(id);
    }

    @PostMapping(path = "/", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public SessionDTO create(@RequestBody SessionDTO classroomDTO) throws DataAlreadyUsed, NoDataFoundException, DateFormatException, ParseException {
        return sessionService.create(classroomDTO);
    }

    @PatchMapping(path = "/{id}")
    public boolean update(@PathVariable Long id,@RequestBody SessionDTO sessionDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        return sessionService.update(id, sessionDTO);
    }

    @DeleteMapping(path = "/{id}")
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return sessionService.delete(id);
    }

    @GetMapping(path = "/session-by-subject",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SessionDTO> findCourseBySubjectName(@RequestParam("subjectName") String subjectName) throws NoDataFoundException{
        return sessionService.findSessionsBySubjectName(subjectName);
    }

    @GetMapping(path = "/session-by-classroom",produces = {MediaType.APPLICATION_JSON_VALUE})
    public SessionDTO findCourseByClassroom_Code(@RequestParam("classroomCode") String classroomCode) throws NoDataFoundException{
        return sessionService.findSessionByClassroom_Code(classroomCode);
    }
    @GetMapping(path = "/sessions-by-teacher",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<SessionDTO> findSessionsByTeacher_Id(Long idTeacher) throws NoDataFoundException {
        return sessionService.findSessionsByTeacher_Id(idTeacher);
    }
}
