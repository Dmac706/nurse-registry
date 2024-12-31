package nurse.registry.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nurse.registry.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {
	
	

}
