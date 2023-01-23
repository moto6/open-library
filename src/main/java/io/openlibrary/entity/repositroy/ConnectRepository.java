package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.system.ConnectLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectRepository extends JpaRepository<ConnectLog, Long> {
}
