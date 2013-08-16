
public class Spielfeld extends Spiel{
	private String[][] Feld = new String[7][7]; // 1. X Koordinate, 2. Y-koordinate
	private int[] Anzahl = new int[7];
	public Spielfeld(){
		for(int i = 0; i<7;i++){
			Anzahl[i]=0;
			for(int b = 0; b<7;b++){
				Feld[i][b]= " ";
			}
		}
	}
	public void zeichneFeld(){
		for(int i=6; i>=0;i--){
			System.out.println("| " + Feld[0][i]+" | "+Feld[1][i]+" | "+Feld[2][i]+" | "+Feld[3][i]+" | " + Feld[4][i]+" | "+Feld[5][i]+" | " + Feld[6][i] + " |" );
		}
		System.out.println("");
		}
	public void setzen(int position,int player)throws ReiheVollException{
		if(Anzahl[position-1]>6){
			throw new ReiheVollException();
		}
		if(player==1)Feld[position-1][Anzahl[position-1]]= "x";
		else Feld[position-1][Anzahl[position-1]]="o";
		Anzahl[position-1]++;
	}
	public boolean fertig(){
		for(int zeile=0;zeile<7;zeile++){
			if((Feld[3][zeile]=="x" || Feld[3][zeile]=="o") && (nachbarn(3, zeile)[1]>=3 || nachbarn(3, zeile)[0]>=3))return true;
			for(int i=0;i<4;i++){
				if(Feld[i][zeile]=="x"){
					if(Feld[i+1][zeile]=="x"){
						if(Feld[i+2][zeile]=="x"){
							if(Feld[i+3][zeile]=="x") return true;}}}

				else if(Feld[i][zeile]=="o"){
					if(Feld[i+1][zeile]=="o"){
						if(Feld[i+2][zeile]=="o"){
							if(Feld[i+3][zeile]=="o") return true;}}}

			}
			if(Feld[3][zeile]=="x"){
				
			}
			
		}
		for(int spalte=0;spalte<7;spalte++){
			for(int i=0;i<4;i++){
				if(Feld[spalte][i]=="x"){
					if(Feld[spalte][i+1]=="x"){
						if(Feld[spalte][i+2]=="x"){
							if(Feld[spalte][i+3]=="x") return true;}}}

				else if(Feld[spalte][i]=="o"){
					if(Feld[spalte][i+1]=="o"){
						if(Feld[spalte][i+2]=="o"){
							if(Feld[spalte][i+3]=="o") return true;}}}
			}
		}
		
		return false;
	}
	private int[] nachbarn(int x, int y){
		int[] nachbar = new int[3];
		nachbar[1]=0;
		nachbar[0]=0;
		nachbar[2]=0;
		for(int index=1; index <4;index++){
			try{
			if(Feld[x][y]==Feld[x+index][y+index]){
				nachbar[0] += 1;
				
			}
			else break;
			}
			catch(Exception e){
				break;
			}
		}
		for(int index=1; index <4;index++){
			try{
			if(Feld[x][y]==Feld[x-index][y-index]){
				nachbar[0] += 1;
				
			}
			else break;
			}
			catch(Exception e){
				break;
			}
		}
		for(int index=1; index <4;index++){
			try{
			if(Feld[x][y]==Feld[x+index][y-index]){
				nachbar[1] += 1;
				
			}
			else break;
			}
			catch(Exception e){
				break;
			}
		}
		
		for(int index=1; index <4;index++){
			try{
			if(Feld[x][y]==Feld[x-index][y+index]){
				nachbar[1] += 1;
				
			}
			else break;
			}
			catch(Exception e){
				break;
			}
		}
		
		
		return nachbar;
	}
}
