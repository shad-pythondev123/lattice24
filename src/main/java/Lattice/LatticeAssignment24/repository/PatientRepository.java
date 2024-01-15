package Lattice.LatticeAssignment24.repository;

import Lattice.LatticeAssignment24.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    public Patient findByEmail(String email);
}
