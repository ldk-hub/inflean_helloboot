package com.example.helloboot;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HelloApiTest {
    @Test
    void helloApi(){
        //http localhost:8080/hello?name=Spring 테스트 코드 구현
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res =   rest.getForEntity("http://localhost:8080/hello?name={name}",String.class, "Spring");

        //status code 200결과 테스트
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        //header(content-type) text/plain 결과 테스트
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        //body Hello Spring
        Assertions.assertThat(res.getBody()).isEqualTo("*HelloSpring*");
    }

    @Test
    void failHelloApi(){
        //http localhost:8080/hello?name=Spring 테스트 코드 구현
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res =   rest.getForEntity("http://localhost:8080/hello?name=",String.class );

        //status code 200결과 테스트
        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
