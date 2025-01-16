package Hope.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {
    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(HttpStatus.NOT_FOUND, String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
