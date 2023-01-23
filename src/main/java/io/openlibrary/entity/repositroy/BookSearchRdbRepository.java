package io.openlibrary.entity.repositroy;

import io.openlibrary.entity.domain.BookMaster;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookSearchRdbRepository implements BookSearchRepository{
    @Override
    public List<BookMaster> titleSearch(String title) {
        return null;
    }
}
