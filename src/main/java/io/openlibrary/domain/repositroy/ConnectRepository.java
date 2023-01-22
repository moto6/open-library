package io.openlibrary.domain.repositroy;

import io.openlibrary.domain.system.ConnectLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectRepository extends JpaRepository<ConnectLog,Long>{
}
