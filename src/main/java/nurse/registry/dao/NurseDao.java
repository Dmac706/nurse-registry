package nurse.registry.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nurse.registry.entity.Nurse;

public interface NurseDao extends JpaRepository<Nurse, Long> {

}
