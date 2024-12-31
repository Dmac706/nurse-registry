package nurse.registry.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Registry {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long registryId;
  
  private String regName; 
  private String regStreetAddress;
  private String regCity;
  private String regState;
  private String regZip;
  private String regPhone;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "registry", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Patient> patients = new HashSet<>();

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "registry", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Nurse> nurses = new HashSet<>();
 

}
