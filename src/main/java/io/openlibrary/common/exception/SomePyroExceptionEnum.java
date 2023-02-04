package io.openlibrary.common.exception;


public enum SomePyroExceptionEnum implements ExceptionCode{

    REASON1("이유없는이유"),
    REASON2("이유있는이유");

    SomePyroExceptionEnum(String description) {
        this.description = description;
    }

    private final String description;

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDescription() {
        return this.description;
    }
}
