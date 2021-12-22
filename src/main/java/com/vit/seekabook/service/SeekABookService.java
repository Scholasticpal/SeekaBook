package com.vit.seekabook.service;

import com.vit.seekabook.domain.BookAd;
import com.vit.seekabook.domain.User;
import com.vit.seekabook.dto.BookAdPostDto;
import com.vit.seekabook.exception.SeekABookException;
import com.vit.seekabook.repo.SeekABookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

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
        if (bookAdPostDto.getSellerEmail() == null || bookAdPostDto.getBookTitle() == null) {
            throw new SeekABookException("Seller email or book title can't be empty");
        }
        seekABookRepository.saveBookAd(bookAdPostDto);
    }

    /**
     * Get all book ads
     *
     * @return List of {@link BookAd}
     * @throws SeekABookException
     */
    public List<BookAd> getAllBookAds() throws SeekABookException {
        return seekABookRepository.getAllBooks().stream().
                filter(bookAd -> bookAd.isApprovedByAdmin()).
                collect(Collectors.toList());
    }

    /**
     * Search book by title
     *
     * @param title
     * @return List of {@link BookAd}
     * @throws SeekABookException
     */
    public List<BookAd> searchBookByTitle(String title) throws SeekABookException {
        return seekABookRepository.getBookByTitle(title);
    }

    /**
     * Sign up new user
     *
     * @param user
     * @throws SeekABookException
     */
    public void signUpUser(User user) throws SeekABookException {
        if (user.getEmail() == null) {
            throw new SeekABookException("User email can't be empty");
        }
        seekABookRepository.addUser(user);
    }
}
