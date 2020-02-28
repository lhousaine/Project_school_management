package com.isma.school_ms_schools;

import com.isma.school_ms_schools.core.dataInilizer.Initializer;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolMsSchoolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolMsSchoolsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(){
        return args -> Initializer.initData();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper;
    }
}
