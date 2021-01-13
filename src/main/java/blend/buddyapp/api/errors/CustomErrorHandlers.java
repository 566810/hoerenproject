package blend.buddyapp.api.errors;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;



@ControllerAdvice
public class CustomErrorHandlers {

    //@ExceptionHandler(HttpMedi)

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<CustomError> VerkeerdeRequestBody (MethodArgumentNotValidException ex ){
        List<String> errorList = new ArrayList<String>();
        ex.getBindingResult().getFieldErrors().forEach(l -> errorList.add(l.getField() +  " : " + l.getDefaultMessage()));
        CustomError customError = new CustomError("You are missing expected parameters");
        customError.setErrors(errorList);
        return new ResponseEntity<CustomError>(customError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<CustomError> gg(HttpMessageNotReadableException ex){
        Throwable rootCause = ex.getRootCause();
        String message = rootCause.getMessage();
        CustomError error;
        if(rootCause instanceof  JsonParseException && message.contains("was expecting comma to separate Object entries"))
            error = new CustomError("JSON parser was expecting comma to separate Object entries");
        else if (message.contains("was expecting a colon to separate field name and value"))
            error = new CustomError("JSON parser was expecting comma to separate Object entries" );
        else if (message.contains(" was expecting double-quote to start field name"))
            error = new CustomError("Please, place property/field names between double quotes" );
        else if (message.contains("Unrecognized token 'as': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false')"))
            error = new CustomError("Unrecognized token 'as': was expecting (JSON String, Number, Array, Object or token 'null', 'true' or 'false");
        else {
            error = new CustomError("unknown json parse error, please read something on the internet regarding json syntax ");
        }
        return new ResponseEntity<CustomError>( error,  HttpStatus.BAD_REQUEST);
    }


}

