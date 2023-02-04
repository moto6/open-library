package io.openlibrary.common.preload.impl;

import com.opencsv.CSVReader;
import io.openlibrary.common.preload.PreloadService;
import io.openlibrary.common.preload.component.PreloadException;
import io.openlibrary.common.preload.component.PreloadHandler;
import io.openlibrary.common.preload.component.PreloadUtils;
import io.openlibrary.entity.domain.BookMaster;
import io.openlibrary.entity.repositroy.BookMasterRepository;
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
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import static io.openlibrary.common.preload.component.PreloadException.INIT_FAIL;
import static io.openlibrary.common.preload.component.PreloadException.NOT_YET_IMPL;
import static io.openlibrary.entity.domain.BookMaster.cleaningAuthor;
import static io.openlibrary.entity.domain.BookMaster.cleaningKdcCode;
import static io.openlibrary.entity.domain.BookMaster.convertPublicationYear;

@RequiredArgsConstructor
@Service
@Slf4j
public class PreloadServiceCsvToBookMaster<T> implements PreloadService<T> {

    private final PreloadUtils preloadUtils;

    public static Function<? super String[], ? extends BookMaster> mapperCsvToEntity() {

        return strings -> {
            BookMaster.BookMasterBuilder builder = BookMaster.builder();
            builder.title(strings[1]);
            builder.author(cleaningAuthor(strings[2]));
            builder.publisher(strings[3]);
            builder.publicationYear(convertPublicationYear(strings[4]));
            builder.kdcCode(strings[9]);
            builder.isbnCode(cleaningKdcCode(strings[5]));
            return builder.build();
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
        BookMasterRepository bookMasterRepository = (BookMasterRepository) jpaRepository;
        try (CSVReader reader = new CSVReader(new InputStreamReader(preloadHandler.getResource().getInputStream()))) {
            reader.skip(1);
            reader.iterator().forEachRemaining(csvLine -> {
                BookMaster apply = (BookMaster) mapper.apply(csvLine);
                log.info("{}",apply.toString());
                bookMasterRepository.save(apply);
                //bookMasterRepository.insertif((BookMaster) TypeMapping(saveType, csvLine, mapper));
            });
        } catch (IOException e) {
            e.printStackTrace();
            log.error("pass... IOException ");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("pass... [{}]", e.getMessage());
        }
    }

    @Override
    public List<String> headerPreloadInfo(PreloadHandler handler) {
        return Arrays.asList(handler.getHeaders());
    }

    @Override
    public void writeAfter(List<String[]> writeData) {
        throw new PreloadException(NOT_YET_IMPL);
    }

}
