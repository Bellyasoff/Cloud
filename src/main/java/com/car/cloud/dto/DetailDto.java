package com.car.cloud.dto;

import com.car.cloud.model.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetailDto {
    private Long id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "OEM number should not be empty")
    private String oemNumber;
    @NotEmpty(message = "Manufacturer should not be empty")
    private String manufacturer;
    @NotNull(message = "Price should not be empty")
    private Integer price;
    private UserEntity createdBy;
}
