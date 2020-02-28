package com.isma.school_ms_payment.service.services;

import com.isma.school_ms_payment.core.enums.PaymentStateEnum;
import com.isma.school_ms_payment.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_payment.core.exceptions.NoDataFoundException;
import com.isma.school_ms_payment.data.entities.StudentCard;
import com.isma.school_ms_payment.data.entities.StudentPayment;
import com.isma.school_ms_payment.data.repositories.StudentPaymentRepository;
import com.isma.school_ms_payment.service.iservices.IAbstractService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class StudentPaymentService implements IAbstractService<Long, StudentPayment> {
    private StudentPaymentRepository studentPaymentRepository;


    public StudentPaymentService(StudentPaymentRepository studentPaymentRepository) {
        this.studentPaymentRepository = studentPaymentRepository;

    }

    @Override
    public List<StudentPayment> getAll() throws NoDataFoundException {
        return studentPaymentRepository.findAll();

    }

    @Override
    public StudentPayment getById(Long id) throws NoDataFoundException {
        if (studentPaymentRepository.findById(id).isPresent())
            return studentPaymentRepository.getOne(id);
        else
            throw new NoDataFoundException("No StudentPayment identified by " + id);
    }


    public List<StudentPayment> getByStudentId(Long id) throws NoDataFoundException {

        return studentPaymentRepository.findByStudentCode("" + id);

    }

    public StudentPayment createStudentPaymentFix(Long id, Double amount) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization").split(" ")[1];
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("AUTHORIZATION", "Bearer " + token);
        headers.add("Accept", "*/*");
        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
        ResponseEntity<StudentCard> responseEntity = rest.exchange("http://localhost:8088/SCHOOLS-SERVICE/students-cards/stuCard-by-stuCode/" + id, HttpMethod.GET, requestEntity, StudentCard.class);
        StudentPayment studentPayment = new StudentPayment();
        if (StudentPayment.amount.equals(amount)) {
            studentPayment.setAmountLeft(0D);
            studentPayment.setPaymentStateEnum(PaymentStateEnum.DONE);
        } else if (!StudentPayment.amount.equals(amount)) {

            studentPayment.setAmountLeft(StudentPayment.amount - amount);
            studentPayment.setPaymentStateEnum(PaymentStateEnum.INPROGRESS);
        }
        studentPayment.setMois(new Date());
        studentPayment.setStudentCard(responseEntity.getBody());
        studentPayment.setStudentCode("" + id);
        return studentPaymentRepository.save(studentPayment);
    }

    @Override
    public StudentPayment create(StudentPayment studentPayment) throws DataAlreadyUsed, NoDataFoundException {
        return null;
    }

    @Override
    public boolean update(Long id, StudentPayment studentPayment) throws NoDataFoundException, DataAlreadyUsed {
        StudentPayment studentPayment1 = studentPaymentRepository.findById(id).get();
        if (studentPayment1 == null)
            throw new DataAlreadyUsed("No StudentPayment was identified by " + id);
        studentPayment1.setStudentCard(studentPayment.getStudentCard());
        studentPayment1.setId(id);
        try {
            studentPaymentRepository.save(studentPayment1);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) throws NoDataFoundException {
        if (!studentPaymentRepository.findById(id).isPresent()) {
            throw new NoDataFoundException("No Student identified by " + id);
        }
        try {
            studentPaymentRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
