import java.util.ArrayList;
public class Stack<T extends Comparable<T>> {
	private ArrayList<T> stapel = new ArrayList<T>();
	public Stack(){}
	public void push(T element){
		this.stapel.add(element);
	}
	public T pop() throws EmptyStack{
		if(this.stapel.size()<=0) throw new EmptyStack();
		else return this.stapel.remove(this.stapel.size()-1);
	}
		
	public ArrayList<T> getall(){
		return this.stapel;
	}
	public String toString(){
		String result = "";
		for(int i=this.stapel.size()-1; i>=0;i--){
			result = result +"Das " + (i+1) + ". Element: " + this.stapel.get(i) + "\n";
		}
		return result;
	}
	
	public void Sort(){
		// int lala=0;
		while(unsortiert()){
			T nr1 = stapel.get(0);
			T nr2;
			for(int index=0; index < this.stapel.size()-1; index++){
				nr2 = stapel.get(index+1);
				if(nr1.compareTo(nr2)>0) swap(index,index+1);
				nr1=nr2;
				
			}
			
		}
	}
	public boolean unsortiert(){
		if(this.stapel.size()==0) return false;
		T c = this.stapel.get(0);
		T d;
		for(int index=0;index< this.stapel.size(); index++){
			d= this.stapel.get(index);
			if(d.compareTo(c)<0)return true;
			c = d;
			
		}
		return false;
	}
	public void swap(int a, int b){
		T temp = stapel.get(a);
		stapel.set(a, stapel.get(b));
		stapel.set(b,temp);
	}
	
	
}
