package com.moveingroup.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import lombok.Data;

@Named
@Data
public class HelloWorld {

  private String firstName = "John";
  private String lastName = "Doe";
  private String message;

  public String showGreeting() {
    return "Hello " + firstName + " " + lastName + "!";
  }
  
   
  public void saveMessage() {
      FacesContext context = FacesContext.getCurrentInstance();
       
      context.addMessage(null, new FacesMessage("Successful",  "Your message: " + message) );
      context.addMessage(null, new FacesMessage("Second Message", "Additional Message Detail"));
  }
}
