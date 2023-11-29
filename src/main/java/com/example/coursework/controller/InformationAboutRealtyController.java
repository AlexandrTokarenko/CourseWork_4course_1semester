package com.example.coursework.controller;

import com.example.coursework.entity.Realty;
import com.example.coursework.model.Property;
import com.example.coursework.service.BookmarkManager;
import com.example.coursework.service.FileService;
import com.example.coursework.service.dataServices.ImageService;
import com.example.coursework.service.dataServices.RealtyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/nedvizhimost/information/realty")
@RequiredArgsConstructor
public class InformationAboutRealtyController {

    private final RealtyService realtyService;
    private final BookmarkManager bookmarkManager;
    private final ImageService imageService;
    private final FileService fileService;

    @GetMapping("/{realtyId}")
    public String showInformationAboutRealty(Model model, @PathVariable Integer realtyId, HttpServletResponse response,
                                             HttpServletRequest request, Principal principal) {

        Realty realty;

        if (principal == null) {
            realty = realtyService.findByIdAndAdvertisementsOpenTrue(realtyId);
            if (realty == null) return "redirect:/";
        } else {
            String userName = principal.getName();
            realty = realtyService.findById(realtyId);
            if (!realty.getAdvertisement().getUserEmail().getEmail().equals(userName)) {
                return "redirect:/";
            }
        }


        model.addAttribute("bookmark",bookmarkManager.checkBookmarkInCookie(realtyId,request,response));
        model.addAttribute("realty",realty);
        List<String> fileNames = imageService.findAllFileNameByRealtyIdOrderByImageOrder(realtyId);

        List<String> paths = fileService.getPathByFileName(fileNames);

        System.out.println("paths = " + paths);
        model.addAttribute("files", paths);
        if (realty.getProperty().getTitle().equals(Property.FLAT.getTitle())) {
            return "/information/flat";
        } else {
            return "/information/house";
        }
    }
}
