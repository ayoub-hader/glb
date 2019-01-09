package fr.cfdt.gasel.groupeldap.resource.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import fr.cfdt.gasel.groupeldap.exception.InvalidInputException;
import fr.cfdt.gasel.groupeldap.exception.TechnicalException;

/**
 * Account Resource ExceptionHandler.
 */
@ControllerAdvice
public class CustomizedExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizedExceptionHandler.class);

    /**
     * On invalid input exception vnd errors.
     *
     * @param e the e
     * @return the vnd errors
     */
    @ExceptionHandler(InvalidInputException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public VndErrors onInvalidInputException(InvalidInputException e) {
        return buildVndErrors(e);
    }


    /**
     * On technical exception vnd errors.
     *
     * @param e the e
     * @return the vnd errors
     */
    @ExceptionHandler(TechnicalException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public VndErrors onTechnicalException(TechnicalException e) {
        return buildVndErrors(e);
    }

    private VndErrors buildVndErrors(Exception e) {
        LOGGER.error("{}", e.getMessage());
        String logRef = e.getClass().getSimpleName();
        String msg = e.getMessage();
        return new VndErrors(logRef, msg);
    }

}
