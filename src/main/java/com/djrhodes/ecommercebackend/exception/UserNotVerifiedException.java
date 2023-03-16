package com.djrhodes.ecommercebackend.exception;

/**
 * Exception to indicate a user does not have a verified email address.
 */
public class UserNotVerifiedException extends Exception{

    /** Was a new verification email sent? */
    private boolean newEmailSent;

    /**
     * Constructor
     * @param newEmailSent Whether a new email was sent.
     */
    public UserNotVerifiedException(boolean newEmailSent) {
        this.newEmailSent = newEmailSent;
    }

    /**
     * Was a new email sent?
     * @return True if it was, false otherwise.
     */
    public boolean isNewEmailSent() {
        return newEmailSent;
    }
}
