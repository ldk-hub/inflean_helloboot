package com.example.helloboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class HelloBootApplication {

    public static void main(String[] args) {

        //스프링을 사용않고 서블릿으로만 구성한 코드임 스프링내부에서 일어나는 과정을 정리한 코드라보면됨.
        //직접 서블릿을 구성하고 컨테이너를 구성하여 서블릿의 동작과정을 코드로 테스트해본내역임.
        //프론트 컨트롤러가 핸들로러 뻗쳐나가는방법을 정의한 코드 (영역분리도 포함)
        HelloController helloController = new HelloController();


        TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletConext -> {
            servletConext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp) throws
                        ServletException, IOException {

                    //인증, 보안, 다국어, 공통 기능
                    if (req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
                        String name = req.getParameter("name");

                        //영역분리
                        String ret = helloController.hello(name); // 새로운 타입으로 정의하는 것을 바인딩이라함

                        //응답 코드
                        resp.setStatus(HttpStatus.OK.value());
                        resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().println("Hello Servlet");

                    }else if(req.getRequestURI().equals("/user")){

                    }else{
                        //응답 실패시 넘길코드
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }

                }
            }).addMapping("/*");
        });
        webServer.start();
    }
}
