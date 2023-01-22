package io.openlibrary.common.preload;

import io.openlibrary.common.preload.component.PreloadHandler;
import io.openlibrary.common.preload.impl.PreloadServiceCsvImpl;
import io.openlibrary.entity.domain.Accounts;
import io.openlibrary.entity.domain.Administrator;
import io.openlibrary.entity.domain.BookMaster;
import io.openlibrary.entity.repositroy.AccountRepository;
import io.openlibrary.entity.repositroy.AdministratorRepository;
import io.openlibrary.entity.repositroy.BookMasterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PreloadLauncher {

    @Bean
    CommandLineRunner accountInitDatabase(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Accounts("","1234","")));
            log.info("Preloading " + repository.save(new Accounts("","0000","")));
        };
    }

    @Bean
    CommandLineRunner adminInitDatabase(AdministratorRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Administrator("jake.ryu")));
            log.info("Preloading " + repository.save(new Administrator("andrew.w")));
            log.info("Preloading " + repository.save(new Administrator("kelly.j")));
        };
    }

    //페이즈에 따라서, 컨피그설정에 따라서 추가하냐마냐 결정하기
    @Bean
    CommandLineRunner bookAdd(BookMasterRepository bookMasterRepository, PreloadServiceCsvImpl<BookMaster> preloadServiceCsv ){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                PreloadHandler preloadHandler = preloadServiceCsv.initPreload();
                preloadServiceCsv.savePreload(bookMasterRepository, preloadHandler, BookMaster.class,BookMaster.csvMapper());
                List<String> list = preloadServiceCsv.headerPreloadInfo(preloadHandler);
                int i=0;
                for (String s : list) {
                    log.info("Headers[{}] : [{}]",i++, s);
                }
                return;
            }
        };
    }



}
//https://spring.io/guides/tutorials/rest/