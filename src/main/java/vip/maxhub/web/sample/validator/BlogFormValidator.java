package vip.maxhub.web.sample.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vip.maxhub.web.sample.model.BlogForm;

/**
 * BlogForm校验器
 * Created by jinlei on 2017/4/18.
 */
public class BlogFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BlogForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {



    }
}
