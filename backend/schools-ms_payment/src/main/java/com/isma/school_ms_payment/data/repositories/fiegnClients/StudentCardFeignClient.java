package com.isma.school_ms_payment.data.repositories.fiegnClients;

import com.isma.school_ms_payment.core.constants.FiegnClientsParams;
import com.isma.school_ms_payment.data.entities.StudentCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "studentCard-rest-api", url = FiegnClientsParams.StudentCard_REST_URL)
public interface StudentCardFeignClient{
    @GetMapping
    List<StudentCard> studentCardList(@RequestHeader("Authorization") String token);
    @GetMapping("/{id}")
    StudentCard studentCardById(@RequestHeader("Authorization") String token,@PathVariable("id") Long id);
}
