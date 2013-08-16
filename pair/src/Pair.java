
public class Pair<T> {
	private T mFirst, mSecond;
	public Pair(T first, T second){
		mFirst = first;
		mSecond = second;
	}
	public void swap() {
		T tmp = mFirst;
		mFirst = mSecond;
		mSecond = tmp;
	}
	
	public String toString() {
		return mFirst.toString()+", "+mSecond.toString();
	}
	
	public T getFirst() {
		return mFirst;
	}
	
	public T getSecond() {
		return mSecond;
	}
	
	public void setFirst(T first) {
		mFirst = first;
	}
	
	public void setSecond(T second) {
		mSecond = second;
	}
	
}