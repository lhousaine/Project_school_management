package com.isma.school_ms_payment.service.services;

import com.isma.school_ms_payment.core.enums.PaymentStateEnum;
import com.isma.school_ms_payment.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_payment.core.exceptions.NoDataFoundException;
import com.isma.school_ms_payment.data.entities.StudentCard;
import com.isma.school_ms_payment.data.entities.StudentPayment;
import com.isma.school_ms_payment.data.entities.TeacherCard;
import com.isma.school_ms_payment.data.entities.TeacherPayment;
import com.isma.school_ms_payment.data.repositories.TeacherPaymentRepository;
import com.isma.school_ms_payment.service.iservices.IAbstractService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TeacherPaymentService implements IAbstractService<Long, TeacherPayment> {
    private TeacherPaymentRepository teacherPaymentRepository;

    public TeacherPaymentService(TeacherPaymentRepository teacherPaymentRepository) {
        this.teacherPaymentRepository = teacherPaymentRepository;
    }

    @Override
    public List<TeacherPayment> getAll() throws NoDataFoundException {
        return null;
    }

    @Override
    public TeacherPayment getById(Long aLong) throws NoDataFoundException {
        return null;
    }

    @Override
    public TeacherPayment create(TeacherPayment teacherPayment) throws DataAlreadyUsed, NoDataFoundException {
        return null;
    }

    @Override
    public boolean update(Long aLong, TeacherPayment teacherPayment) throws NoDataFoundException, DataAlreadyUsed {
        return false;
    }

    public TeacherPayment createTeacherPaymentFix(Long id, Double amount){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("AUTHORIZATION" ,"Bearer " + token);
        headers.add("Accept", "*/*");
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<List<TeacherCard>> responseEntity = rest.exchange("http://localhost:8081/SCHOOLS-SERVICE/teacher-cards/teachercode/" + id , HttpMethod.GET, requestEntity,  new ParameterizedTypeReference<List<TeacherCard>>() {});
        TeacherPayment teacherPayment = new TeacherPayment();
        if(TeacherPayment.amount == amount){
            teacherPayment.setAmountLeft(0D);
            teacherPayment.setPaymentStateEnum(PaymentStateEnum.DONE);
        } else if(TeacherPayment.amount != amount) {
            teacherPayment.setAmountLeft(TeacherPayment.amount - amount);
            teacherPayment.setPaymentStateEnum(PaymentStateEnum.INPROGRESS);
        }
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        teacherPayment.setMonth(month);
        teacherPayment.setTeacherCards(responseEntity.getBody());
        teacherPayment.setTeacherCode("" + id);
        return teacherPaymentRepository.save(teacherPayment);
    }

    @Override
    public boolean delete(Long aLong) throws NoDataFoundException {
        return false;
    }
}
