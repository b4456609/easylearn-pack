package soselab.easylearn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by bernie on 10/23/16.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Pack not found")
public class PackNotFoundException  extends RuntimeException {
}
