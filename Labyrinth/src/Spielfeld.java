import java.util.ArrayList;


public class Spielfeld {
	Spielfeld linkerN;
	Spielfeld rechterN;
	Spielfeld obererN;
	Spielfeld untererN;
	private int eigenschaft;
	static final int NORMAL = 0;
	static final int STARTFELD = 1;
	static final int ENDFELD = 2;
	int PositionX;
	int PositionY;
	
	
	
	public Spielfeld(int x,int y){
		this.eigenschaft = NORMAL;
		this.PositionX = x;
		this.PositionY = y;
		
	}
	public void update(Spielfeld[][] lab){
		if (this.PositionX == 0) this.linkerN = null;
		else this.linkerN = lab[this.PositionX-1][this.PositionY];
		if (this.PositionY == 0) this.obererN = null;
		else this.obererN = lab[this.PositionX][this.PositionY-1];
		if (this.PositionX == lab.length-1) this.rechterN = null;
		else this.rechterN = lab[this.PositionX+1][this.PositionY];
		if (this.PositionY == lab[0].length-1) this.untererN = null;
		else this.untererN = lab[this.PositionX][this.PositionY+1];
	}
	public ArrayList<Spielfeld> schritte(){
		ArrayList<Spielfeld> result = new ArrayList<>();
		if(linkerN != null) result.add(linkerN);
		if(rechterN != null) result.add(rechterN);
		if(obererN != null) result.add(obererN);
		if(untererN != null) result.add(untererN);
		return result;
		
	}
}
