package com.cs371group2.admin;

import com.cs371group2.ValidationResult;
import com.cs371group2.account.Account;
import com.cs371group2.concern.Concern;
import com.cs371group2.concern.ConcernDao;
import com.google.api.server.spi.config.*;
import com.google.api.server.spi.response.BadRequestException;
import com.google.api.server.spi.response.NotFoundException;
import com.google.api.server.spi.response.UnauthorizedException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This AdminApi class serves as a backend endpoint to be accessed through the administrative website.
 *
 * Created on 2017-02-08.
 */
@Api(name = "admin",
        title = "Admin API",
        version = "v1")
public class AdminApi {

    /** Logger statement for the class */
    private static final Logger logger = Logger.getLogger( AdminApi.class.getName() );

    /**
     * Requests a list of concerns from the datastore with the request offset and limit. The user submitting
     * the request must have administrative permissions which will be verified for the request.
     *
     * @param request The concern request containing the user's firebase token, along with the requested offset/limit
     * @return A list of concerns loaded from the datastore at the given offset and limit
     * @throws UnauthorizedException If the admin is unauthorized or there is an error loading the concern list
     * @throws BadRequestException If the request contained invalid paging information.
     */
    @ApiMethod(name = "requestConcernList", path = "admin/requestConcernList")
    public List<Concern> requestConcernList(ConcernListRequest request) throws UnauthorizedException, BadRequestException {

        ValidationResult result = request.validate();
        if (!result.isValid()){
            logger.log(Level.WARNING, "Admin tried requesting a concern list with invalid data.");
            throw new BadRequestException(result.getErrorMessage());
        }

        Account account = request.authenticate();
        assert account != null;
        logger.log(Level.INFO,account + " is requesting a concern " + request);

        ConcernDao dao = new ConcernDao();
        List<Concern> list = dao.load(account, request.getOffset(), request.getLimit());

        logger.log(Level.INFO, "Concern list request was successful.");
        return list;
    }

    /**
     * Requests an individual concern from the database based on the given unique concern id. The user submitting
     * the request must have administrative permissions which will be verified before the request is completed.
     *
     * @param request The concern request containing the user's firebase token, along with the desired concern id
     * @return The concern requested from the database
     * @throws UnauthorizedException If the admin is unauthorized or there is an error loading the concern
     * @throws BadRequestException If the request or the admin's account contained invalid information
     * @throws NotFoundException Thrown if the requested concern does not exist.
     * @precond request != null
     */
    @ApiMethod(name = "requestConcern", path = "admin/requestConcern")
    public Concern requestConcern(ConcernRequest request)
            throws UnauthorizedException, BadRequestException, NotFoundException {

        ValidationResult result = request.validate();
        if (!result.isValid()){
            logger.log(Level.WARNING, "Admin tried requesting a concern with invalid data.");
            throw new BadRequestException(result.getErrorMessage());
        }

        Account account = request.authenticate();
        assert account != null;
        logger.log(Level.INFO,account + " is requesting a concern " + request);

        Concern loadedConcern = new ConcernDao().load(account, request.getConcernId());
        logger.log(Level.INFO, "Concern " + loadedConcern + " was successfully loaded!");
        return loadedConcern;
    }
}
