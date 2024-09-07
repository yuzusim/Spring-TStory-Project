package site.metacoding.blogv3._core.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.metacoding.blogv3._core.exception.*;
import site.metacoding.blogv3._core.util.ApiUtil;

@RestControllerAdvice
public class MyApiExceptionHandler {
    @ExceptionHandler(ApiException400.class)
    public ResponseEntity<?> exApi400(ApiException400 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException401.class)
    public ResponseEntity<?> exApi401(ApiException401 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ApiException403.class)
    public ResponseEntity<?> exApi403(ApiException403 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ApiException404.class)
    public ResponseEntity<?> exApi404(ApiException404 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException500.class)
    public ResponseEntity<?> exApi500(ApiException500 e){
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
