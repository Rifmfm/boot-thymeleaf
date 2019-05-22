package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	// IoC contaiber : Inversion of Control (제어의 역전)
    	// 내가 Greeter가 필요해 그래서 정보를 줌 그러면 컨테이너가 가져다줌 
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
    	
    	// Greet 인터페이스 선언된 메소드는  sayHello 밖에 없음
    	Greet g = (Greet) ctx.getBean("korea");
    	System.out.println( "Hi  " + g.sayHello() +((Greeter) g).iam());
    	g = (Greet) ctx.getBean("english");
    	System.out.println( "Hi  " + g.sayHello() );

    	System.out.println("-----------");
    	
    	Greeter korea = (Greeter) ctx.getBean("korea");
    	// Greeter 클래스로 부터 생성된 객체를 참조하는 변수,
    	// korea는 Greeter 클래스에 정의된 메소드를 사용할 수 있다고 생각함
    	
    	// korea라는 게 안만들었는데 있어  -> ctx가 new를 해줌?
    	System.out.println( "Hi  " + korea.sayHello() );
    	Greeter2 eng = (Greeter2) ctx.getBean("english");  // Greeter2로부터 오는 친구
    	System.out.println( "Hi  " + eng.sayHello() );
    }
}
