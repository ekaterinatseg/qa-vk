package org.tsegelnikova.util;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.tsegelnikova.dto.*;

import java.io.File;
import java.net.URLEncoder;

public class ApiAppRequest {
    private static final String BASE_ENDPOINT = ConfigUtil.getSettingDataByName("/url_api");
    private static final String ACCESS_TOKEN = ConfigUtil.getSettingDataByName("/access_token");
    private static final String API_VERSION = ConfigUtil.getSettingDataByName("/api_version");
    private static final String OWNER_ID = ConfigUtil.getSettingDataByName("/owner_id");

    private ApiAppRequest() {
        throw new IllegalStateException("Utility class");
    }

    public static MyResponse<Post> createPostWith(String message) {
        String endpointWithParams = "wall.post?owner_id=" + OWNER_ID + "&message=" + message + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        Post post = ParsingUtil.parsingObject(response.getBody().getObject().get("response").toString(), Post.class);
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, post);
    }

    public static MyResponse<Post> editPost(Long postId, String message, String photoId) {
        String endpointWithParams = "wall.edit?post_id=" + postId + "&owner_id=" + OWNER_ID + "&message=" + message + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN + "&attachments=photo793103646_" + photoId;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        Post post = ParsingUtil.parsingObject(response.getBody().getObject().get("response").toString(), Post.class);
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, post);
    }

    public static MyResponse<String> getPost(Long postId) {
        String endpointWithParams = "wall.getById?posts=" + OWNER_ID + "_" + postId + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        String imageUrl = response.getBody().getObject().getJSONArray("response").getJSONObject(0).getJSONArray("attachments").getJSONObject(0).getJSONObject("photo").getJSONArray("sizes").getJSONObject(3).getString("url");
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, imageUrl);
    }

    public static MyResponse<Comment> createComment(Long postId, String message) {
        String endpointWithParams = "wall.createComment?post_id=" + postId + "&owner_id=" + OWNER_ID + "&message=" + message + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        Comment comment = ParsingUtil.parsingObject(response.getBody().getObject().get("response").toString(), Comment.class);
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, comment);
    }

    public static MyResponse<Like> getLikes(Long postId) {
        String endpointWithParams = "wall.getLikes?post_id=" + postId + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        Like like = ParsingUtil.parsingObject(response.getBody().getObject().get("response").toString(), Like.class);
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, like);
    }

    public static MyResponse<Long> deletePost(Long postId) {
        String endpointWithParams = "wall.delete?post_id=" + postId + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        Long l = response.getBody().getObject().getLong("response");
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, l);
    }

    public static MyResponse<String> getUploadServer(Long albumId) {
        String endpointWithParams = "photos.getUploadServer?album_id=" + albumId + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN;
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        String uploadServerUrl = response.getBody().getObject().getJSONObject("response").getString("upload_url");
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, uploadServerUrl);
    }

    public static MyResponse<UploadPhotoInfo> uploadPhoto(String uploadUrl, File image) {
        HttpResponse<JsonNode> response = ApiUtil.postRequest(uploadUrl, image);
        UploadPhotoInfo uploadPhotoInfo = ParsingUtil.parsingObject(response.getBody().getObject().toString(), UploadPhotoInfo.class);
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, uploadPhotoInfo);
    }

    public static MyResponse<Photo> savePhoto(Long albumId, UploadPhotoInfo uploadPhotoInfo) {
        String endpointWithParams = "photos.save?album_id=" + albumId + "&v=" + API_VERSION + "&access_token=" + ACCESS_TOKEN + "&server=" + uploadPhotoInfo.getServer() + "&hash=" + uploadPhotoInfo.getHash() + "&photos_list=" + URLEncoder.encode(uploadPhotoInfo.getPhotos_list());
        HttpResponse<JsonNode> response = ApiUtil.getRequest(BASE_ENDPOINT, endpointWithParams);
        Photo photo = ParsingUtil.parsingObject(response.getBody().getObject().getJSONArray("response").get(0).toString(), Photo.class);
        StatusCode code = StatusCode.valueOfCode(response.getStatus());

        return new MyResponse<>(code, photo);
    }
}
