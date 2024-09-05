package com.car.cloud.mapper;

import com.car.cloud.dto.DetailDto;
import com.car.cloud.model.Detail;

public class DetailMapper {

    public static Detail mapToDetail(DetailDto detail) {
        Detail detailDto = Detail.builder()
                .id(detail.getId())
                .name(detail.getName())
                .oemNumber(detail.getOemNumber())
                .manufacturer(detail.getManufacturer())
                .price(detail.getPrice())
                .createdBy(detail.getCreatedBy())
                .build();
        return detailDto;
    }

    public static DetailDto mapToDetailDTO(Detail detail) {
        DetailDto detailDTO = DetailDto.builder()
                .id(detail.getId())
                .name(detail.getName())
                .oemNumber(detail.getOemNumber())
                .manufacturer(detail.getManufacturer())
                .price(detail.getPrice())
                .createdBy(detail.getCreatedBy())
                .build();
        return detailDTO;
    }
}
