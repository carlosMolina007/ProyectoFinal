package co.edu.uniquindio.hospitalproject.utils;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class CorreoUtils {
    public static boolean enviarCorreo(String destinatario, String asunto, String cuerpo) {
        final String remitente = "carlosa.molinal@uqvirtual.edu.co"; //correo desde el que se enviarán mensajes
        final String clave = "vtuswqqwftxifbll"; //Clave generada en google

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.port", "587");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        try {
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            Transport.send(mensaje);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
