package design.pattern.factory;

public class SendSmsFactory implements Provider{
  
    @Override  
    public Sender produce() {  
        return new SmsSender();  
    }  
} 