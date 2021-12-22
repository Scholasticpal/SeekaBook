package com.vit.seekabook.service;

import com.vit.seekabook.domain.User;
import com.vit.seekabook.dto.BookAdPostDto;
import com.vit.seekabook.exception.SeekABookException;
import com.vit.seekabook.repo.SeekABookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class SeekABookService {

    @Autowired
    private SeekABookRepository seekABookRepository;

    /**
     * Get user detail by email
     *
     * @param email
     * @return {@link User}
     * @throws SeekABookException
     */
    public User getUser(String email) throws SeekABookException {
        List<User> users = seekABookRepository.getUsers(email);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        } else {
            throw new SeekABookException("User doesn't exist");
        }
    }

    /**
     * Save book ad
     * @param bookAdPostDto
     * @throws SeekABookException
     */
    public void saveBookAd(BookAdPostDto bookAdPostDto) throws SeekABookException {
        if(bookAdPostDto.getSellerEmail() == null || bookAdPostDto.getBookTitle() == null) {
            throw new SeekABookException("Seller email or book title can't be empty");
        }
        seekABookRepository.saveBookAd(bookAdPostDto);
    }
}
