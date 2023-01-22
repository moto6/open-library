package io.openlibrary.domain.repositroy;

import io.openlibrary.domain.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
}
