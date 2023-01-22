package io.openlibrary.repo;

import io.openlibrary.domain.system_entity.ConnectLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectRepository extends JpaRepository<ConnectLog,Long>{
}
