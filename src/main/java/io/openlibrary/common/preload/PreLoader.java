package io.openlibrary.common.preload;

import io.openlibrary.domain.Account;
import io.openlibrary.repo.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class PreLoader {
    @Bean
    CommandLineRunner initDatabase(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Account("1234")));
            log.info("Preloading " + repository.save(new Account("0000")));
        };
    }
}
//https://spring.io/guides/tutorials/rest/