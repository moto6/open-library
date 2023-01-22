package io.openlibrary.repo;

import io.openlibrary.domain.system_entity.PersistLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistRepository extends JpaRepository<PersistLog,Long> {
}
