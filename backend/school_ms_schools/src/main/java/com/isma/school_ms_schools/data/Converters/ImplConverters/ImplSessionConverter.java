package com.isma.school_ms_schools.data.Converters.ImplConverters;

import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.SessionDTO;
import com.isma.school_ms_schools.data.Entities.Session;
import com.isma.school_ms_schools.data.Repositories.ClassroomRepository;
import com.isma.school_ms_schools.data.Repositories.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.isma.school_ms_schools.core.params.GlobalParams.FORMATTER;

@Component
@Qualifier("ImplSessionConverter")
public class ImplSessionConverter implements IConverter<Session, SessionDTO>{
    private ModelMapper modelMapper;
    private final ClassroomRepository classroomRepository;
    private final SubjectRepository subjectRepository;

    public ImplSessionConverter(ModelMapper modelMapper, ClassroomRepository classroomRepository, SubjectRepository subjectRepository) {
        this.modelMapper = modelMapper;
        this.classroomRepository = classroomRepository;
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SessionDTO convertToDto(Session session) {
        SessionDTO sessionDTO =modelMapper.map(session, SessionDTO.class);
        sessionDTO.setCodeClassroom(session.getClassroom().getCode());
        sessionDTO.setSubjectName(session.getSubject().getName());
        sessionDTO.setTeacherName(session.getTeacher().getFirstName());
        sessionDTO.setStarted_At(FORMATTER.format(session.getStarted_At()));
        return sessionDTO;
    }

    @Override
    public List<SessionDTO> convertListToListDto(List<Session> sessions) {
        List<SessionDTO> sessionDTOS =new ArrayList<>();
        for (Session session : sessions){
            sessionDTOS.add(this.convertToDto(session));
        }
        return sessionDTOS;
    }

    @Override
    public Session convertToEntity(SessionDTO sessionDTO) throws NoDataFoundException, ParseException {
        Session session =modelMapper.map(sessionDTO, Session.class);
        session.setStarted_At(FORMATTER.parse(sessionDTO.getStarted_At()));
        session.setClassroom(classroomRepository.getOne(sessionDTO.getCodeClassroom()));
        session.setSubject(subjectRepository.findSubjectByName(sessionDTO.getSubjectName()));
        return session;
    }

    @Override
    public List<Session> convertListDtoToListEntity(List<SessionDTO> sessionDTOS) {
        List<Session> sessions =new ArrayList<>();
        Session session;
        for (SessionDTO sessionDTO : sessionDTOS){
            session =modelMapper.map(sessionDTO, Session.class);
            sessions.add(session);
        }
        return sessions;
    }
}
