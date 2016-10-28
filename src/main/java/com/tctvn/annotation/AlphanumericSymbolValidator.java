package com.tctvn.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

import com.tctvn.utils.MessageUtil;

public class AlphanumericSymbolValidator implements ConstraintValidator<AlphanumericSymbol, String>{
    private String[] arg;
    private String message;
    
    @Override
    public void initialize(AlphanumericSymbol arg0) {
        this.arg = arg0.arg();
        this.message = arg0.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (!isAlphaNumeric(value)) {
            arg1.disableDefaultConstraintViolation();
            arg1.buildConstraintViolationWithTemplate(MessageUtil.getMessage(message, arg))
                .addConstraintViolation();
            return false;
        }
        return true;
    }
    
    public boolean isAlphaNumeric(String s) {
        if (StringUtils.isBlank(s)) {
            return true;
        }
        String pattern= "^[a-zA-Z0-9]*$";
        if(s.matches(pattern)){
            return true;
        }
        return false;   
    }
}
