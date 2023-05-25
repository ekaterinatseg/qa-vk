package org.tsegelnikova.forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class LeftMenu extends Form {
    private final IButton btMyProfile = getElementFactory().getButton(By.xpath("//*[@id='l_pr']/a"), "My Profile Button");

    public LeftMenu() {
        super(By.xpath("//*[@id='side_bar_inner']"), "Left Menu");
    }

    public void clickMyPageButton() {
        btMyProfile.clickAndWait();
    }

}
