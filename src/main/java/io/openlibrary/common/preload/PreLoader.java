package io.openlibrary.common.preload;

import io.openlibrary.entity.domain.Accounts;
import io.openlibrary.entity.domain.Administrator;
import io.openlibrary.entity.repositroy.AccountRepository;
import io.openlibrary.entity.repositroy.AdministratorRepository;
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
            log.info("Preloading " + repository.save(new Accounts("","1234","")));
            log.info("Preloading " + repository.save(new Accounts("","0000","")));
        };
    }

    @Bean
    CommandLineRunner init2Database(AdministratorRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Administrator("jake.ryu")));
            log.info("Preloading " + repository.save(new Administrator("andrew.w")));
            log.info("Preloading " + repository.save(new Administrator("kelly.j")));
        };
    }
}
//https://spring.io/guides/tutorials/rest/