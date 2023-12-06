package com.prevalentware.monitoringapp.common;

import com.prevalentware.monitoringapp.constants.Constants;
import com.prevalentware.monitoringapp.errors.ForbiddenException;
import com.prevalentware.monitoringapp.errors.NotAcceptableException;
import com.prevalentware.monitoringapp.errors.ResourceNotFoundException;
import com.prevalentware.monitoringapp.model.RoleNameEnum;
import com.prevalentware.monitoringapp.model.User;
import org.apache.coyote.BadRequestException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class GuardClauses {

    public static void guardAgainstUnauthorizedUserEmail(User authenticatedUser, String userEmail) throws ForbiddenException {
        if (authenticatedUser.getRole().getName() == RoleNameEnum.User && !authenticatedUser.getEmail().equals(userEmail)) {
            throw new ForbiddenException(Constants.ERROR_FORBIDDEN_EMAIL);
        }
    }
    public static void guardAgainstUserNotFound(Optional<User> user) throws ResourceNotFoundException {
        if (user.isEmpty()) throw new ResourceNotFoundException(Constants.ERROR_NOT_FOUND);
    }

}

