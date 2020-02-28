package com.isma.school_ms_payment.Controllers;

import com.isma.school_ms_payment.core.exceptions.AmountSurplusException;
import com.isma.school_ms_payment.core.exceptions.DataAlreadyUsed;
import com.isma.school_ms_payment.core.exceptions.NoDataFoundException;
import com.isma.school_ms_payment.data.entities.StudentPayment;
import com.isma.school_ms_payment.service.services.StudentPaymentService;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path ="/studentPayments")
public class StudentPaymentController {
    private StudentPaymentService studentPaymentService;

    public StudentPaymentController(StudentPaymentService studentPaymentService) {
        this.studentPaymentService = studentPaymentService;
    }

    @GetMapping(path = "",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentPayment> getStudentPayments() throws NoDataFoundException {
        return studentPaymentService.getAll();
    }

    @GetMapping(path = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public StudentPayment getStudentPaymentById(@PathVariable(value = "id") Long id) throws NoDataFoundException {
        return studentPaymentService.getById(id);
    }


    @GetMapping(path = "/studentId/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<StudentPayment> getStudentPaymentByStudentId(@PathVariable(value = "id") Long id) throws NoDataFoundException {
        return studentPaymentService.getByStudentId(id);
    }

    @GetMapping(path = "/createFix")
    public StudentPayment createStudentPayments(@RequestParam("id") Long id,@RequestParam("amount") Double amount) throws NoDataFoundException, AmountSurplusException {
        return studentPaymentService.createStudentPaymentFix(id,amount);
    }

    @PatchMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes ={MediaType.APPLICATION_JSON_VALUE})
    public boolean update(@PathVariable Long id,@RequestBody StudentPayment studentPayment) throws NoDataFoundException, DataAlreadyUsed {
        return studentPaymentService.update(id,studentPayment);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public boolean delete(@PathVariable Long id) throws NoDataFoundException {
        return studentPaymentService.delete(id);
    }
}
