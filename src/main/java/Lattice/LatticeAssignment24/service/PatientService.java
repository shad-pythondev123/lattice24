package Lattice.LatticeAssignment24.service;

import Lattice.LatticeAssignment24.Exception.EntityAlreadyExistsException;
import Lattice.LatticeAssignment24.Exception.EntityNotFoundException;
import Lattice.LatticeAssignment24.model.Patient;
import Lattice.LatticeAssignment24.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public Patient addPatient(Patient patient){
        Patient patient1= patientRepository.findByEmail(patient.getEmail());
        if (patient1!=null) {
            throw new EntityAlreadyExistsException("Patient Already Present");
        }
        return patientRepository.save(patient);
    }
    public String deletePatient(int id){
        Optional<Patient> patient1 = patientRepository.findById(id);
        if (patient1.isEmpty()) {
            throw new EntityNotFoundException("Patient not Found!");
        }
        patientRepository.delete(patient1.get());
        return "Patient deleted Successfully";
    }
}
