package io.openlibrary.domain.repositroy;

import io.openlibrary.domain.system.FaultLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultRepository extends JpaRepository<FaultLog,Long> {
}
