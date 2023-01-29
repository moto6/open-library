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

import java.util.Arrays;
import java.util.List;

import static io.openlibrary.common.preload.impl.PreloadServiceCsvToBookMaster.mapperCsvToEntity;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class PreloadLauncher {

    private static final List<String> bookMasterDataset = Arrays.asList("Andong-202212.csv", "Ansan-202212-aa.csv", "Ansan-202212-ab.csv", "Asan-202212.csv", "Busan-202212.csv", "BusanCentral-202212-aa.csv", "BusanCentral-202212-ab.csv", "Cheonan-202212.csv", "EverGyeonggi-202212-aa.csv", "EverGyeonggi-202212-ab.csv", "Gangnam-202212.csv", "Gunpo-202212_aa.csv", "Gunpo-202212_ab.csv", "Gwanak-202212.csv", "Gwangju-202212_aa.csv", "Gwangju-202212_ab.csv", "Gyeonggi-202212-aa.csv", "Gyeonggi-202212-ab.csv", "Hwaseong-202212.csv", "Mapo-202212.csv", "Nowon-202212.csv", "Osan-202212.csv", "Paju-202212.csv", "Pochen-202212.csv", "Pohang-202212.csv", "Sejong-202212-aa.csv", "Sejong-202212-ab.csv", "Sejong-202212-ac.csv", "Seongnam-202212.csv", "Seoul-202212-aa.csv", "Seoul-202212-ab.csv", "Siheung-202212.csv", "Suwon-202212.csv", "Taean-202212.csv", "Uiwang-202212.csv", "Ulsan-202212.csv", "Wonju-202212.csv", "Yangcheon-202212.csv", "Yangsan-202212.csv", "Yongin-202212.csv", "Yongsan-202212.csv", "dongtan-202212.csv", "incheon-202212_aa.csv", "incheon-202212_ab.csv", "jatuli-paju-wonju.csv", "jeju-202212.csv");

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

    @Bean
    @Profile(value = {"test", "demo"})
    CommandLineRunner bookMasterAddSample(BookMasterRepository bookMasterRepository, PreloadServiceCsvToBookMaster<BookMaster> preloadServiceCsvToBookMaster) {
        return args -> {
            if (!preloadActivate) return;

            preloadServiceCsvToBookMaster.savePreload(bookMasterRepository,
                    preloadServiceCsvToBookMaster.initPreload("dataset", "sample-202212.csv"),
                    BookMaster.class, mapperCsvToEntity());
            log.info("Preloading bookMaster Sample Done. count=[{}]", bookMasterRepository.count());
        };
    }

    @Bean
    @Profile(value = {"test", "demo"})
    CommandLineRunner bookAdd(BookMasterRepository bookMasterRepository, PreloadServiceCsvToBookMaster<BookMaster> preloadServiceCsvToBookMaster) {
        return args -> {
            if (false) {
                log.info("Saving the entire dataset is risky");
                return;
            }
            bookMasterDataset.parallelStream().forEach(datasetFileName -> {
                preloadServiceCsvToBookMaster.savePreload(bookMasterRepository,
                        preloadServiceCsvToBookMaster.initPreload("dataset", datasetFileName),
                        BookMaster.class, mapperCsvToEntity());
                log.info("Preloading bookMaster Sample Done. count=[{}]", bookMasterRepository.count());
            });
        };
    }
}
//https://spring.io/guides/tutorials/rest/