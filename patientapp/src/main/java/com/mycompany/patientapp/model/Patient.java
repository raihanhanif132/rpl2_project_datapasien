package com.mycompany.patientapp.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String nik;

    @Column(nullable = false)
    private String nama;

    @Column(name = "jenis_kelamin")
    private String jenisKelamin; // Laki-laki / Perempuan

    @Column(name = "tanggal_lahir")
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Format input HTML5
    private LocalDate tanggalLahir;

    @Column(columnDefinition = "TEXT")
    private String alamat;

    @Column(name = "nomor_telepon")
    private String nomorTelepon;

    @Column(columnDefinition = "TEXT")
    private String keluhan; 
}