package com.amochalo.error;

import java.util.Set;

public class UsersUnSupportedFieldPatchException extends RuntimeException {

    public UsersUnSupportedFieldPatchException(Set<String> keys) {
        super("Field " + keys.toString() + " update is not allow.");
    }

}
