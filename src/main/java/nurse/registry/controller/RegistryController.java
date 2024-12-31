package nurse.registry.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;
import nurse.registry.controller.model.RegistryData;
import nurse.registry.controller.model.RegistryData.NurseData;
import nurse.registry.controller.model.RegistryData.PatientData;
import nurse.registry.service.RegistryService;

@RestController
@RequestMapping("/nurse_registry")
@Slf4j
public class RegistryController {
  @Autowired	
  private RegistryService registryService;
  
  @PostMapping	
  @ResponseStatus(code = HttpStatus.CREATED)
  public RegistryData createRegistry(@RequestBody RegistryData registryData) {
	log.info("Creating registry ()", registryData);  
	return registryService.saveRegistry(registryData);
	  
  }
  
  
 @PutMapping("/registry/{registryId}")
	public RegistryData updateRegistry(@PathVariable Long registryId,
			@RequestBody RegistryData registryData) {
	
	registryData.setRegistryId(registryId);
	  log.info("Updating registry {}", registryData);
	  return registryService.saveRegistry(registryData);

	}
		
	
	
	@GetMapping("/registry/{registryId}")
	public RegistryData retrieveRegistry(@PathVariable Long registryId) {
	  log.info("Retrieving registry with ID={}", registryId);
	  return registryService.retrieveRegistryByID(registryId);
	  

		
		
	}
	
	
	
	@GetMapping("/registry")
	public List<RegistryData> retrieveAllRegistry() {
	  log.info("Retrieving all locations");	
	  return registryService.retrieveAllRegistry();	
	}
	
	@DeleteMapping("/registry/{registryId}")
	public Map<String, String> deleteRegistry(
			@PathVariable Long registryId){
	  
	log.info("Deleting registry with ID=" + registryId + "." );
	  registryService.deleteRegistry(registryId);
	  
	  return Map.of("message", "Registry with ID=" + registryId
			  + " was deleted successfully.");
		
	}
//Nurse*******************
	

	  @PostMapping("/{registryId}/nurse")	
	  @ResponseStatus(code = HttpStatus.CREATED)
	  public NurseData createNurse(@PathVariable Long registryId, @RequestBody NurseData nurseData) {
		log.info("Creating registry ()", nurseData);  
		return registryService.saveNurse(registryId, nurseData);
		  
	  }
	
	//	Patient***********************
	@PostMapping("/{registryId}/patient")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PatientData insertPatient(@PathVariable Long registryId,
			@RequestBody PatientData patientData) {
		
		log.info("Creating patient {} for registry with ID={}", patientData,
				registryId); 
		
		return registryService.savePatient(registryId, patientData);
	}

	
//	nurse_patient****************
	
	@PostMapping("/{patientId}/{nurseId}")
	public Map<String, String> addPatientToNurse(
			@PathVariable Long patientId, @PathVariable Long nurseId){
	  
	log.info(" Adding patient with ID=" + patientId + " to nurse with ID =" + nurseId);
	  registryService.addPatientToNurse(patientId, nurseId);
	  
	  return Map.of("message", " Patient with ID=" 
	  + patientId + "  was added to nurse with ID =" + nurseId);
	}
	}

	
