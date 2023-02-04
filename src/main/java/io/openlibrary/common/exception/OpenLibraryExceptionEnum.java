package io.openlibrary.common.exception;


public enum OpenLibraryExceptionEnum implements ExceptionCode{
    ENTITY_NOT_FOUND("엔티티에 존재하지 않는 데이터 입니다"),
    UNEXPECTED("알수없는에러");

    OpenLibraryExceptionEnum(String description) {
        this.description = description;
    }

    private final String description;

    @Override
    public String getCode() {
        return this.name();
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}


