/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Vinay Patel
 */
package com.algonquincollege.cst8277.customers2.jsf;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Description: Email validator for a simple email pattern
 * using Matcher and regex
 * 
 */
@SessionScoped
@FacesValidator("emailValidator")
public class EmailValidator implements Validator<String>, Serializable{

    // really really (!) simple email pattern: at-least-1-letter, '@', at-least-1-letter
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(.+)@(.+)$");
    
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        
        if (value == null) {
            FacesMessage msg = new FacesMessage("Email should not be empty", "Invalid Email format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        Matcher match = EMAIL_PATTERN.matcher(value);
        if(!match.matches()) {//if not matched with above regex pattern will give an error message
            FacesMessage msg = new FacesMessage("Email should be Valid", "Invalid Email format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
		
    }

}