
public class Tests {

	/**
	 * @param args
	 * ein Paar tests mit dem Rechnendings in Java
	 */
	public static void main(String[] args) {
		int a = 3;
		int b = 11;
		int c =4;
		boolean d = (a * a -c) == (-c - a);
		int e = ( (a & b) == 0?a*b++:-c/a);
		System.out.println("1." + d +"2."+ e);
		
		Stack<Integer> Zahlenreihe = new Stack<Integer>();
		try{System.out.println(Zahlenreihe.pop());}
		catch(EmptyStack f){
			System.err.println("Kein Element im Stack" + f.getMassage());
		}
		Zahlenreihe.push(14);
		Zahlenreihe.push(2);
		Zahlenreihe.push(23);
		Zahlenreihe.push(41);
		Zahlenreihe.push(15);
		Zahlenreihe.push(6);
		Zahlenreihe.push(247);
		Zahlenreihe.push(18);
		Zahlenreihe.push(95);
		Zahlenreihe.push(150);
		Zahlenreihe.push(1123);
		Zahlenreihe.push(0);
		Zahlenreihe.push(132);
		
		try{System.out.println(Zahlenreihe.pop());}
		catch(EmptyStack f){
			System.err.println("Kein Element im Stack" + f.getMassage());
		}
		System.out.println(Zahlenreihe.toString());
		Zahlenreihe.Sort();
		System.out.println(Zahlenreihe.toString());
		
		
		System.out.println(65 + "=" + (char)65);
		
		
		
	}

}
