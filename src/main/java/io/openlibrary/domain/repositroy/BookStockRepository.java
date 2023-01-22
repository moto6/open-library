package io.openlibrary.domain.repositroy;

import io.openlibrary.domain.entity.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookStockRepository extends JpaRepository<BookStock, Long> {

}
