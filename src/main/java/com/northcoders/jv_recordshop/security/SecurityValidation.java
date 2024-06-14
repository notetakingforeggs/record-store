package com.northcoders.jv_recordshop.security;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecurityValidation {
    public boolean validateEmailInput(String emailInput){
        if(emailInput.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
            return true;
        }else return false;
    }

    public boolean validatePasswordInput(String passwordInput){
        Pattern pattern = Pattern.compile("[!@#$%^&*]");
        Matcher matcher = pattern.matcher(passwordInput);
        if(passwordInput.length()< 8){
            System.out.println("to short pass");
            return false;
        } else if (!matcher.find()) {
            System.out.println("the pass must contain any special character !@#$%^&*");
            return false;
        }
        return true;
    }
}
