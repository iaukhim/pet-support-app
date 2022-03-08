package com.unknown.supportapp.utils.account;

import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountUtils {

    public static boolean checkAccountFormat(String email, String password){
        if(!checkEmailFormat(email)){
            return false;
        }

        if(!checkPasswordFormat(password)){
            return false;
        }
        return true;
    }

    public static boolean checkPasswordFormat(String password){
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])"
                + "(?=\\S+$).{6,20}$";

        Pattern p = Pattern.compile(regex);

        if (password == null) {
            return false;
        }

        Matcher m = p.matcher(password);

        return m.matches();
    }

    public static boolean checkEmailFormat(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
