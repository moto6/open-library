package io.openlibrary.service;

import io.openlibrary.domain.BookStock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookStockService {
    public List<BookStock> list() {
        List<BookStock> o = null;
        return o;
    }
}
