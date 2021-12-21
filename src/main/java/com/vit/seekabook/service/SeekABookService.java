package com.vit.seekabook.service;

import com.vit.seekabook.domain.User;
import com.vit.seekabook.exception.SeekABookException;
import com.vit.seekabook.repo.SeekABookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
     */
    public User getUser(String email) throws SeekABookException {
        List<User> users = seekABookRepository.getUsers(email);
        if (!CollectionUtils.isEmpty(users)) {
            return users.get(0);
        } else {
            throw new SeekABookException("User doesn't exist");
        }

    }
}
