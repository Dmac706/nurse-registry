package nurse.registry.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Nurse {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long nurseId;
  private String nurseFirstName;
  private String nurseLastName;
  
  
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(mappedBy = "nurses", cascade = CascadeType.PERSIST)
  private Set<Patient> patients = new HashSet<>();

@EqualsAndHashCode.Exclude
@ToString.Exclude
@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn(name = " registry_id")
private Registry registry;



}
