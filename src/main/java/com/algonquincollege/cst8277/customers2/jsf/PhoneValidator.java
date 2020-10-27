/*****************************************************************c******************o*******v******id********
 * File: EmailValidator.java
 * Course materials (20F) CST 8277
 *
 * @author (original) Mike Norman
 * @author Vinay Patel
 *
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
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 * Description: Phone Number validator for a North American Phone Number pattern
 * using Matcher and regex 
 * 
 */
@SessionScoped
@FacesValidator("phoneValidator")
public class PhoneValidator implements Validator<String>, Serializable{

    // North American phoneNumber pattern
    private static final Pattern PHONE_PATTERN = Pattern.compile("^(\\+\\d( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
    
    @Override
    public void validate(FacesContext context, UIComponent component, String value) throws ValidatorException {
        
        if (value == null) {
            FacesMessage msg = new FacesMessage("Phone Number should not be empty", "Invalid Phone Number format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        
        Matcher match = PHONE_PATTERN.matcher(value);
        if(!match.matches()) { //if not matched with above regex pattern will give an error message
            FacesMessage msg = new FacesMessage("Phone Number should be Valid", "Invalid Phone Number format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        // 
    }

}