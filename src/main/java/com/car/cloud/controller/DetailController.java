package com.car.cloud.controller;

import com.car.cloud.dto.DetailDto;
import com.car.cloud.model.Detail;
import com.car.cloud.model.UserEntity;
import com.car.cloud.security.SecurityUtil;
import com.car.cloud.service.DetailService;
import com.car.cloud.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DetailController {

    private DetailService detailService;
    private UserService userService;

    @Autowired
    public DetailController(DetailService detailService, UserService userService) {
        this.userService = userService;
        this.detailService = detailService;
    }

    @GetMapping("/details")
    public String listDetails(Model model) {
        UserEntity user = new UserEntity();
        List<DetailDto> details = detailService.findAllDetails();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("detail", details);
        return "details-list";
    }

    @GetMapping("/details/search")
    public String searchDetail(@RequestParam(value = "query") String query, Model model) {
        List<DetailDto> details = detailService.searchDetails(query);
        model.addAttribute("detail", details);
        return "details-list";
    }

    @GetMapping("/details/{detailId}")
    public String detailInfo(@PathVariable("detailId") long detailId, Model model) {
        UserEntity user = new UserEntity();
        DetailDto detailDto = detailService.findDetailById(detailId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("detail", detailDto);
        return "details-info";
    }

    @GetMapping("/details/new")
    public String createDetailForm(Model model) {
        Detail detail = new Detail();
        model.addAttribute("detail", detail);
        return "details-create";
    }

    @GetMapping("/details/{detailId}/delete")
    public String deleteDetail(@PathVariable("detailId") Long detailId) {
        detailService.delete(detailId);
        return "redirect:/details";
    }

    @PostMapping("/details/new")
    public String saveDetail(@Valid @ModelAttribute("detail") DetailDto detailDto,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("detail", detailDto);
            return "details-create";
        }
        detailService.saveDetail(detailDto);
        return "redirect:/details";
    }

    @GetMapping("/details/{detailId}/edit")
    public String editDetail(@PathVariable("detailId") Long detailId, Model model) {
        DetailDto detail = detailService.findDetailById(detailId);
        model.addAttribute("detail", detail);
        return "details-edit";
    }

    @PostMapping("/details/{detailId}/edit")
    public String updateDetail(@PathVariable("detailId") Long detailId,
                               @Valid @ModelAttribute("detail") DetailDto detail,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "details-edit";
        }
        detail.setId(detailId);
        detailService.updateDetail(detail);
        return "redirect:/details";
    }
}
