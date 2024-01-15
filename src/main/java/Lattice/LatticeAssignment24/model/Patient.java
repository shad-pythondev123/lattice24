package Lattice.LatticeAssignment24.model;

import Lattice.LatticeAssignment24.Enum.PatientSymptom;
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
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;
    @Size(min=3)
    @Column(nullable = false)
    private String name;
    @Size(max=20)
    String city;
    @Enumerated(EnumType.STRING)
    private PatientSymptom symptom;

    @Email
    @Column(unique = true)
    private String email;
    @Size(min=10)
    @Column(unique = true)
    private String phone;
}
