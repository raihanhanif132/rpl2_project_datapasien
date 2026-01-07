package com.mycompany.patientapp.service;

import com.mycompany.patientapp.model.Patient;
import org.springframework.data.domain.Page;
import java.util.List;

public interface PatientService {
    List<Patient> getAllPatients();
    void savePatient(Patient patient);
    Patient getPatientById(long id);
    void deletePatientById(long id);
    Page<Patient> findPaginated(int pageNum, int pageSize, String sortField, String sortDirection);
}