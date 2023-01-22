package io.openlibrary.repo;

import io.openlibrary.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator,Long> {
}
