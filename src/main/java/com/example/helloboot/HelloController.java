package com.example.helloboot;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HelloController{

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello") //메소드레벨의 /hello 경로로 해당 기능을 수행한다.
    //String문자열을 그대로반환하기위한 어노테이션 이거선언안하면 view를 찾게되면서 오류발생. @RestController 를 선언했다면 RestController가 역할을 함. 지금은 선언 안해서 메소드에 선언해줘야함
    public String hello(String name) {
        if(name == null || name.trim().length() ==0)throw new IllegalArgumentException();
        return helloService.sayHello(name);
    }

}
