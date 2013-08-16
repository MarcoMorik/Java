import java.util.Random;
public class Spielstand{
	private int[] geheimzahlen = new int[4];
	private int[] count = new int[6];
	public Spielstand(){
		for(int i=0; i<6;i++){
			count[i]=0;
		}
		Random random = new Random();
		for(int i=0;i<4;i++){
			this.geheimzahlen[i]= random.nextInt(6);
			count[this.geheimzahlen[i]]++;
		}}
		
	public String getZahlen(){
		String result = "|";
		for(int i = 0; i<4;i++){
			result = result + this.geheimzahlen[i] + "|";
		}
		return result;
	
	}
	public int[] vergleich( int[] eingabe){
		int[] result = {0,0};
		int[] anzahl = {0,0,0,0,0,0,};
		for(int i=0;i<4;i++){
			anzahl[eingabe[i]]++;
		}
		for(int i=0;i<4;i++){
			if(eingabe[i] == this.geheimzahlen[i]){
				result[0]++;
			}
		}
		for(int i=0;i<6;i++){
			if(count[i]>= anzahl[i]){
				result[1]+=anzahl[i];
			}
			else{
				result[1]+=count[i];
			}
		}
		result[1] -= result[0];
		return result;
	}
}
