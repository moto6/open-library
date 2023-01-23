package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.domain.BookMaster;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookSearchRepository {
    List<BookMaster> titleSearch(@Param("word") String title);
}
