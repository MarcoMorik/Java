
public class LabyrinthA {
	public LabyrinthA(int breite, int hoehe){
		createLab(breite, hoehe);
	}
	
	private void createLab(int breite, int hoehe){
		Spielfeld[][] Labyrinth;
		Labyrinth = new Spielfeld[breite][hoehe];
		for(int i=0; i<breite;i++){
			for(int j=0; j<hoehe;j++){
				Labyrinth[i][j] = new Spielfeld(i,j);
			}
		}
		for(int i=0; i<breite;i++){
			for(int j=0; j<hoehe;j++){
				Labyrinth[i][j].update(Labyrinth);
			}
		}
		
		
	}
	public void StartZielGenerator(Spielfeld[][] lab, int breite, int hoehe){
		int Starth = (int ) Math.random() * hoehe;
		int Startb = (int) Math.random() * breite;
		int Zielh = (int) Math.random() * hoehe;
		int Zielb = (int) Math.random() * breite;
		while(Zielh == Starth && Zielb == Startb){
			Zielh = (int) Math.random() * hoehe;
			Zielb = (int) Math.random() * breite;
		}
	}
}

