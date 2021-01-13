package blend.buddyapp.api.helpers;

import blend.buddyapp.api.resources.profiles.Profile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class HelperMethods {

    public static  <T> void patch(T object, Map<String, Object> updates){
        Arrays.stream(Profile.class.getDeclaredFields()).forEach(
                field -> {
                    String name = field.getName();
                    if (updates.containsKey(name) && !name.equals("Id") && field.getType().getName().startsWith("java.lang")){
                        Object value =  updates.get(name);
                        try {
                            field.setAccessible(true);
                            field.set(object, value);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );


    }

    public static <T> String  JsonStringIgnoreProperties(List<String> ignored, T object) throws JsonProcessingException {

        return "";
    }
}
