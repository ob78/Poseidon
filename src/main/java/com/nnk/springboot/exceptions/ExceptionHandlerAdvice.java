package com.nnk.springboot.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Class in charge of managing some exceptions that may happen.
 */
@ControllerAdvice(basePackages = { "com.nnk.springboot" })
public class ExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    /**
     * Method managing the RessourceNotFoundException.
     *
     * @param e The exception
     */
/*    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<String> handleException(RessourceNotFoundException e) {

        logger.error("Error : ressource {} not found", e.getId());

        return new ResponseEntity<>(e.getMessage() + e.getId(), e.getHttpStatus());
    }
*/
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ModelAndView handleException(ResourceNotFoundException e) {

        logger.error("Error : resource {} not found", e.getId());

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("id", e.getId());
        mav.setViewName("errorResourceNotFound");
        return mav;

    }

    /**
     * Method managing the RessourceAlreadyExistException.
     *
     * @param e The exception
     */
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<String> handleException(ResourceAlreadyExistException e) {

        logger.error("Error : ressource {} already exist", e.getId());

        return new ResponseEntity<>(e.getMessage() + e.getId(), e.getHttpStatus());
    }

    /**
     * Method managing the InternalServerErrorException.
     *
     * @param e The exception
     */
    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<String> handleException(InternalServerErrorException e) {

        logger.error("Error during the operation");

        return new ResponseEntity<>(e.getMessage(), e.getHttpStatus());
    }

}