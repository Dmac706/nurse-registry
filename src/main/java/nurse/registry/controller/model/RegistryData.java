package nurse.registry.controller.model;


import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import nurse.registry.entity.Nurse;
import nurse.registry.entity.Patient;
import nurse.registry.entity.Registry;

@Data
@NoArgsConstructor
public class RegistryData {
	
	private Long registryId;
	  private String regName; 
	  private String regStreetAddress;
	  private String regCity;
	  private String regState;
	  private String regZip;
	  private String regPhone;
	  private Set<PatientData> patients = new HashSet<>();
	  private Set<NurseData> nurses = new HashSet<>();

	  public RegistryData (Registry registry) {
		this.registryId = registry.getRegistryId();
		this.regName = registry.getRegName();
		this.regStreetAddress = registry.getRegStreetAddress();
		this.regCity = registry.getRegCity();
		this.regState = registry.getRegState();
		this.regZip = registry.getRegZip();
		this.regPhone = registry.getRegPhone();
		
		for(Patient patient : registry.getPatients()) {
			this.patients.add(new PatientData(patient));
		}
		
		
		}

		
		
	  
	  
	 public RegistryData ( Long registryId, String regName, 
			 String regStreetAddress,String regCity, String regState, String regZip,
	     String regPhone) {
		 this.registryId = registryId;
		 this.regName = regName; 
		 this.regStreetAddress = regStreetAddress;
		 this.regCity = regCity;
		 this.regState = regState;
		 this.regZip = regZip;
		 this.regPhone = regPhone;
	 }
	 
	 public Registry toRegistry() {
	  Registry registry = new Registry();
	  
	  registry.setRegistryId(registryId);
	  registry.setRegName(regName);
	  registry.setRegStreetAddress(regStreetAddress);
	  registry.setRegCity(regCity);
	  registry.setRegState(regState);
	  registry.setRegZip(regZip);
	  registry.setRegPhone(regPhone);
	  
	  for(PatientData patientData : patients) {
		  registry.getPatients().add(patientData.toPatient());
		  
	  }
	   
//	  for(NurseData nurseData : nurses) {
//		  registry.getNurses().add(nurseData.toNurse());
//	  }
	  
	  
	  return registry;
	 }


	  

	  
	  
	  @Data
	  @NoArgsConstructor
	  public static class PatientData {
		  private Long patientId;
		  private String ptFirstName;
		  private String ptLastName;
		  private int ptAge;
		  private String diagnosis;
		  private String ptStreetAddress;
		  private String ptCity;
		  private String ptState;
		  private String ptZip;
		  private String ptPhone;
		  private Set<NurseData> nurses  = new HashSet<>();
		  
		  
	  public PatientData(Patient patient) {	  
		this.patientId = patient.getPatientId();
		this.ptFirstName = patient.getPtFirstName();
		this.ptLastName = patient.getPtLastName();
		this.ptAge = patient.getPtAge();
		this.diagnosis = patient.getDiagnosis();
		this.ptStreetAddress = patient.getPtStreetAddress();
		this.ptCity = patient.getPtCity();
		this.ptState = patient.getPtState();
		this.ptZip = patient.getPtZip();
		this.ptPhone = patient.getPtPhone();
		
		for(Nurse nurse : patient.getNurses()) {
		  this.nurses.add(new NurseData(nurse));	
		}
		
	  }
	  
	  public Patient toPatient() {
		Patient patient = new Patient();
		
		patient.setPatientId(patientId);
		patient.setPtFirstName(ptFirstName);
		patient.setPtLastName(ptLastName);
		patient.setPtAge(ptAge);
		patient.setDiagnosis(diagnosis);
		patient.setPtStreetAddress(ptStreetAddress);
		patient.setPtCity(ptCity);
		patient.setPtState(ptState);
		patient.setPtZip(ptZip);
		patient.setPtPhone(ptPhone);
		
		for(NurseData nurseData : nurses) {
		  patient.getNurses().add(nurseData.toNurse());	
			
		}
		
		return patient;
		  
	  }
	  }  
	
	
   @Data
   @NoArgsConstructor
   public static class NurseData {	
	   private Long nurseId;
	   private String nurseFirstName;
	   private String nurseLastName; 
	   
	   public NurseData (Nurse nurse) {
		 this.nurseId = nurse.getNurseId();
		 this.nurseFirstName = nurse.getNurseFirstName();
		 this.nurseLastName = nurse.getNurseLastName();
		   
	   }
	  public Nurse toNurse() { 
		Nurse nurse = new Nurse();
		
		nurse.setNurseId(nurseId);
		nurse.setNurseFirstName(nurseFirstName);
		nurse.setNurseLastName(nurseLastName);
		
		return nurse;
	  }
	public static Object getAmenities() {
		// TODO Auto-generated method stub
		return null;
	}
	   
	   
   }
}


	  



