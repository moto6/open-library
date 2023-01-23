package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.system.FaultLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultRepository extends JpaRepository<FaultLog, Long> {
}
