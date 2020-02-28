package com.isma.school_ms_payment.data.repositories.fiegnClients;

import com.isma.school_ms_payment.core.constants.FiegnClientsParams;
import com.isma.school_ms_payment.data.entities.Session;
import com.isma.school_ms_payment.data.entities.StudentCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "session-rest-api", url = FiegnClientsParams.Session_REST_URL)
public interface SessionFeignClient {
    @GetMapping("")
    List<StudentCard> sessionList(@RequestHeader("Authorization") String token);
    @GetMapping("/{id}")
    Session sessionOne(@RequestHeader("Authorization") String token,@PathVariable("id") Long id);
    @GetMapping("/sessions-by-teacher")
    StudentCard sessionById(@RequestHeader("Authorization") String token, @PathVariable("idTeacher") Long idTeacher);
}
