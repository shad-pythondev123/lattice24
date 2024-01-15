package Lattice.LatticeAssignment24.model;

import Lattice.LatticeAssignment24.Enum.DoctorCity;
import Lattice.LatticeAssignment24.Enum.DoctorSpeciality;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Size(min=3)

    @Column(nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private DoctorCity doctorCity;
    @Enumerated(EnumType.STRING)
    private DoctorSpeciality speciality;

    @Email
    @Column(unique = true)
    private String email;
    @Size(min=10)
    @Column(unique = true)
    private String phone;
}
