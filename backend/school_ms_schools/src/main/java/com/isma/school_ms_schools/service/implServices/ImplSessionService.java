package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Dto.SessionDTO;
import com.isma.school_ms_schools.data.Entities.Session;
import com.isma.school_ms_schools.data.Repositories.SessionRepository;
import com.isma.school_ms_schools.service.iservices.ISessionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ImplSessionService implements ISessionService {
    private final SessionRepository sessionRepository;
    private final IConverter<Session, SessionDTO> sessionConverter;

    public ImplSessionService(SessionRepository sessionRepository, @Qualifier("ImplSessionConverter") IConverter<Session, SessionDTO> sessionConverter) {
        this.sessionRepository = sessionRepository;
        this.sessionConverter = sessionConverter;
    }


    @Override
    public List<SessionDTO> getAll() throws NoDataFoundException {
        List<Session> sessions = sessionRepository.findAll();
        if (sessions ==null)
            throw new NoDataFoundException("There is no Course");
        return sessionConverter.convertListToListDto(sessions);
    }

    @Override
    public SessionDTO getById(Long idCourse) throws NoDataFoundException {
        Session session = sessionRepository.getOne(idCourse);
        if (session ==null)
            throw new NoDataFoundException("there is no course identified by "+idCourse);
        return sessionConverter.convertToDto(session);
    }

    @Override
    public SessionDTO create(SessionDTO sessionDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        Session session =sessionConverter.convertToEntity(sessionDTO);
        session.setStarted_At(new Date());
        session = sessionRepository.save(session);
        return sessionConverter.convertToDto(session);
    }

    @Override
    public boolean update(Long idCourse, SessionDTO sessionDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        Session session =sessionConverter.convertToEntity(sessionDTO);
        Session c1= sessionRepository.getOne(idCourse);
        if (c1==null) {
            throw new NoDataFoundException("No Course identified by " + idCourse);
        }
        c1.setClassroom(session.getClassroom());
        c1.setStarted_At(session.getStarted_At());
        c1.setDuration(session.getDuration());
        try {
            sessionRepository.save(c1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idCourse) throws NoDataFoundException {
        Session session = sessionRepository.findById(idCourse).get();
        if (session ==null) {
            throw new NoDataFoundException("No Course identified by " + idCourse);
        }
        try{
            sessionRepository.deleteById(idCourse);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public SessionDTO findSessionByClassroom_Code(String code) throws NoDataFoundException {
        Session session = sessionRepository.findSessionsByClassroom_Code(code);
        if (session ==null)
            throw new NoDataFoundException("there is no programmed in the classroom identified by "+code);
        return sessionConverter.convertToDto(session);
    }

    @Override
    public List<SessionDTO> findSessionsByTeacher_Id(Long idTeacher) throws NoDataFoundException {
        List<Session> sessions = sessionRepository.findSessionsByTeacher_Id(idTeacher);
        if (sessions ==null)
            throw new NoDataFoundException("No Session for the teacher identified by " +idTeacher);
        return sessionConverter.convertListToListDto(sessions);
    }

    @Override
    public List<SessionDTO> findSessionsBySubjectName(String subjectName) throws NoDataFoundException {
        List<Session> sessions = sessionRepository.findSessionsBySubjectName(subjectName);
        if (sessions ==null)
            throw new NoDataFoundException("No programmed Session for the subject named" +subjectName);
        return sessionConverter.convertListToListDto(sessions);
    }

}
