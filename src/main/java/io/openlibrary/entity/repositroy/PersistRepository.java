package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.system.PersistLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersistRepository extends JpaRepository<PersistLog,Long> {
}
