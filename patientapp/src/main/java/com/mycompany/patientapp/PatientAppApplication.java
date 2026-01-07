package com.mycompany.patientapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientAppApplication {

    public static void main(String[] args) {
        // Baris ini menjalankan seluruh ekosistem Spring Boot
        SpringApplication.run(PatientAppApplication.class, args);
    }

}