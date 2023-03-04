package com.example.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@SpringBootApplication
public class HelloBootApplication {

    public static void main(String[] args) {
        //스프링 컨테이너를 직접생성한 영역
        GenericWebApplicationContext applicationContext =  new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class); //빈 등록
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh(); //
        //스프링을 사용않고 서블릿으로만 구성한 코드임 스프링내부에서 일어나는 과정을 정리한 코드라보면됨.
        //직접 서블릿을 구성하고 컨테이너를 구성하여 서블릿의 동작과정을 코드로 테스트해본내역임.
        //프론트 컨트롤러가 핸들로러 뻗쳐나가는방법을 정의한 코드 (영역분리도 포함)

        ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
        WebServer webServer = serverFactory.getWebServer(servletConext -> {
            servletConext.addServlet("dispatcherServlet",//오브젝트 나 작업을 위임할 디스패쳐 서블릿이 수행한다.
                    new DispatcherServlet(applicationContext)).addMapping("/*");  //디스패쳐서블릿이 기존의 소스를 아주 간결하게 해준다.
        });
        webServer.start();
    }
}
