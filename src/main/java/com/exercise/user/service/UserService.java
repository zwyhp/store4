package com.exercise.user.service;

import com.exercise.user.domain.User;
import com.exercise.user.repository.IuserRepository;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final IuserRepository userRepository;

    @Autowired
    public UserService(IuserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void  saveUser(User user){
        userRepository.save(user);
    }
}
