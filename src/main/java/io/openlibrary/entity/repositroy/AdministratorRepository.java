package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
}
