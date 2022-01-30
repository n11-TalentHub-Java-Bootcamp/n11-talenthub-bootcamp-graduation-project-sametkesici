package com.n11project.creditapplication.service;

import static com.n11project.creditapplication.constant.TwilioConstant.ACCOUNT_SID;
import static com.n11project.creditapplication.constant.TwilioConstant.AUTH_TOKEN;
import static com.n11project.creditapplication.constant.TwilioConstant.PHONE_NUMBER;

import com.n11project.creditapplication.exception.PhoneNumberIsNotValidException;
import com.n11project.creditapplication.model.ApplicationStatus;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SmsService {

  public void sendSms(String phoneNumber, ApplicationStatus applicationStatus, Double creditLimit) {
    if (isPhoneNumberValid(phoneNumber)) {
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      String message = createMessage(applicationStatus, creditLimit);
      PhoneNumber from = new PhoneNumber(PHONE_NUMBER);
      PhoneNumber to = new PhoneNumber(phoneNumber);
      MessageCreator creator = Message.creator(to, from, message);
      creator.create();
      log.info("Send sms to {}", phoneNumber);
    } else {
      throw new PhoneNumberIsNotValidException();
    }
  }

  private boolean isPhoneNumberValid(String phoneNumber) {
    Pattern regexPattern = Pattern.compile("\\+\\d{12}$");
    Matcher matcher = regexPattern.matcher(phoneNumber);
    return matcher.matches();
  }

  private String createMessage(ApplicationStatus applicationStatus, Double creditLimit) {
    String message;
    if (applicationStatus.equals(ApplicationStatus.APPROVED)) {
      message = "Başvurunuz onaylandı. Kredi Limitiniz : " + creditLimit + "TL";
    } else {
      message = "Başvurunuz reddedildi. Kredi skorunuz yetersiz";
    }
    return message;
  }
}