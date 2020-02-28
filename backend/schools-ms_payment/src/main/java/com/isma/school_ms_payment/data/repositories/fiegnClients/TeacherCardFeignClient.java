package com.isma.school_ms_payment.data.repositories.fiegnClients;

import com.isma.school_ms_payment.core.constants.FiegnClientsParams;
import com.isma.school_ms_payment.data.entities.TeacherCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "teacherCard-rest-api", url = FiegnClientsParams.StudentCard_REST_URL)
public interface TeacherCardFeignClient {
    @GetMapping("")
    List<TeacherCard> TeacherCardList(@RequestHeader("Authorization") String token);
    @GetMapping("/{id}")
    TeacherCard TeacherCardById(@RequestHeader("Authorization") String token,@PathVariable("id") Long id);
}