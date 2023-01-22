package io.openlibrary.domain.repositroy;

import io.openlibrary.domain.entity.BookMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookMasterRepository extends JpaRepository<BookMaster,Long> {
}
