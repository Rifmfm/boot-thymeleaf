package hello;

import org.joda.time.LocalTime;

public class HelloWorld {
	public static void main(String[] args) {
		LocalTime currentTime = new LocalTime();
		System.out.println("The current local time is: " + currentTime);
		Greeter greeter = new Greeter();  // 내가 Greeter가 필요해 그래서 만들어서 써
		System.out.println(greeter.sayHello());
	}
}
