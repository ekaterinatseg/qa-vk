package org.tsegelnikova.forms.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.tsegelnikova.util.ConfigUtil;

public class MyProfile extends Form {
    private final IButton btnLike;
    private final String ownerId = ConfigUtil.getSettingDataByName("/owner_id");

    public MyProfile(String id) {
        super(By.xpath("//*[@id='index_email']"), "My Profile");
        btnLike = getElementFactory().getButton(By.xpath("//*[@id='post" + ownerId + "_" + id + "']//div[contains(@class, 'PostButtonReactions') and contains(@class, 'icon')]"), "Like");
    }

    public void clickLikeBtn() {
        btnLike.clickAndWait();
    }

    public void clickShowCommentsBtn(String id) {
        AqualityServices.getElementFactory().getButton(By.xpath("//*[@id='replies" + ownerId + "_" + id + "']"), "Comment").clickAndWait();
    }

    public String getTextFromPost(int number, String postId) {
        String[] postArray = AqualityServices.getBrowser().getDriver().findElement(By.id("post" + ownerId + "_" + postId )).getText().split("\n");
        return postArray[number];
    }

}
