package site.metacoding.blogv3._core.exception;

public class ApiException500 extends RuntimeException {
    public ApiException500(String msg) {
        super(msg);
    }
}