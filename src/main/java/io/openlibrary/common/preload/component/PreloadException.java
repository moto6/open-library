package io.openlibrary.common.preload.component;

public class PreloadException extends RuntimeException{
    public static final String INIT_FAIL = "init Fail";
    public static final String NOT_YET_IMPL = "Features not yet implemented";
    public PreloadException(String message) {
        super("[preload] Fail to Preload : " + message);
    }
}
