package com.materiais.araujo.araujo_materiais_api.service.usuario;

import com.materiais.araujo.araujo_materiais_api.infra.exceptions.personalizadas.usuario.FalhaAoEnviarEmailException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmail(String destinatario, String assunto, String mensagem){

        try {

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(remetente);
            simpleMailMessage.setTo(destinatario);
            simpleMailMessage.setSubject(assunto);
            simpleMailMessage.setText(mensagem);
            javaMailSender.send(simpleMailMessage);

        } catch (Exception e){
            throw new FalhaAoEnviarEmailException();
        }

    }

}
