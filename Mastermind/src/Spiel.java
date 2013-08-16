import java.util.Scanner;
public class Spiel {
	static int[] eingabe = new int[4];
	public static void main(String[] args) {
		Spielstand game = new Spielstand();
		//for(int i=0;i<4;i++){
		//System.out.println( game.getZahlen()[i]);
		//}
		run(game);
	}
	public static void run(Spielstand game){
		int rundenzahl= 0;
		Scanner inputScanner = new Scanner(System.in);
		System.out.println("Machen sie ihre Eingaben");
		while(true){
			try{
			for(int i=0; i<4;i++){
			
				eingabe[i]= inputScanner.nextInt(6);
			}	}
			catch(Exception e){
				System.out.println("Falsche Eingabe");
				String error= inputScanner.nextLine();
				continue;
			}
			rundenzahl++;
			int[] Ausgabe = game.vergleich(eingabe);
			if(Ausgabe[0]>=4) break;
			System.out.println("|" + eingabe[0]+  "|"+ eingabe[1] + "|"+ eingabe[2] + "|"+ eingabe[3]+"|  " + Ausgabe[0] + " genau Treffer;  "+ Ausgabe[1] + " richtige Farben.");
		}
		System.out.println("Sie haben gewonnen, die richtige Lösung war: " + game.getZahlen());
		System.out.println("Sie haben "+ rundenzahl + " Runden gebraucht");
		System.out.println("Wollen sie ein neues Spiel beginnen");
		String asdf = inputScanner.next();
		if(asdf.equals("Ja")) {
			Spielstand game2 = new Spielstand();
			run(game2);}
		inputScanner.close();
	}

}
