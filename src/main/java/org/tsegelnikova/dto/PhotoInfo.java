package org.tsegelnikova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoInfo {
    Long height;
    Long width;
    String type;
    String url;
}
