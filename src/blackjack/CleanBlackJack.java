package blackjack;

import java.util.Scanner;

/*
 *  Kommentare sind bewusst f�r die �bung enthalten, widerspricht eigentlich Clean Code :)
 *  Issue Tracking ist schwer zu belegen und in dem Umfang der �bung auch Overkill.
 *  "In echt" sollte so etwas nat�rlich mit einem Issue Tracker (JIRA, Redmine, ...) verbunden sein und beim Commit
 *  auch darauf verweisen. Selbstverst�ndlich g�be es dann auch Unit und Integrationstest, die innerhalb des CI-Prozesses
 *  (Jenkins, Bamboo, ...) gepr�ft werden. Erfahrungsgem�� bei mir hilft hier Gamification-Elemente (Punktevergabe
 *  f�r Unit-Tests bzw. fehlgeschlagene Unit-Tests)
 *  Code-Review gab es spa�eshalber mit einem Kollegen, um die �bung nicht dadurch zu verf�lschen sind die Ergebnisse bzw.
 *  R�ckmeldungen aber nicht eingearbeitet.
 */

public class CleanBlackJack {

	// CheckStyle: private statt public & Umbenennung damit lesbar - precise naming
	private int currentTotalCardValue = 0;
	
	// CheckStyle: Konstante definieren (reuse)
	final int WIN_CARDVALUE_THRESHOLD = 21;
	
	// CheckStyle args als final
	public static void main(String[] args) {
		new CleanBlackJack().run();
	}
	
	/* 	Versuch alles in "calling Code" zu verwandeln. Doing ausgelagert in andere Methoden. 
	 *  F�hrt auch zu insgesamt k�rzeren Methoden (Kommentare hier ausgenommen), was im Sinne von Clean Code ist.
	 */
	public void run() {
		/* 	Umwandlung Variable in Boolean & Umbennenung damit lesbar. "Stay" ist scheinbar ein Begriff im BlackJack und
		*	sollte daher bekannt sein
		*/
		boolean userWantToContinue = true;
		
		showInstructions();
		
		Scanner in = new Scanner(System.in);
		takeCard(generateValue());		
		
		do{
			System.out.println("Weiterspielen? (0/1)");
			userWantToContinue = in.nextInt() == 1;
			
			takeCard(generateValue());
		} while(userWantToContinue && cardValueIsUnderWinThreshold(currentTotalCardValue));
		
		String result = checkResult();
		System.out.println(result);
	}
	
	public boolean cardValueIsUnderWinThreshold(int totalCardValue){
		return totalCardValue < WIN_CARDVALUE_THRESHOLD;
	}
	
	// Clean Code: Berechnungen und Aufruf trennen
	public int takeCard(int value) {
		currentTotalCardValue += value;
		// CheckStyle: Leerzeichen hinzugef�gt
		System.out.println("Du hast eine " + value + " gezogen. Damit hast du jetzt " + currentTotalCardValue + " Punkte!");
		return value;
	}
	
	// Clean Code: leichter lesbar im Fluss
	public boolean gameGoesOn(boolean userWillStay, int totalValue) {
		return (!userWillStay) && (totalValue < WIN_CARDVALUE_THRESHOLD);	
	}
	
	/* ToDo Checkstyle ignoriert. 9 ist zwar Magic Number, auslagern macht f�r mich aber in diesem Kontext wenig Sinn
	 * Da Einsatzzweck im BlackJack die 9 fest definiert
	 */
	public int generateValue() {
		return ((int)(Math.random() * 9)) + 2;
	}
	
	public void showInstructions(){
		//CheckStyle willentlich ignoriert: "maximale Zeilenl�nge 80"
		System.out.println("easyBlackJack: Du entscheidest ob du ziehst oder nicht. Es sind jedes Mal 2-10 Punkte m�glich.");
		System.out.println("Bei genau 21 hast du gewonnen. Bei �ber 21 hast du verloren.");
		System.out.println("Solltest du unter 21 liegen und nicht mehr ziehen, hat der PC die Chance einmal zu ziehen. Kommt er danach <= 21, gewinnt der PC.");
	}
	
	public String checkResult() {
		String result ="";
		if (currentTotalCardValue < WIN_CARDVALUE_THRESHOLD) {
			takeCard(generateValue());
			if (currentTotalCardValue <= WIN_CARDVALUE_THRESHOLD) {
				result = "verloren, Mut tut manchmal gut :(  - der PC hat " + currentTotalCardValue + " Punkte";
			}else {
				result = "gewonnen, da war der PC zu gierig :) - der PC hat " + currentTotalCardValue + " Punkte";
			}
		} else if (currentTotalCardValue == WIN_CARDVALUE_THRESHOLD) {
			result = "gewonnen! Glatt " + WIN_CARDVALUE_THRESHOLD + "Flasche Sekt :)";
		} else if (currentTotalCardValue > WIN_CARDVALUE_THRESHOLD) {
			result = "verloren, wer hoch hinaus will - kann auch tief fallen :( - Du hast " + currentTotalCardValue + " Punkte";
		}
		return result;
	}
}