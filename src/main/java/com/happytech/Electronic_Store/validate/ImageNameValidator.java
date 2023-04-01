package com.happytech.Electronic_Store.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid, String> {
    private Logger logger= LoggerFactory.getLogger(getClass());
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("Messege from isvalid:{}",value);

        if(value.isBlank()){
            return false;
        }else {
            return true;
        }


    }


}
