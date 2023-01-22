package io.openlibrary.common.preload.component;

import org.springframework.core.io.Resource;

public class PreloadHandler {

    private final Resource resource;
    private final String location;
    private final String[] headers;

    public PreloadHandler(Resource resource, String location, String[] headers) {
        this.resource = resource;
        this.location = location;
        this.headers = headers;
    }

    public Resource getResource() {
        return resource;
    }

    public String getLocation() {
        return location;
    }

    public String[] getHeaders() {
        return headers;
    }
}
