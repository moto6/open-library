package io.openlibrary.common.preload.impl;

import com.opencsv.CSVReader;
import io.openlibrary.common.preload.PreloadService;
import io.openlibrary.common.preload.component.PreloadException;
import io.openlibrary.common.preload.component.PreloadHandler;
import io.openlibrary.common.preload.component.PreloadUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static io.openlibrary.common.preload.component.PreloadException.*;

@RequiredArgsConstructor
@Service
@Slf4j
public class PreloadServiceCsvImpl<T> implements PreloadService<T> {

    @Value("${preload.filename}")
    String preloadFilename;
    @Value("${preload.path}")
    String preloadPath;

    private final ResourceLoader resourceLoader;

    private final PreloadUtils preloadUtils;

    @Override
    public PreloadHandler initPreload() {
        ClassPathResource resource = new ClassPathResource(preloadUtils.makePath(preloadPath, preloadFilename));
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
        //return handler.getHeaders();
        throw new PreloadException(NOT_YET_IMPL);
    }


    @Override
    @Transactional
    public void savePreload(JpaRepository<T, Long> jpaRepository, PreloadHandler preloadHandler, Class<T> saveType, Function<? super String[], ? extends T> mapper) {
        Set<String> isbnSet = new HashSet<>();
        try (CSVReader reader = new CSVReader(new InputStreamReader(preloadHandler.getResource().getInputStream()))) {
            reader.skip(1);
            reader.iterator().forEachRemaining(csvLine -> {
                //isbnSet.contains(csvLine[])
                jpaRepository.save(TypeMapping(saveType, csvLine, mapper));
            });
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            e.getCause();
            throw new PreloadException("Fail to save csv");
        } catch (ConstraintViolationException | DataIntegrityViolationException e ) {
            log.error("PASS : []");
        } catch (Exception e) {
            log.error("pass...");
        }
    }

    //todo type mapping error 체크해보기
    private T TypeMapping(Class<T> saveType, String[] csvLine, Function<? super String[], ? extends T> mapper) {
        return mapper.apply(csvLine);
    }

    @Override
    public List<String> headerPreloadInfo(PreloadHandler handler) {
        //throw new PreloadException(NOT_YET_IMPL);
        return Arrays.asList(handler.getHeaders());
    }

    @Override
    public void writeAfter(List<String[]> writeData) {
        throw new PreloadException(NOT_YET_IMPL);
    }

}
