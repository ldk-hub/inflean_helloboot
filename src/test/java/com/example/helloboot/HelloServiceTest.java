package com.example.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService(){
        SimpleHelloService helloService = new SimpleHelloService();

        String ret = helloService.sayHello("Test");

        Assertions.assertThat(ret).isEqualTo("HelloTest");
    }

    @Test
    void HelloDecorator(){ //데코레이터 패턴 예시테스트코드
        HelloDecorator decorator = new HelloDecorator(name -> name);

       String ret =  decorator.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("*Test*");
    }
}
