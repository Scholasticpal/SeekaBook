package com.vit.seekabook.controller;

import com.vit.seekabook.domain.User;
import com.vit.seekabook.dto.BookAdPostDto;
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
}
