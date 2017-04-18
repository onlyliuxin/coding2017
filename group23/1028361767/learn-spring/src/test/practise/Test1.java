package test.practise;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import practise.chapter1.HelloService;
import practise.chapter1.HelloWorldService;

public class Test1 {
	
	ApplicationContext context = new ClassPathXmlApplicationContext("practise/chapter1.xml");
	
	@Test
	public void testHello(){
		HelloWorldService helloWorldService = context.getBean("helloWorldService", HelloWorldService.class);
		Assert.assertEquals("hello", helloWorldService.hello());
		HelloWorldService helloWorldService1 = context.getBean("helloWorldService1", HelloWorldService.class);
		Assert.assertEquals("hello", helloWorldService1.hello());
		HelloWorldService helloWorldService2 = context.getBean("helloWorldService2", HelloWorldService.class);
		Assert.assertEquals("hello", helloWorldService2.hello());
		String[] alias = context.getAliases("helloWorldService");
		Assert.assertEquals(2, alias.length);
		for(String alia : alias){
			System.out.println(alia);
		}
	}
	
	@Test
	public void testSay(){
		HelloService helloService = context.getBean("helloService", HelloService.class);
		Assert.assertEquals("hello spring3", helloService.say());
		for(String target : helloService.getTargets()){
			System.out.println(target);
		}
		Assert.assertEquals(3, helloService.getTargets().size());
	}
}
