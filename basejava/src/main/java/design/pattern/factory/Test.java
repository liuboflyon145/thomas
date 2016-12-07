package design.pattern.factory;

public class Test {
  
    public static void main(String[] args) {  
        Provider provider = new SendMailFactory();  
        Sender sender = provider.produce();  
        sender.send();
        Builder builder = new Builder();
        builder.produceMailSender(10);
    }  
}  
