package com.moisegui.msgestionvirement.infra.impl;

import com.moisegui.msgestionvirement.application.dto.MailParameter;
import com.moisegui.msgestionvirement.infra.proxy.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class MailSender {
    @Autowired
    public MailService mailService;

    @Autowired
    private KafkaTemplate<String, MailParameter> kafkaTemplate;

    @Value("${kafka.topic.mail}")
    String topicName;

    public void sendSimpleMessage(MailParameter mailParameter) {
        mailService.sendSimpleMessage(mailParameter);
    }

    public void sendKafkaMessage(MailParameter mailParameter) {

        ListenableFuture<SendResult<String, MailParameter>> future =
                kafkaTemplate.send(topicName, mailParameter);

        future.addCallback(new ListenableFutureCallback<SendResult<String, MailParameter>>() {

            @Override
            public void onSuccess(SendResult<String, MailParameter> result) {
                System.out.println("Sent message=[" + mailParameter.getSubject() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to send message=["
                        + mailParameter.getSubject() + "] due to : " + ex.getMessage());
            }
        });
    }
}
