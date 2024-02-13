package com.clementbayard.clement_ws.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @Autowired
    private JavaMailSender emailSender;

    @CrossOrigin(origins = "https://www.clementbayardphotographe.com/#/contact")
    @PostMapping("/send-email")
    public String envoyerEmail(@RequestBody ContactForm contactForm) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("bayard.clt@gmail.com");
        message.setFrom(contactForm.getEmail());
        message.setSubject("Nouveau message de contact depuis votre site web");
        message.setText("Nom : " + contactForm.getNom() + "\nPrénom : " +contactForm.getPrenom() + "\nE-mail: " + contactForm.getEmail() + "\nNuméro de téléphone: " + contactForm.getNumero()+"\nMessage: " + contactForm.getMessage());

        emailSender.send(message);

        return "E-mail envoyé avec succès !";
    }

}
