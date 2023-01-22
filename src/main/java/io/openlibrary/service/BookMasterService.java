package io.openlibrary.service;

import io.openlibrary.domain.BookMaster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookMasterService {
    public List<BookMaster> list() {
        List<BookMaster> o = null;
        return o;
    }

    public BookMaster add() {
        return null;
    }

    public BookMaster modify(String isbnCode) {
        return null;
    }
}
