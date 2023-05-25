package org.tsegelnikova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MyResponse<T> {
    StatusCode status;
    T responseDate;
}
