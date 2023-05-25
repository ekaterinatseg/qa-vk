package org.tsegelnikova.forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AuthorizationForm extends Form {
    private final ITextBox txbUsername = getElementFactory().getTextBox(By.xpath("//*[@id='index_email']"), "Username");
    private final ITextBox txbPassword = getElementFactory().getTextBox(By.xpath("//*[@autocomplete='current-password']"), "Password");
    private final IButton btnSignIn = getElementFactory().getButton(By.xpath("//*[@class='FlatButton FlatButton--primary FlatButton--size-l FlatButton--wide VkIdForm__button VkIdForm__signInButton'][@type='submit']"), "Sign In");
    private final IButton btnContinue = getElementFactory().getButton(By.xpath("//*[@class='vkuiButton__in']"), "Continue");

    public AuthorizationForm() {
        super(By.xpath("//*[@id='index_email']"), "Authorization Form");
    }

    public void performAuthorization(String username, String password) {
        txbUsername.clearAndType(username);
        btnSignIn.clickAndWait();
        btnContinue.clickAndWait();
        txbPassword.clearAndType(password);
    }

}
