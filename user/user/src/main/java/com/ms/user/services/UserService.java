package com.ms.user.services;

import com.ms.user.dtos.UserRecordDto;
import com.ms.user.models.UserModel;
import com.ms.user.producer.UserProducer;
import com.ms.user.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    //@Transactional é usado para garantir que a operação de salvar o usuário seja feita em uma transação e operar com o broker
    @Transactional
    public UserModel save(UserModel userModel) {
        // o método save() do UserRepository salva o usuário no banco de dados
        userModel = userRepository.save(userModel);
        // o método publishMessageEmail() do UserProducer envia uma mensagem para a fila de email
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
