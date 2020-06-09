package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rest.repository.CenovnikRepository;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void SendMail(String email,String subject,String poruka) {
		SimpleMailMessage mail=new SimpleMailMessage();
		mail.setTo(email); //radi provjere,kasnije samo email staviti
		mail.setSubject(subject);
		mail.setText(poruka);
		javaMailSender.send(mail);
	}
}
