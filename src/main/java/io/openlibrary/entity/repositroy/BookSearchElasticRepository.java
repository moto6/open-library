package io.openlibrary.entity.repositroy;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import io.openlibrary.entity.domain.BookMaster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BookSearchElasticRepository implements BookSearchRepository {
    private static final String indexName = "bookElastic";
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public List<BookMaster> titleSearch(String title) {
        try {
            SearchResponse<BookMaster> search = elasticsearchClient.search(s -> s
                            .index(indexName)
                            .query(q -> q
                                    .term(t -> t
                                            .field("title")
                                            .value(v -> v.stringValue(title))
                                    )),
                    BookMaster.class);
            return search.hits().hits()
                    .parallelStream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        }
        catch (IOException ioException) {
            throw new RuntimeException("ES 검색 실패");
        }
    }
}
