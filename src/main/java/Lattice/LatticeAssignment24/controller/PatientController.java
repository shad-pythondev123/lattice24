package Lattice.LatticeAssignment24.controller;

import Lattice.LatticeAssignment24.Exception.EntityAlreadyExistsException;
import Lattice.LatticeAssignment24.Exception.EntityNotFoundException;
import Lattice.LatticeAssignment24.model.Patient;
import Lattice.LatticeAssignment24.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/Patient")
@RestController
public class PatientController {
    @Autowired
    PatientService patientService;
    @PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@Valid @RequestBody Patient patient){
        try{
            patientService.addPatient(patient);
            return ResponseEntity.ok("Patient Added Successfully!");
        }
        catch(EntityAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @DeleteMapping("/deletePatient")
    public ResponseEntity<String> deletePatient(@RequestParam int id){
        try{
            patientService.deletePatient(id);
            return ResponseEntity.ok("Patient Deleted Successfully!");
        }
        catch(EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
