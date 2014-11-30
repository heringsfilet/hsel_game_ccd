package blackjack;

import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		new BlackJack().run();
	}
	
	public void run(){
		int userAbort = 0;
		int totalValue = 0;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("easyBlackJack: Du entscheidest ob du ziehst oder nicht. Es sind jedes Mal 2-10 Punkte möglich.");
		System.out.println("Bei genau 21 hast du gewonnen. Bei über 21 hast du verloren.");
		System.out.println("Solltest du unter 21 liegen und nicht mehr ziehen, hat der PC die Chance einmal zu ziehen. Kommt er danach <= 21, gewinnt der PC.");
			
		do{
			int currentValue = generateValue();
			totalValue += currentValue;
			System.out.println("Du hast eine "+currentValue+" gezogen. Damit hast du jetzt "+totalValue+" Punkte!");

			System.out.println("Abbrechen? (0/1)");
			userAbort = in.nextInt();
		}while((userAbort!=1) && (totalValue < 21));
		in.close();
		
		if(totalValue <21){
			totalValue += generateValue();
			if(totalValue<=21){
				System.out.println("verloren, Mut tut manchmal gut :(  - der PC hat "+totalValue+" Punkte");
			}else{
				System.out.println("gewonnen, da war der PC zu gierig :) - der PC hat "+totalValue+" Punkte");
			}
		}else if(totalValue==21){
			System.out.println("gewonnen! Glatt 21 :)");
		}else if(totalValue>21){
			System.out.println("verloren, wer hoch hinaus will - kann auch tief fallen :( - Du hast "+totalValue+" Punkte");
		}
	}
	
	public int generateValue(){
		return ((int)(Math.random()*9))+2;
	}
}