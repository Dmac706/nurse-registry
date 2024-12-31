package nurse.registry.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import nurse.registry.entity.Registry;

public interface RegistryDao extends JpaRepository<Registry, Long> {

}
