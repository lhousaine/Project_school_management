package com.isma.school_ms_schools.service.implServices;

import com.isma.school_ms_schools.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_schools.core.exceptions.NoDataFoundException;
import com.isma.school_ms_schools.data.Converters.IConverter;
import com.isma.school_ms_schools.data.Dto.SubjectDTO;
import com.isma.school_ms_schools.data.Entities.Subject;
import com.isma.school_ms_schools.data.Repositories.SubjectRepository;
import com.isma.school_ms_schools.service.iservices.ISubjectService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.List;

@Service
@Transactional
public class ImplSubjectService implements ISubjectService {
    private final SubjectRepository subjectRepository;
    private final IConverter<Subject, SubjectDTO> subjectConverter;

    public ImplSubjectService(SubjectRepository subjectRepository, @Qualifier("ImplSubjectConverter") IConverter<Subject, SubjectDTO> subjectConverter) {
        this.subjectRepository = subjectRepository;
        this.subjectConverter = subjectConverter;
    }

    @Override
    public List<SubjectDTO> getAll() throws NoDataFoundException {
        List<Subject> subjects=subjectRepository.findAll();
        if (subjects==null)
            throw new NoDataFoundException("There is no subject");
        return subjectConverter.convertListToListDto(subjects);
    }

    @Override
    public SubjectDTO getById(Long idSubject) throws NoDataFoundException {
        Subject subject=subjectRepository.getOne(idSubject);
        if (subject==null)
            throw new NoDataFoundException("there is no course identified by "+idSubject);
        return subjectConverter.convertToDto(subject);
    }

    @Override
    public SubjectDTO create(SubjectDTO subjectDTO) throws DataAlreadyUsed, NoDataFoundException, ParseException {
        Subject subject=subjectConverter.convertToEntity(subjectDTO);
        Subject subject1=subjectRepository.findSubjectByName(subject.getName());
        if(subject1!=null)
            throw new DataAlreadyUsed("Branch "+subject.getName()+" Name already in use");
        subject1=subjectRepository.save(subject);
        return subjectConverter.convertToDto(subject1);
    }

    @Override
    public boolean update(Long idSubject, SubjectDTO subjectDTO) throws NoDataFoundException, DataAlreadyUsed, ParseException {
        Subject subject1=subjectRepository.getOne(idSubject);
        if (subject1==null) {
            throw new NoDataFoundException("No subject identified by " + idSubject);
        }
        Subject subject=subjectConverter.convertToEntity(subjectDTO);
        subject1.setName(subject.getName());
        subject1.setDescription(subject.getDescription());
        try {
            subjectRepository.save(subject1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long idSubject) throws NoDataFoundException {
        if (!subjectRepository.findById(idSubject).isPresent()) {
            throw new NoDataFoundException("No Subject identified by " + idSubject);
        }
        try{
            subjectRepository.deleteById(idSubject);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public SubjectDTO findSubjectByName(String subjectName) throws NoDataFoundException {
        Subject subject =subjectRepository.findSubjectByName(subjectName);
        if (subject==null)
            throw new NoDataFoundException("There is No subject named " +subjectName);
        return subjectConverter.convertToDto(subject);
    }
}
