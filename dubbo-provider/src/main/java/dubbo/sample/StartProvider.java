package dubbo.sample;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class StartProvider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-provider.xml");
        context.start();
        System.out.println("press to exit");
        System.in.read();
    }
}