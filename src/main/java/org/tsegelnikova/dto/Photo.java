package org.tsegelnikova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
    Long album_id;
    Long date;
    Long id;
    Long owner_id;
    String text;
    Boolean has_tags;
    List<PhotoInfo> sizes;
}
