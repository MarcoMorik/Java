
	public class wetter {
		public static void main(String[] args) {
			// Aufgabe 1.) day wird nicht benoetigt da man es ueber den index machen
			// kann
			int[] day = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
			int[] temperature = { 12, 14, 9, 12, 15, 16, 15, 15, 11, 8, 13, 13, 15, 12 };

			// Aufgabe 2.)
			System.out.println("2.) \t Durchschnittstemperatur");
			int average = 0;
			for (int i = 0; i < temperature.length; i++) {
				average = average + temperature[i];
			}
			average = average / temperature.length;
			System.out.println("Durchschnittstemeratur betraegt: " + average);

			// Aufgabe 3.)
			System.out.println("3.) \t maximale und minimale Temperatur");
			int max = temperature[0];
			int min = temperature[0];
			for (int i = 0; i < temperature.length; i++) {
				if (max < temperature[i]) {
					max = temperature[i];
				}
				if (min > temperature[i]) {
					min = temperature[i];
				}
			}
			System.out.println("Maximale Temperatur: " + max);
			System.out.println("Minimale Temperatur: " + min);

			// Aufgabe 4.)
			System.out.println("4.) \t beiden aufeinanderfolgenden Tage angeben kann, an denen es den größten Temperaturumschwung gab");
			int maxTempDiff = 0;
			int foundDay = 0;
			for (int i = 0; i < temperature.length; i++) {
				int newMaxDiff = 0;
				if ((i + 1) < temperature.length) {
					if (temperature[i] < temperature[i + 1]) {
						newMaxDiff = temperature[i + 1] - temperature[i];
					}
					if (temperature[i] >= temperature[i + 1]) {
						newMaxDiff = temperature[i] - temperature[i + 1];
					}
					if (maxTempDiff < newMaxDiff) {
						maxTempDiff = newMaxDiff;
						foundDay = i;
					}
				}
			}
			if (maxTempDiff != 0) {
				System.out.println("Maximale Temperatur unterschied: " + maxTempDiff + " von Tag " + (foundDay + 1) + " zu Tag " + (foundDay + 2));
			} else {
				System.out.println("Alle Temperaturen sind gleich");
			}

			// Aufgabe 5.)
			System.out.println("5.) \tTabelle schön tabellarisch auf der Konsole ausgibt");
			System.out.print("Tag: \t\t");
			for (int i = 0; i < day.length; i++) {
				System.out.print(day[i] + "\t");
			}
			System.out.println();
			System.out.print("Temperatur \t");
			for (int j = 0; j < temperature.length; j++) {
				System.out.print(temperature[j] + "\t");
			}
		}
	}
