
public class foobar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for ( int i = 1 ; i <101 ; i++ ){
			if (i%3 == 0 ) {
				if (i%5 == 0 ){
					System.out.println("foobar");
				}
				else{ System.out.println("foo");
				}
			}
			else if(i%5 == 0){
				System.out.println("bar");
				}
			else { System.out.println(i);
			}
		}

	}

}
