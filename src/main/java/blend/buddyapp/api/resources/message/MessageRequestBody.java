package blend.buddyapp.api.resources.message;

import javax.validation.constraints.NotNull;

public class MessageRequestBody {
    public Long from;
    public Long to;
    @NotNull
    public String message;
}
