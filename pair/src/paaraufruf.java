
public class paaraufruf {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pair<Integer> a = new Pair<Integer>(12,13);
		Pair<String> b = new Pair<String>("Java ist","scheiﬂe!");
		Pair<Character> c = new Pair<Character>('a','b');
		Pair<Float> d = new Pair<Float>(12.5f , 123.5f);
		System.out.println( a.getFirst() + d.getSecond());
		System.out.println(b.getFirst());
		System.out.println(c.toString());

	}

}
