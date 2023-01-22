package io.openlibrary.common.preload.impl;

import com.opencsv.CSVReader;
import io.openlibrary.common.preload.PreloadService;
import io.openlibrary.common.preload.component.PreloadException;
import io.openlibrary.common.preload.component.PreloadHandler;
import io.openlibrary.common.preload.component.PreloadUtils;
import io.openlibrary.entity.domain.BookMaster;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static io.openlibrary.common.preload.component.PreloadException.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class PreloadServiceCsvToBookMaster<T> implements PreloadService<T> {

    private final PreloadUtils preloadUtils;

    @Override
    public PreloadHandler initPreload(String path, String fileName) {
        ClassPathResource resource = new ClassPathResource(preloadUtils.makePath(path, fileName));
        try (CSVReader reader = new CSVReader(new FileReader(resource.getFile().getAbsoluteFile()))) {
            String[] headers = reader.readNext();
            String location = resource.getFilename();
            return new PreloadHandler(resource, location, headers);
        } catch (IOException ioException) {
            log.error("IOException = [{}]", ioException.getMessage());
            ioException.printStackTrace();
            throw new PreloadException(INIT_FAIL);
        }
    }

    @Override
    public List<String[]> readPreload(PreloadHandler handler) {
        throw new PreloadException(NOT_YET_IMPL);
    }


    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void savePreload(JpaRepository<T, Long> jpaRepository, PreloadHandler preloadHandler, Class<T> saveType, Function<? super String[], ? extends T> mapper) {
        Set<String> isbnSet = new HashSet<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(preloadHandler.getResource().getInputStream()))) {
            reader.skip(1);
            reader.iterator().forEachRemaining(csvLine -> {
                jpaRepository.save(TypeMapping(saveType, csvLine, mapper));
            });
        } catch (IOException e) {
            log.error("pass... IOException ");
            //throw new PreloadException("Fail to save csv");
        } catch (Exception e) {
            e.printStackTrace();
            //e.getCause();
            log.error("pass... [{}]", e.getMessage());
        }
    }

    private T TypeMapping(Class<T> saveType, String[] csvLine, Function<? super String[], ? extends T> mapper) {
        return mapper.apply(csvLine);
    }

    @Override
    public List<String> headerPreloadInfo(PreloadHandler handler) {
        return Arrays.asList(handler.getHeaders());
    }

    @Override
    public void writeAfter(List<String[]> writeData) {
        throw new PreloadException(NOT_YET_IMPL);
    }


    public static Function<? super String[], ? extends BookMaster> mapperCsvToBookMaster() {

        return strings -> {
            BookMaster.BookMasterBuilder builder = BookMaster.builder();
            builder.title(strings[1]);
            builder.author(validAuthoer(strings[2]));
            builder.publisher(strings[3]);
            builder.publicationYear(validYear(strings[4]));
            builder.isbnCode(strings[5]);
            return builder
                    .build();
            //0-번호,
            // 1-도서명,
            // 2저자,
            // 3출판사,
            // 4발행년도,
            // 5ISBN,
            // 6세트 ISBN,
            // 7부가기호,
            // 8권,
            // 9주제분류번호,
            // 10도서권수,
            // 11대출건수,
            // 12등록일자,

        };
    }

    private static String validAuthoer(String string) {
        if (string.length() > 100) {
            return string.substring(0, 99);
        }
        return string;
    }

    private static Integer validYear(String string) {
        if (string.isBlank()) {
            return 0;
        }
        if (string.length() == 4 && isNumber(string.charAt(0)) && isNumber(string.charAt(3))) {
            return Integer.parseInt(string);
        }
        if (string.length() == 5 && string.endsWith("-")) {
            return Integer.parseInt(string.substring(0, 3));
        }
        return 0;
        //string.startsWith("-")
    }

    private static boolean isNumber(char c) {
        return ('0' <= c) && (c <= '9');
    }

}
