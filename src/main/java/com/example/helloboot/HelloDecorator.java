package com.example.helloboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary //두개의 서비스레이어가 있을때 순서를정의해줘야함. 데코레이터패턴
public class HelloDecorator implements HelloService{
    private final HelloService helloService;

    public HelloDecorator(HelloService helloService){
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name){
        return "*"+ helloService.sayHello(name)+"*";
    }
}
