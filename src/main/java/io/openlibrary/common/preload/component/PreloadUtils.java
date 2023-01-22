package io.openlibrary.common.preload.component;

import org.springframework.stereotype.Component;

@Component
public class PreloadUtils {
    public String makePath(String path, String filename) {
        return suffixValidate(path) + "/" + prefixValidate(filename);
    }

    private String suffixValidate(String origin) {
        final String preventedSuffix = "/";
        return origin.endsWith(preventedSuffix) ?
                origin.substring(preventedSuffix.length()) : origin;
    }

    private String prefixValidate(String origin) {
        final String preventedPrefix = "/";
        return origin.startsWith(preventedPrefix) ?
                origin.substring(preventedPrefix.length(), preventedPrefix.length()) : origin;
    }
}
