package com.ms.user.producer;

import com.ms.user.config.RabbitMQConfig;
import com.ms.user.dtos.EmailDto;
import com.ms.user.models.UserModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(UserModel userModel) {
        var emailDto = new EmailDto();
        emailDto.setUserId(userModel.getUserId());
        emailDto.setEmailTo(userModel.getEmail());
        emailDto.setSubject("cadastro realizado com sucesso!");
        emailDto.setText("Parabéns " + userModel.getName() + ", seu cadastro foi realizado com sucesso!");

        // como o RabbitMQ não tem um exchange padrão, precisamos passar uma string vazia
        // a routingKey é o nome da fila que queremos enviar a mensagem
        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}
