package io.openlibrary.common.preload;

import io.openlibrary.common.preload.component.PreloadHandler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public interface PreloadService<T> {
    PreloadHandler initPreload(String path, String filename);
    List<String[]> readPreload(PreloadHandler handler);
    void savePreload(JpaRepository<T, Long> jpaRepository, PreloadHandler preloadHandler, Class<T> saveType, Function<? super String[], ? extends T> csvMapper);
    List<String> headerPreloadInfo(PreloadHandler handler);
    void writeAfter(List<String[]> writeData);
}
