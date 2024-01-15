package Lattice.LatticeAssignment24.repository;

import Lattice.LatticeAssignment24.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    public Doctor findByEmail(String email);
}
