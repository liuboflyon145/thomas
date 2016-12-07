package dubbo.sample;

public class DemoServiceImpl implements DemoService{

	@Override
	public String sayHello(String name) {
		return "Hello Dubbo,Hello " + name;
	}

}