package nurse.registry.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nurse.registry.controller.model.RegistryData;
import nurse.registry.controller.model.RegistryData.NurseData;
import nurse.registry.controller.model.RegistryData.PatientData;
import nurse.registry.dao.NurseDao;
import nurse.registry.dao.PatientDao;
import nurse.registry.dao.RegistryDao;
import nurse.registry.entity.Nurse;
import nurse.registry.entity.Patient;
import nurse.registry.entity.Registry;


@Service
public class RegistryService {
	
	@Autowired
	private RegistryDao registryDao;
	
    @Transactional(readOnly = false)
	public RegistryData saveRegistry(RegistryData registryData) {
	  Registry registry = registryData.toRegistry();
	  Registry dbRegistry = registryDao.save(registry);
	  
	  return new RegistryData(dbRegistry);
	  
	}
    @Transactional(readOnly = true)
	public RegistryData retrieveRegistryByID(Long registryId) {
    	Registry registry = findRegistryById(registryId);	
  	  return new RegistryData(registry);
		
		
	}

	private Registry findRegistryById(Long registryId) {
	  return registryDao.findById(registryId).orElseThrow(() -> new NoSuchElementException(
			  "Registry with ID" + registryId + " was not found."));
		
	}
	
	@Transactional(readOnly = true)
	public List<RegistryData> retrieveAllRegistry() {
	  List<Registry> registryEntities = registryDao.findAll();
	  List<RegistryData> registryDtos = new LinkedList<>();
	  
	  registryEntities.sort((reg1, reg2) -> reg1.getRegName()
	  .compareTo(reg2.getRegName()));
	  
	  for(Registry registry : registryEntities) {
		RegistryData registryData = new RegistryData(registry);
		registryDtos.add(registryData);
	  }
	  
	  return registryDtos;
	  
	}
    
	@Transactional(readOnly = false)
	public void deleteRegistry(Long registryId) {
	  Registry registry = findRegistryById(registryId);
	  registryDao.delete(registry);
	
		
	}
	@Autowired
	private NurseDao nurseDao; 
	
//	@Transactional(readOnly = false)
//	public NurseData saveNurse(NurseData nurseData) {
//	  Nurse nurse = nurseData.toNurse();
//	  Nurse dbNurse = nurseDao.save(nurse);
//	  
//	  return new NurseData(dbNurse);
//	}
//	
//	@Transactional(readOnly = false)
//	public PatientData savePatient(PatientData patientData) {
//	  Nurse Patient = patientData.toPatient();
//	  Nurse dbNurse = nurseDao.save(nurse);
//	  
//	  return new PatientData(dbPatient);
//	}
	
	public void addPatientToNurse(Long patientId, Long nurseId) {
//		Registry registry = findRegistryById(registryId);
		Nurse nurse = findNurseById(nurseId);
		Patient patient = findPatientById(patientId);
	    nurse.getPatients().add(patient);
	    
	    patient.getNurses().add(nurse);
	    
	    nurseDao.save(nurse);
	    patientDao.save(patient);
	}
private Nurse findNurseById(Long nurseId) {
	Nurse nurse = nurseDao.findById(nurseId).orElseThrow(() ->
	new NoSuchElementException("Nurse with ID=" + nurseId + " was not found"));
	return nurse;
	  
}
@Autowired
private PatientDao patientDao;

private Patient findPatientById(Long patientId) {
	Patient patient = patientDao.findById(patientId).orElseThrow(() ->
	new NoSuchElementException("Patient with ID=" + patientId + " was not found"));
	return patient;
	  
}

@Transactional(readOnly = false)
	public PatientData savePatient(Long registryId,
			PatientData patientData) {
   	Registry registry = findRegistryById(registryId);
     	
   
   	Patient patient = findOrCreatePatient(registryId, patientData.getPatientId());
   	setPatientFields(patient, patientData);
   	
   	patient.setRegistry(registry);
   	registry.getPatients().add(patient);
   	
   	Patient dbPatient = patientDao.save(patient);
   	return new PatientData(dbPatient);
}
private void setPatientFields(Patient patient, PatientData patientData) {
	patient.setPatientId(patientData.getPatientId());
	patient.setPtFirstName(patientData.getPtFirstName());
	patient.setPtLastName(patientData.getPtLastName());
	patient.setPtAge(patientData.getPtAge());
	patient.setDiagnosis(patientData.getDiagnosis());
	patient.setPtStreetAddress(patientData.getPtStreetAddress());
	patient.setPtCity(patientData.getPtCity());
	patient.setPtState(patientData.getPtState());
	patient.setPtZip(patientData.getPtZip());
	patient.setPtPhone(patientData.getPtPhone());
	
}
private Patient findOrCreatePatient(Long  registryId, Long patientId) {
		  Patient patient;
		  
		  if(Objects.isNull(patientId)) {
			patient = new Patient();
			  
		  }
		  else {
			  patient = findPatientById(registryId, patientId);
		  }
		   
		   return patient; 	
	
}
private Patient findPatientById(Long registryId, Long patientId) {
	Patient patient = patientDao.findById(patientId).orElseThrow(() ->
	new NoSuchElementException("Patient with ID=" + patientId + " was not found"));
	
	if(patient.getRegistry().getRegistryId() != registryId) {
		throw new IllegalArgumentException(" The patient with ID=" 
	+ patientId + " is not employed by the registry with ID=" + registryId);
	}
	return patient;
}

@Transactional(readOnly = false)
public NurseData saveNurse(Long registryId,
		NurseData nurseData) {
	Registry registry = findRegistryById(registryId);
 	

	Nurse nurse = findOrCreateNurse(registryId, nurseData.getNurseId());
	setNurseFields(nurse, nurseData);
	
	nurse.setRegistry(registry);
	registry.getNurses().add(nurse);
	
	Nurse dbNurse = nurseDao.save(nurse);
	return new NurseData(dbNurse);
}
private void setNurseFields(Nurse nurse, NurseData nurseData) {
	nurse.setNurseId(nurseData.getNurseId());
	nurse.setNurseFirstName(nurseData.getNurseFirstName());
	nurse.setNurseLastName(nurseData.getNurseLastName());

}
private Nurse findOrCreateNurse(Long  registryId, Long nurseId) {
	  Nurse nurse;
	  
	  if(Objects.isNull(nurseId)) {
		nurse = new Nurse();
		  
	  }
	  else {
		  nurse = findNurseById(registryId, nurseId);
	  }
	   
	   return nurse; 	

}
private Nurse findNurseById(Long registryId, Long nurseId) {
Nurse nurse = nurseDao.findById(nurseId).orElseThrow(() ->
new NoSuchElementException("Nurse with ID=" + nurseId + " was not found"));

if(nurse.getRegistry().getRegistryId() != registryId) {
	throw new IllegalArgumentException(" The nurse with ID=" 
+ nurseId + " is not employed by the registry with ID=" + registryId);
}
return nurse;
}
}
