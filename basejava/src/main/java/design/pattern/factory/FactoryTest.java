package design.pattern.factory;

public class FactoryTest {

	public static void main(String[] args) {
		SendFactory factory = new SendFactory();
		Sender sender = factory.produce("mail");
		sender.send();
		factory.produceMail().send();
		factory.produceSms().send();


	}
}