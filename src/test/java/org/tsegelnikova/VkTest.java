package org.tsegelnikova;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.tsegelnikova.base.BaseTest;
import org.tsegelnikova.dto.*;
import org.tsegelnikova.forms.pages.AuthorizationForm;
import org.tsegelnikova.forms.pages.LeftMenu;
import org.tsegelnikova.forms.pages.MyProfile;
import org.tsegelnikova.util.*;

import java.io.File;
import java.util.UUID;

public class VkTest implements BaseTest {

    @Test
    public void testVk() {
        Long albumId = Long.parseLong(ConfigUtil.getSettingDataByName("/album_id"));
        MyResponse<String> uploadServer = ApiAppRequest.getUploadServer(albumId);
        File baseImage = FileUtil.getFileFromResource(ConfigUtil.getTestDataByName("/imageFileName"));
        MyResponse<UploadPhotoInfo> uploadPhotoInfo = ApiAppRequest.uploadPhoto(uploadServer.getResponseDate(), baseImage);
        MyResponse<Photo> savedPhoto = ApiAppRequest.savePhoto(albumId, uploadPhotoInfo.getResponseDate());

        AqualityServices.getLogger().info("Step 1 :: Navigating to Main Page");
        AqualityServices.getLogger().info("Step 2 :: Authorization");
        AuthorizationForm authorizationForm = new AuthorizationForm();
        authorizationForm.performAuthorization(ConfigUtil.getSettingDataByName("/username"), ConfigUtil.getSettingDataByName("/password"));

        AqualityServices.getLogger().info("Step 3 :: Going to My Profile");
        LeftMenu leftMenu = new LeftMenu();
        leftMenu.clickMyPageButton();

        AqualityServices.getLogger().info("Step 4 :: Creating new post");
        String randomStringOne = UUID.randomUUID().toString();
        MyResponse<Post> createdPost = ApiAppRequest.createPostWith(randomStringOne);
        String stringPostId = createdPost.getResponseDate().getPost_id().toString();
        By postElementId= By.id("post" + ConfigUtil.getSettingDataByName("/owner_id")  + "_" + stringPostId);
        MyProfile myProfile = new MyProfile(stringPostId);
        AqualityServices.getConditionalWait().waitFor(ExpectedConditions.visibilityOfElementLocated(postElementId));
        ScrollUtil.scrollToElement(postElementId);

        AqualityServices.getLogger().info("Step 5 :: Checking existing of post");
        Assert.assertEquals(myProfile.getTextFromPost(2, stringPostId), randomStringOne, "Not equal");
        Assert.assertEquals(myProfile.getTextFromPost(0, stringPostId), "Jane Doe", "Not equal");

        AqualityServices.getLogger().info("Step 6 :: Editing created post");
        String randomStringTwo = UUID.randomUUID().toString();
        MyResponse<Post> editPost = ApiAppRequest.editPost(createdPost.getResponseDate().getPost_id(), randomStringTwo, savedPhoto.getResponseDate().getId().toString());
        MyResponse<String> attachmentPostImage = ApiAppRequest.getPost(createdPost.getResponseDate().getPost_id());
        File downoladFile = FileUtil.downloadFile(attachmentPostImage.getResponseDate());

        AqualityServices.getLogger().info("Step 7 :: Checking image and new text");
        Assert.assertTrue(CompareImageUtil.compareTwoImage(baseImage, downoladFile) >= Double.parseDouble(ConfigUtil.getTestDataByName("/imageSimilarity")), "Images are different");
        String postPath = "//*[@id='wpt" + ConfigUtil.getSettingDataByName("/owner_id") + "_" + stringPostId + "']";
        AqualityServices.getConditionalWait().waitFor(ExpectedConditions.textToBe(By.xpath(postPath), randomStringTwo));
        Assert.assertEquals(myProfile.getTextFromPost(2, createdPost.getResponseDate().getPost_id().toString()), randomStringTwo, "Not equal");

        AqualityServices.getLogger().info("Step 8 :: Creating new comment");
        String randomStringThree = UUID.randomUUID().toString();
        MyResponse<Comment> createdComment = ApiAppRequest.createComment(editPost.getResponseDate().getPost_id(), randomStringThree);
        By commentXpath = By.xpath("//*[@id='wpt" + ConfigUtil.getSettingDataByName("/owner_id") + "_" + createdComment.getResponseDate().getComment_id() + "']");
        myProfile.clickShowCommentsBtn(stringPostId);
        AqualityServices.getConditionalWait().waitFor(ExpectedConditions.textToBe(commentXpath, randomStringThree));
        String commentText = AqualityServices.getBrowser().getDriver().findElement(commentXpath).getText();
        AqualityServices.getLogger().info("Step 9 :: Checking the comment");
        Assert.assertEquals(commentText, randomStringThree);

        AqualityServices.getLogger().info("Step 10 :: Clicking Like button");
        myProfile.clickLikeBtn();
        MyResponse<Like> likes = ApiAppRequest.getLikes(editPost.getResponseDate().getPost_id());
        AqualityServices.getLogger().info("Step 11 :: Checking the like");
        Assert.assertEquals(likes.getResponseDate().getCount(), 1);
        Assert.assertEquals(likes.getResponseDate().getUsers().get(0).getUid().toString(), ConfigUtil.getSettingDataByName("/owner_id"));

        AqualityServices.getLogger().info("Step 12 :: Deleting post");
        MyResponse<Long> deleted = ApiAppRequest.deletePost(editPost.getResponseDate().getPost_id());
        Assert.assertEquals(deleted.getResponseDate(), 1L);
        WebElement crPost = AqualityServices.getBrowser().getDriver().findElement(postElementId);
        AqualityServices.getLogger().info("Step 13 :: Checking if the post deleted");
        AqualityServices.getConditionalWait().waitFor(ExpectedConditions.invisibilityOfElementLocated(postElementId));
        Assert.assertFalse(crPost.isDisplayed(), "True");
    }
}
