package com.example.Health.service;

import com.example.Health.model.Medicament;
import com.example.Health.model.Posologie;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.util.List;
@Service
public class EmailPrescription {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;



    @Async
    public void sendEmail(String Nom, String Prenom, String receiver) throws MessagingException {





        MimeMessage message= mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true);
        helper.setFrom("HealthyAdministration@gmail.com");
        helper.setTo(receiver);
        helper.setSubject("Rappel de médicament");

        // Créer un contexte Thymeleaf
        Context context = new Context();

        LocalDate date = LocalDate.now();
        int annee = date.getYear();

        context.setVariable("Nom",Nom);
        context.setVariable("Prenom",Prenom);


        context.setVariable("Date",annee);


        // Résoudre le template Thymeleaf avec les variables
        String htmlContent = templateEngine.process("emailPrescription", context);
        helper.setText(htmlContent, true); // true indique que le contenu est au format HTML

        mailSender.send(message);
        System.out.println("Mail send");


    }
}
