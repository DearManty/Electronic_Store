package com.happytech.Electronic_Store.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    //error message
    String message() default "Invalid Image Name";

    // represt group of Constraint
    Class<?>[] groups() default {};

    // addition information about validation
    Class<? extends Payload>[] payload() default {};

}
