package Lattice.LatticeAssignment24.service;

import Lattice.LatticeAssignment24.Enum.DoctorCity;
import Lattice.LatticeAssignment24.Enum.DoctorSpeciality;
import Lattice.LatticeAssignment24.Enum.PatientSymptom;
import Lattice.LatticeAssignment24.Exception.EntityAlreadyExistsException;
import Lattice.LatticeAssignment24.Exception.EntityNotFoundException;
import Lattice.LatticeAssignment24.model.Doctor;
import Lattice.LatticeAssignment24.model.Patient;
import Lattice.LatticeAssignment24.repository.DoctorRepository;
import Lattice.LatticeAssignment24.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class DoctorService {
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    PatientRepository patientRepository;

    public Doctor addDoctor(Doctor doctor) {
        Doctor doctor1= doctorRepository.findByEmail(doctor.getEmail());
        if (doctor1!=null) {
            throw new EntityAlreadyExistsException("Doctor Already Present");
        }
        return doctorRepository.save(doctor);

    }

    public String deleteDoctor(int Id) {
        Optional<Doctor> doctor1 = doctorRepository.findById(Id);
        if (doctor1.isEmpty()) {
            throw new EntityNotFoundException("Doctor Not Found!");
        }
        doctorRepository.delete(doctor1.get());
        return "Doctor Deleted Successfully";
    }

    public DoctorCity convertStringToEnum(String str) {

        if (str.equals("DELHI"))
            return DoctorCity.DELHI;
        if (str.equals("NOIDA"))
            return DoctorCity.NOIDA;
        if (str.equals("FARIDABAD"))
            return DoctorCity.FARIDABAD;

        return null;
    }

    public DoctorSpeciality getDoctorSpecialityFromPatientSymptom(PatientSymptom patientSymptom) {
        if (patientSymptom.equals(PatientSymptom.ARTHRITIS) || patientSymptom.equals(PatientSymptom.BACK_PAIN) ||
                patientSymptom.equals(PatientSymptom.TISSUE_INJURIES))
            return DoctorSpeciality.ORTHOPEDIC;
        else if (patientSymptom.equals(PatientSymptom.DYSMENORRHEA))
            return DoctorSpeciality.GYNECOLOGY;
        else if (patientSymptom.equals(PatientSymptom.SKIN_INFECTION) || patientSymptom.equals(PatientSymptom.SKIN_BURN))
            return DoctorSpeciality.DERMATOLOGY;
        else if (patientSymptom.equals(PatientSymptom.EAR_PAIN))
            return DoctorSpeciality.ENT;

        return null;
    }

    public ResponseEntity<Object> suggestDoctors(int patientId) {
        Optional<Patient> check = patientRepository.findById(patientId);

        if(check.isPresent())
        {
            Patient patient1 = check.get();


            DoctorCity patientLocation = convertStringToEnum(patient1.getCity());
            PatientSymptom patientSymptom = patient1.getSymptom();

            List<Doctor> doctors = doctorRepository.findAll();

            List<Doctor> doctors1 = new ArrayList<>();

            for(Doctor d : doctors){
                if(d.getDoctorCity().equals(patientLocation)){
                    doctors1.add(d);
                }
            }

            if(doctors1.isEmpty())
            {
                return new ResponseEntity<>("We are still waiting to expand to your location", HttpStatus.NOT_FOUND);
            }
            else
            {
                DoctorSpeciality doctorSpeciality = getDoctorSpecialityFromPatientSymptom(patientSymptom);

                List<Doctor> doctors2 = new ArrayList<>();

                for(Doctor doctor :  doctors1){
                    if(doctor.getSpeciality().equals(doctorSpeciality)){
                        doctors2.add(doctor);
                    }
                }

                if(doctors2.isEmpty()){
                    return new ResponseEntity<>("There isn’t any doctor present at your location for your symptom", HttpStatus.NOT_FOUND);
                }

                return new ResponseEntity<>(doctors2, HttpStatus.OK);
            }
        }


        return new ResponseEntity<>("Patient with id " + patientId + " not present", HttpStatus.NOT_FOUND);
    }
}
