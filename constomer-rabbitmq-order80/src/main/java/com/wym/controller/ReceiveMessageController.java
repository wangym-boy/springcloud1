package com.wym.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableBinding(Sink.class)
public class ReceiveMessageController {

    @Value("${server.port}")
    private String serverProt;

    @StreamListener(Sink.INPUT)
    public void receiveMessage(Message<String> message){
        System.out.println("消费者1号,--------------->收到的消息："+message.getPayload()+"\t"+"端口："+serverProt);
    }

}
