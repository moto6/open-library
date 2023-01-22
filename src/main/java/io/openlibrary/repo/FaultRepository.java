package io.openlibrary.repo;

import io.openlibrary.domain.system_entity.FaultLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaultRepository extends JpaRepository<FaultLog,Long> {
}
