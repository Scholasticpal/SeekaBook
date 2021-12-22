package com.vit.seekabook.controller;

import com.vit.seekabook.domain.BookAd;
import com.vit.seekabook.domain.User;
import com.vit.seekabook.dto.BookAdPostDto;
import com.vit.seekabook.dto.SeekABookListResponse;
import com.vit.seekabook.dto.SeekABookResponse;
import com.vit.seekabook.service.SeekABookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class SeekABookController {

    @Autowired
    private SeekABookService seekABookService;

    /**
     * Get user by email
     *
     * @param email
     * @return {@link User} user
     */
    @GetMapping(value = "/user/{email}", produces = "application/json")
    public SeekABookResponse<User> getUser(@PathVariable("email") String email) {
        log.info("Getting user by email");
        SeekABookResponse<User> dataResponse;
        try {
            dataResponse = new SeekABookResponse<>(seekABookService.getUser(email));
        } catch (Exception ex) {
            dataResponse = new SeekABookResponse<>(SeekABookResponse.FAILURE, ex.getMessage(), null);
        }
        return dataResponse;
    }

    /**
     * Save ad posted by seller for book
     *
     * @param bookAdPostDto
     * @return {@link User} user
     */
    @PostMapping(value = "/book-ad", produces = "application/json")
    public SeekABookResponse<String> postBookAd(@RequestBody BookAdPostDto bookAdPostDto) {
        log.info("Save ad posted by seller for book");
        SeekABookResponse<String> dataResponse;
        try {
            seekABookService.saveBookAd(bookAdPostDto);
            dataResponse = new SeekABookResponse<>("Book ad posted successfully");
        } catch (Exception ex) {
            dataResponse = new SeekABookResponse<>(SeekABookResponse.FAILURE, ex.getMessage(), null);
        }
        return dataResponse;
    }

    /**
     * Get all posted book ads
     *
     * @return {@link BookAd} bookAd
     */
    @GetMapping(value = "/book-ads", produces = "application/json")
    public SeekABookListResponse<BookAd> getAllBookAds() {
        log.info("Get all books ad");
        SeekABookListResponse<BookAd> dataResponse;
        try {
            dataResponse = new SeekABookListResponse<>(seekABookService.getAllBookAds());
        } catch (Exception ex) {
            dataResponse = new SeekABookListResponse<>(SeekABookListResponse.FAILURE, ex.getMessage(), null);
        }
        return dataResponse;
    }

    /**
     * Search book by title
     *
     * @param
     * @return {@link BookAd} bookAd
     */
    @GetMapping(value = "/book-ad", produces = "application/json")
    public SeekABookListResponse<BookAd> getBookAdByTitle(@RequestParam String bookTitle) {
        log.info("Search book by title");
        SeekABookListResponse<BookAd> dataResponse;
        try {
            dataResponse = new SeekABookListResponse<>(seekABookService.searchBookByTitle(bookTitle.strip()));
        } catch (Exception ex) {
            dataResponse = new SeekABookListResponse<>(SeekABookListResponse.FAILURE, ex.getMessage(), null);
        }
        return dataResponse;
    }

    /**
     * Signing up a new user
     *
     * @param user
     * @return {@link String}
     */
    @PostMapping(value = "/signup", produces = "application/json")
    public SeekABookResponse<String> signUpUser(@RequestBody User user) {
        log.info("Signing up new user");
        SeekABookResponse<String> dataResponse;
        try {
            seekABookService.signUpUser(user);
            dataResponse = new SeekABookResponse<>("Sign up of user is successful");
        } catch (Exception ex) {
            dataResponse = new SeekABookResponse<>(SeekABookResponse.FAILURE, ex.getMessage(), null);
        }
        return dataResponse;
    }
}
