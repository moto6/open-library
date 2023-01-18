package io.openlibrary.common.exception;

public class MyLibraryException extends RuntimeException{
    public MyLibraryException(MyException exception) {
        //super(message);
    }
}
