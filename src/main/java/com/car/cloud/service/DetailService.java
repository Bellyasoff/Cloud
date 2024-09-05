package com.car.cloud.service;

import com.car.cloud.dto.DetailDto;
import com.car.cloud.mapper.DetailMapper;
import com.car.cloud.model.Detail;

import java.util.List;

public interface DetailService {
    List<DetailDto> findAllDetails();

    Detail saveDetail(DetailDto detailDto);

    DetailDto findDetailById(long detailId);

    void updateDetail(DetailDto detailDto);

    void delete(Long detailId);

    List<DetailDto> searchDetails(String query);
}
