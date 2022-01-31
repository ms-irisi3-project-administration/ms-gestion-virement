package com.moisegui.msgestionvirement.infra.proxy;

import com.moisegui.msgestionvirement.application.dto.MailParameter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("ms-mailler")
public interface MailService {
    @PostMapping("/ms-mailler/api/send")
    public void sendSimpleMessage(@RequestBody MailParameter mailParameter);
}
