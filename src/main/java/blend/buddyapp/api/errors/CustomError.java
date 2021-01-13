package blend.buddyapp.api.errors;

import java.util.List;

public class CustomError {

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    private String message;

    private List<String> errors;


    public List<String> getErrors() {
        return errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomError(String message) {
        this.message = message;
    }
    public CustomError(String message, List<String> errors) {
        this.message = message;
        this.errors = errors; 
    }








}
