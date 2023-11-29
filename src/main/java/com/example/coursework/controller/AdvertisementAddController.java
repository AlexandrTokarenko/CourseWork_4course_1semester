package com.example.coursework.controller;

import com.example.coursework.dto.*;
import com.example.coursework.facade.RealtySaveFacade;
import com.example.coursework.model.Property;
import com.example.coursework.repository.PropertyRepository;
import com.example.coursework.service.*;
import com.example.coursework.service.dataServices.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nedvizhimost/my-ads/add-ad")
public class AdvertisementAddController {

    private final RealtyService realtyService;
    private final PropertyRepository propertyRepository;
    private final RepairService repairService;
    private final ConstructionService constructionService;
    private final PurposeService purposeService;
    private final TypeOfHeatingService typeOfHeatingService;
    private final RegionService regionService;
    private final LocalityService localityService;
    private final DistrictService districtService;
    private final AddressService addressService;
    private final FlatService flatService;
    private final AdvertisementService advertisementService;
    private final HouseService houseService;
    private final FileService fileService;
    private final RealtySaveFacade realtySaveFacade;
    @GetMapping("/property")
    public String showAddPropertyWindow(Model model) {

        model.addAttribute("properties",propertyRepository.findAll());

        return "/user/add-ad/property";
    }

    @GetMapping("/add-information")
    public String addInformationAboutRealty(@RequestParam String property, Model model) {
          addAttributeToModel(property,model);
      //  model.addAttribute("realty",new RealtyRequestDTO());

        if (property.equals(Property.FLAT.getTitle())) {
            model.addAttribute("realtyFlat",new RealtyFlatDto());
            return "/user/add-ad/flat";
        }
        model.addAttribute("realtyHouse",new RealtyHouseDTO());
        return "/user/add-ad/house";

    }

    @PostMapping("/save-flat/{property}")
    public String saveFlat(Principal principal, @PathVariable String property, @Validated @ModelAttribute("realtyFlat") RealtyFlatDto realty, BindingResult errors, Model model) {

        System.out.println("realty = " + realty);
        if (errors.hasErrors()) {
            addAttributeToModel(property, model);
            return "/user/add-ad/flat";
        }

        System.out.println("imageFiles.length = " + realty.getImageFiles().length);

        if (realty.getImageFiles().length == 1) {
            if (realty.getImageFiles()[0].isEmpty()) {
                errors.addError(new FieldError("realtyFlat","imageFiles","Завантажте мінімум одне фото"));
                addAttributeToModel(property, model);
                return "/user/add-ad/flat";
            }
        }

        if (realty.getFloor() > realty.getNumberOfFloors()) {
            errors.addError(new FieldError("realtyFlat","floor","Поверх, на якому знаходиться квартира, не повинен бути більше кількості поверхів у будинку"));
            addAttributeToModel(property, model);
            return "/user/add-ad/flat";
        }



        if (realty.getDistrictId() == null) {
            if (districtService.countByLocalityId(realty.getLocalityId()) > 0) {
                errors.addError(new FieldError("realtyFlat", "districtId", "Виберіть район, у якому знаходиться нерухомість"));
                addAttributeToModel(property, model);
                return "/user/add-ad/flat";
            }
        }

        realtySaveFacade.save(realty,principal.getName(),property);

        return "redirect:/nedvizhimost/my-ads";
    }

    @PostMapping("/save-house/{property}")
    public String saveHouse(Principal principal, @PathVariable String property, @Validated @ModelAttribute("realtyHouse") RealtyHouseDTO realty, BindingResult errors,
                            Model model) {

        System.out.println("realty = " + realty);
        if (errors.hasErrors()) {
            addAttributeToModel(property, model);
            return "/user/add-ad/house";
        }

        System.out.println("imageFiles.length = " + realty.getImageFiles().length);

        if (realty.getImageFiles().length == 1) {
            if (realty.getImageFiles()[0].isEmpty()) {
                errors.addError(new FieldError("realtyHouse","imageFiles","Завантажте мінімум одне фото"));
                addAttributeToModel(property, model);
                return "/user/add-ad/house";
            }
        }

        if (realty.getDistrictId() == null) {
            if (districtService.countByLocalityId(realty.getLocalityId()) > 0) {
                errors.addError(new FieldError("realtyHouse", "districtId", "Виберіть район, у якому знаходиться нерухомість"));
                addAttributeToModel(property, model);
                return "/user/add-ad/house";
            }
        }

        realtySaveFacade.save(realty,principal.getName(),property);

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
