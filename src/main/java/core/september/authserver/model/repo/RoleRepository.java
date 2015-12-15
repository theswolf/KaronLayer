package core.september.authserver.model.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import core.september.authserver.model.Role;


public interface RoleRepository extends JpaRepository<Role, Serializable>
{

}
