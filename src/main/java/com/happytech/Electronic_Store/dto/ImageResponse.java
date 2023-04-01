package com.happytech.Electronic_Store.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageResponse {
    private String imageName;
    private String messege;

    private HttpStatus status;

    private boolean success;
}
