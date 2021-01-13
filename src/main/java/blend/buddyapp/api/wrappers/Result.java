package blend.buddyapp.api.wrappers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public  class Result<T> {

    public T getObject() {
        return object;
    }

    public HttpStatus getStatus() {
        return status;
    }

    private final T object;
     private final HttpStatus status;

     public Result(T object, HttpStatus status ) {
         this.object = object;
         this.status = status;
     }

     public boolean isError(){
         return this.status.isError();
     }

     public  ResponseEntity<?> toResponseEntity(){
         return new ResponseEntity<>(this.object, this.status);
     }
 }
