package org.tsegelnikova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadPhotoInfo {
    Long server;
    String photos_list;
    String hash;
    String aid;
}
