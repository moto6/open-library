package io.openlibrary.entity.repositroy;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import io.openlibrary.entity.domain.BookMaster;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookSearchElasticRepository implements BookSearchRepository{
    private static final String indexName = "bookElastic";
    private final ElasticsearchClient elasticsearchClient;
//https://www.pixeltrice.com/spring-boot-elasticsearch-crud-example/
    @Override
    public List<BookMaster> titleSearch(String title) {
        elasticsearchClient.index(i -> i
                .index(indexName)
                .index(title)
                .d
        );

        return null;
    }
}
