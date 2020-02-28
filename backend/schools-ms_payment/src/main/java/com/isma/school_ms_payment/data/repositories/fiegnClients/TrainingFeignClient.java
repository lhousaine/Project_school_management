package com.isma.school_ms_payment.data.repositories.fiegnClients;

import com.isma.school_ms_payment.core.constants.FiegnClientsParams;
import com.isma.school_ms_payment.data.entities.Training;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "trainingCard-rest-api", url = FiegnClientsParams.Training_REST_URL)
public interface TrainingFeignClient {
    @GetMapping("")
    List<Training> TrainingList(@RequestHeader("Authorization") String token);
    @GetMapping("/{id}")
    Training TrainingById(@RequestHeader("Authorization") String token,@PathVariable("id") Long id);
}
