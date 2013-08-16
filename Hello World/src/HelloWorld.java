
public class HelloWorld {
	public static void main(String[] args){
		System.out.println("Hello world!");
	Hello.lala(null);
	Player p1 = new Player(3,4, "Objekt 1");
	
	Player p2 = new Player(3,4, "lala");
	Player asdfasdf = p1;
	String b = new String( "blabla");
	String a = b ;
	b = "max" ;
	System.out.println(a);
	p1.printPosition(); p2.printPosition();
	p1.move(1, -5); asdfasdf.printPosition(); p2.printPosition();
	
	
	}
	

}
