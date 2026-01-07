package com.mycompany.patientapp.controller;

import com.mycompany.patientapp.model.Patient;
import com.mycompany.patientapp.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Halaman Utama
    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "nama", "asc", model);
    }

    @GetMapping("/showNewPatientForm")
    public String showNewPatientForm(Model model) {
        // Membuat objek model atribut untuk mengikat data form
        Patient patient = new Patient();
        model.addAttribute("patient", patient);
        return "new_patient";
    }

    @PostMapping("/savePatient")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        // Simpan pasien ke database
        patientService.savePatient(patient);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        // Ambil pasien dari service
        Patient patient = patientService.getPatientById(id);
        
        // Set pasien sebagai model atribut untuk pre-populate form
        model.addAttribute("patient", patient);
        return "update_patient";
    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable(value = "id") long id) {
        // Panggil method delete pada service
        this.patientService.deletePatientById(id);
        return "redirect:/";
    }

    // Pagination dan Sorting
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {
        int pageSize = 5;

        Page<Patient> page = patientService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Patient> listPatients = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("listPatients", listPatients);
        return "index";
    }
}