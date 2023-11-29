package com.example.coursework.controller;

import com.example.coursework.dto.RealtyMAINTODO;
import com.example.coursework.entity.Image;
import com.example.coursework.service.BookmarkManager;
import com.example.coursework.service.CurrencyService;
import com.example.coursework.service.FileService;
import com.example.coursework.service.dataServices.ImageService;
import com.example.coursework.service.dataServices.RealtyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/nedvizhimost")
public class BookmarkController {

    private final BookmarkManager bookmarkManager;
    private final RealtyService realtyService;
    private final ImageService imageService;
    private final FileService fileService;
    private final CurrencyService currencyService;

    @PostMapping("/change-bookmark/{realtyId}")
    public String changeStatusBookmark(@PathVariable Integer realtyId, @RequestParam Boolean stateBookmark,
                                       HttpServletRequest request, HttpServletResponse response
                                      ) {

        if (stateBookmark) {
            bookmarkManager.addToBookmark(request, response, realtyId);
        } else bookmarkManager.deleteCartItem(request, response, realtyId);
        return "redirect:/nedvizhimost/information/realty/" + realtyId;
    }

    @GetMapping("/bookmarks")
    public String showBookmarks(Model model, HttpServletResponse response, HttpServletRequest request) {

        List<RealtyMAINTODO> realties = realtyService.findRealtyDTOByIdAndOpenTrue(bookmarkManager.getBookmarkItems(request));

        List<Image> images = imageService.findImageByRealtyIdAndImageOrder(realties.stream().map(RealtyMAINTODO::getId).toList(), 1);

        for (int i = 0; i < realties.size(); i++) {
            realties.get(i).setFirstImage(fileService.getPathByFileName(images.get(i).getPath()));
        }

        realties = currencyService.firstPriceExchangeRate(realties);
        model.addAttribute("realties",realties);

        return "/bookmark/bookmarks";
    }

    @GetMapping("/delete-bookmark/{realtyId}")
    public String deleteBookmark(@PathVariable Integer realtyId, HttpServletRequest request, HttpServletResponse response) {

        bookmarkManager.deleteCartItem(request, response, realtyId);

        return "redirect:/nedvizhimost/bookmarks";
    }
}
