package io.openlibrary.common.preload;

import io.openlibrary.common.preload.impl.PreloadServiceCsvToBookMaster;
import io.openlibrary.entity.domain.Accounts;
import io.openlibrary.entity.domain.Administrator;
import io.openlibrary.entity.domain.BookMaster;
import io.openlibrary.entity.domain.Reservation;
import io.openlibrary.entity.repositroy.AccountRepository;
import io.openlibrary.entity.repositroy.AdministratorRepository;
import io.openlibrary.entity.repositroy.BookMasterRepository;
import io.openlibrary.entity.repositroy.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static io.openlibrary.common.preload.impl.PreloadServiceCsvToBookMaster.mapperCsvToEntity;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PreloadLauncher {

    @Value("${preload.activate}")
    private boolean preloadActivate;


    @Bean
    @Profile(value = {"test", "demo"})
    CommandLineRunner accountInitDatabase(AccountRepository repository) {
        return args -> {
            if (!preloadActivate) return;
            log.info("Preloading " + repository.save(new Accounts("", "1234", "")));
            log.info("Preloading " + repository.save(new Accounts("", "0000", "")));
        };
    }

    @Bean
    @Profile(value = {"test", "demo"})
    CommandLineRunner adminInitDatabase(AdministratorRepository repository) {
        return args -> {
            if (!preloadActivate) return;
            log.info("Preloading " + repository.save(new Administrator("faraday.m")));
            log.info("Preloading " + repository.save(new Administrator("feynman.r")));
            log.info("Preloading " + repository.save(new Administrator("kelly.j")));
        };
    }

    @Bean
    @Profile(value = {"test", "demo"})
    CommandLineRunner reservationInitDatabase(ReservationRepository repository) {
        return args -> {
            if (!preloadActivate) return;
            log.info("Preloading " + repository.save(new Reservation(1L, 1L)));
            log.info("Preloading " + repository.save(new Reservation(2L, 2L)));
            log.info("Preloading " + repository.save(new Reservation(3L, 3L)));
        };
    }

    //페이즈에 따라서, 컨피그설정에 따라서 추가하냐마냐 결정하기
    @Bean
    @Profile(value = {"test", "demo"})
    CommandLineRunner bookAdd(BookMasterRepository bookMasterRepository, PreloadServiceCsvToBookMaster<BookMaster> preloadServiceCsvToBookMaster) {
        return args -> {
            if(!preloadActivate) return;
            preloadServiceCsvToBookMaster.savePreload(bookMasterRepository,
                    preloadServiceCsvToBookMaster.initPreload("dataset", "daejeon-sample-202212.csv"),
                    BookMaster.class, mapperCsvToEntity());
            log.info("Preloading bookMaster Sample Done. count=[{}]", bookMasterRepository.count());

//            preloadServiceCsvToBookMaster.savePreload(bookMasterRepository,
//                    preloadServiceCsvToBookMaster.initPreload("dataset", "daejeon-202212.csv"),
//                    BookMaster.class, mapperCsvToEntity());
//            log.info("Preloading bookMaster 1/2 Done. count=[{}]", bookMasterRepository.count());
//            preloadServiceCsvToBookMaster.savePreload(bookMasterRepository,
//                    preloadServiceCsvToBookMaster.initPreload("dataset", "pohang-202212.csv"),
//                    BookMaster.class, mapperCsvToEntity());
//            log.info("Preloading bookMaster 2/2 Done. count=[{}]", bookMasterRepository.count());


        };
    }
}
//https://spring.io/guides/tutorials/rest/