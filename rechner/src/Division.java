
public class Division {
	public static int[] div(int a , int b ){
		int result = 0;
		int rest;
		while (true){
			if (a-b < 0){
				rest = a;
				break; }
			a -= b;
			result += 1;
				}
		int[] back = {result, rest};
		return back;
		}
	}
