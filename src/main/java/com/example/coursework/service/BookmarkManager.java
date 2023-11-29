package com.example.coursework.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookmarkManager {
    private static final String CART_COOKIE_NAME = "bookmark";
    private static final int CART_COOKIE_MAX_AGE = 7 * 24 * 60 * 60; // Время жизни cookie в секундах

    public List<Integer> getBookmarkItems(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (CART_COOKIE_NAME.equals(cookie.getName())) {
                    String cartValue = cookie.getValue();

                    return convertStringToCartItems(cartValue);
                }
            }
        }
        return new ArrayList<>();
    }

    public void addToBookmark(HttpServletRequest request, HttpServletResponse response, Integer realtyId) {

        List<Integer> cartItems = getBookmarkItems(request);

        cartItems.add(realtyId);

        saveCookie(response, cartItems);
    }

    private void saveCookie(HttpServletResponse response, List<Integer> cartItems) {
        String cartValue = convertCartItemsToString(cartItems);
        Cookie cookie = new Cookie(CART_COOKIE_NAME, cartValue);
        cookie.setMaxAge(CART_COOKIE_MAX_AGE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private List<Integer> convertStringToCartItems(String cartValue) {

        String[] bookmarksInCookie = cartValue.split("-");
        if (bookmarksInCookie.length == 0) return null;

        List<Integer> bookmarks = new ArrayList<>();
        for (String s : bookmarksInCookie) {
            int realtyId;
            try {
                realtyId = Integer.parseInt(s);
            } catch (Exception e) {
                return bookmarks;
            }
            bookmarks.add(realtyId);
        }

        return bookmarks;
    }


    private String convertCartItemsToString(List<Integer> cartItems) {

        StringBuilder res = new StringBuilder();

        for (Integer bookmark : cartItems) {
            res.append(bookmark).append("-");
        }

        return res.toString();
    }

    public void deleteCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie(CART_COOKIE_NAME, "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void deleteCartItem(HttpServletRequest request, HttpServletResponse response, Integer realtyId) {

        List<Integer> cartItems = getBookmarkItems(request);

        cartItems.remove(realtyId);

        saveCookie(response, cartItems);
    }

    public boolean checkBookmarkInCookie(Integer realtyId, HttpServletRequest request, HttpServletResponse response) {

        List<Integer> bookmarkItems = getBookmarkItems(request);

        return bookmarkItems.contains(realtyId);
    }
}
