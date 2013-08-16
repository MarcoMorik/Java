import java.util.Scanner;
public class Spiel {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Der Spieler Nummer 1 hat das Zeichen x, der Spieler Nummer 2 das Zeichen o!");
		Spielfeld game= new Spielfeld();
		game.zeichneFeld();
		game.run(game);


	}
	public void run(Spielfeld game){
		int player = 1;
		Scanner inputScanner = new Scanner(System.in);	
		while(true){
			if(game.fertig())break;
			System.out.println("Spieler " + player +" ist dran");
			String input = inputScanner.next();
			if ("q".equals(input)) break;
			int zeile = Integer.parseInt(input);
			if(1 > zeile  || zeile > 7){ 
				System.err.println("Eingabe ungültig");
				continue;
				
			}
			try{
				game.setzen(zeile, player);
				if(player==1)player++;
				else player--;
				game.zeichneFeld();
			}
			catch(ReiheVollException e){
				System.err.println("Die Reihe ist schon voll" );
				continue;				
			}
			
		}
		
		
		inputScanner.close();
		if(player==1)player++;
		else player--;
		System.out.println("Der Sieger ist Spieler Nr."+ player );
	}

}

