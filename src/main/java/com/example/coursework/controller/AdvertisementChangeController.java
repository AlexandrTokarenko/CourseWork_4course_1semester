package com.example.coursework.controller;

import com.example.coursework.dto.*;
import com.example.coursework.entity.Realty;
import com.example.coursework.facade.RealtyUpdateFacade;
import com.example.coursework.model.Property;
import com.example.coursework.service.*;
import com.example.coursework.service.dataServices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/nedvizhimost/my-ads")
@RequiredArgsConstructor
public class AdvertisementChangeController {

    private final RealtyService realtyService;
    private final RepairService repairService;
    private final ConstructionService constructionService;
    private final TypeOfHeatingService typeOfHeatingService;
    private final AdvertisementService advertisementService;
    private final HouseService houseService;
    private final FlatService flatService;
    private final RegionService regionService;
    private final LocalityService localityService;
    private final DistrictService districtService;
    private final AddressService addressService;
    private final ImageService imageService;
    private final FileService fileService;
    private final PurposeService purposeService;
    private final RealtyUpdateFacade realtyUpdateFacade;

    @GetMapping("/edit-ad/{realtyId}")
    public String editAd(Model model, @PathVariable Integer realtyId, Principal principal) {

        Realty realty = realtyService.findById(realtyId);

        if (realty == null) return "redirect:/my-ads";
        model.addAttribute("repairs", repairService.findAll());
        model.addAttribute("constructions", constructionService.findAllByPropertyTitle(realty.getProperty().getTitle()));
        model.addAttribute("heatings", typeOfHeatingService.findAll());
        model.addAttribute("regions",regionService.findAll());
        model.addAttribute("localities",localityService.findAll());
        model.addAttribute("districts",districtService.findAll());
        if (realty.getProperty().getTitle().equals(Property.FLAT.getTitle())) {
            model.addAttribute("realtyFlat", RealtyFlatDto.of(realty));
            return "/user/edit-ad/flat";
        } else {
            model.addAttribute("realtyHouse", RealtyHouseDTO.of(realty));
            return "/user/edit-ad/house";
        }
    }

    @PostMapping("/change-condition/{realtyId}")
    public String changeStatus(@PathVariable Integer realtyId, @RequestParam Boolean condition) {
        advertisementService.changeStatusByRealtyId(realtyId,condition);

        return "redirect:/nedvizhimost/my-ads";
    }

    @GetMapping("/delete-ad/{realtyId}")
    public String deleteAd(@PathVariable Integer realtyId) {

        List<String> paths = imageService.findAllFileNameByRealtyIdOrderByImageOrder(realtyId);

        fileService.delete(paths);

        realtyService.deleteById(realtyId);

        return "redirect:/nedvizhimost/my-ads";
    }

    @PostMapping("/edit-ad/house/{realtyId}")
    public String updateHouse(@Validated @ModelAttribute RealtyHouseDTO realtyHouse, BindingResult errors, @RequestParam("imageFiles") MultipartFile[] imageFiles,
                              @PathVariable Integer realtyId, Model model) throws IOException {

        if (errors.hasErrors()) {
            addAttributeToModel(realtyHouse.getPropertyTitle(), model);
            return "/user/edit-ad/house";
        }

        if (realtyHouse.getDistrictId() == null) {
            if (districtService.countByLocalityId(realtyHouse.getLocalityId()) > 0) {
                errors.addError(new FieldError("realtyHouse", "districtId", "Виберіть район, у якому знаходиться нерухомість"));
                addAttributeToModel(realtyHouse.getPropertyTitle(), model);
                return "/user/edit-ad/house";
            }
        }

        for (MultipartFile imageFile : imageFiles) {
            System.out.println("imageFile = " + imageFile);
        }

        System.out.println("realtyHouse = " + realtyHouse);

        realtyUpdateFacade.update(realtyHouse,imageFiles,realtyId);

        return "redirect:/nedvizhimost/my-ads";
    }

    @PostMapping("/edit-ad/flat/{realtyId}")
    public String updateFlat(@Validated @ModelAttribute("realtyFlat") RealtyFlatDto realty, BindingResult errors, @RequestParam("imageFiles") MultipartFile[] imageFiles,
                             @PathVariable Integer realtyId, Model model) throws IOException {

        if (errors.hasErrors()) {
            addAttributeToModel(realty.getPropertyTitle(), model);
            return "/user/edit-ad/flat";
        }

        if (realty.getFloor() > realty.getNumberOfFloors()) {
            errors.addError(new FieldError("realtyFlat","floor","Поверх, на якому знаходиться квартира, не повинен бути більше кількості поверхів у будинку"));
            addAttributeToModel(realty.getPropertyTitle(), model);
            return "/user/edit-ad/flat";
        }
        if (realty.getDistrictId() == null) {
            if (districtService.countByLocalityId(realty.getLocalityId()) > 0) {
                errors.addError(new FieldError("realtyFlat", "districtId", "Виберіть район, у якому знаходиться нерухомість"));
                addAttributeToModel(realty.getPropertyTitle(), model);
                return "/user/edit-ad/flat";
            }
        }
        for (MultipartFile imageFile : imageFiles) {
            System.out.println("imageFile = " + imageFile);
        }

        realtyUpdateFacade.update(realty,imageFiles,realtyId);

        return "redirect:/nedvizhimost/my-ads";
    }
    private void addAttributeToModel(@PathVariable String property, Model model) {
        model.addAttribute("property",property);
        model.addAttribute("repairs",repairService.findAll());
        model.addAttribute("constructions",constructionService.findAllByPropertyTitle(property));
//        model.addAttribute("purposes",purposeService.findAll());
        model.addAttribute("type_of_heating",typeOfHeatingService.findAll());
        model.addAttribute("regions",regionService.findAll());
        model.addAttribute("localities",localityService.findAll());
        model.addAttribute("districts",districtService.findAll());
    }

}
