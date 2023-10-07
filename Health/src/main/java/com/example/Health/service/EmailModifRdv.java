package com.example.Health.service;

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
@Service
public class EmailModifRdv {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;



    @Async
    public void sendEmail(String Nom,String Prenom, String receiver,String dateRdv, String heure) throws MessagingException {


        MimeMessage message= mailSender.createMimeMessage();
        MimeMessageHelper helper= new MimeMessageHelper(message,true);
        helper.setFrom("HealthyAdministration@gmail.com");
        helper.setTo(receiver);
        helper.setSubject("Rendez-vous modifié");

        // Créer un contexte Thymeleaf
        Context context = new Context();

        LocalDate date = LocalDate.now();
        int annee = date.getYear();

        context.setVariable("Nom",Nom);
        context.setVariable("Prenom",Prenom);
        context.setVariable("DateRdv",dateRdv);
        context.setVariable("Heure",heure);

        context.setVariable("Date",annee);


        // Résoudre le template Thymeleaf avec les variables
        String htmlContent = templateEngine.process("emailModifRdv", context);
        helper.setText(htmlContent, true); // true indique que le contenu est au format HTML

        mailSender.send(message);
        System.out.println("Mail send");


    }
}
