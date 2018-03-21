package com.epam.brest.course.rest;

import com.epam.brest.course.model.Department;
import com.epam.brest.course.service.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
public class RestErrorHandler {

    private static final Logger LOGGER = LogManager.getLogger();



    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody String handleDataAccessException(DataAccessException e) {
        LOGGER.debug("handleDataAccessException({})", e);
        return "Data access exception: " + e.getLocalizedMessage();
    }


}
