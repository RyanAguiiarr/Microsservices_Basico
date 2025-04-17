package com.ms.user.services;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //@Transactional é usado para garantir que a operação de salvar o usuário seja feita em uma transação e operar com o broker
    @Transactional
    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
