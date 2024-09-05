package com.car.cloud.service.impl;

import com.car.cloud.mapper.DetailMapper;
import com.car.cloud.model.Detail;
import com.car.cloud.dto.DetailDto;
import com.car.cloud.model.UserEntity;
import com.car.cloud.repository.DetailRepository;
import com.car.cloud.repository.UserRepository;
import com.car.cloud.security.SecurityUtil;
import com.car.cloud.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.car.cloud.mapper.DetailMapper.mapToDetail;
import static com.car.cloud.mapper.DetailMapper.mapToDetailDTO;

@Service
public class DetailServiceImpl implements DetailService {

    private DetailRepository detailRepository;
    private UserRepository userRepository;

    @Autowired
    public DetailServiceImpl(DetailRepository detailRepository, UserRepository userRepository) {
        this.detailRepository = detailRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<DetailDto> findAllDetails() {
        List<Detail> details = detailRepository.findAll();
        return details.stream().map(detail -> mapToDetailDTO(detail)).collect(Collectors.toList());
    }

    @Override
    public Detail saveDetail(DetailDto detailDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Detail detail = mapToDetail(detailDto);
        detail.setCreatedBy(user);
        return detailRepository.save(detail);
    }

    @Override
    public DetailDto findDetailById(long detailId) {
        Detail detail = detailRepository.findById(detailId).get();
        return mapToDetailDTO(detail);
    }

    @Override
    public void updateDetail(DetailDto detailDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Detail detail = mapToDetail(detailDto);
        detail.setCreatedBy(user);
        detailRepository.save(detail);
    }

    @Override
    public void delete(Long detailId) {
        detailRepository.deleteById(detailId);
    }

    @Override
    public List<DetailDto> searchDetails(String query) {
        List<Detail> details = detailRepository.searchDetails(query);
        return details.stream().map(detail -> mapToDetailDTO(detail)).collect(Collectors.toList());
    }
}
