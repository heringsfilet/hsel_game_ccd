package blackjack;

import static org.junit.Assert.*;

import org.junit.Test;

public class BlackJackTest {

	@Test
	public void testGenerateValueShouldBetweenTwoAndTen() {
		CleanBlackJack bj = new CleanBlackJack();
		int cardValue = bj.generateValue();
		
		int high = 10;
		int low = 2;
		
		for(int i=0; i < 9900000; i++){
			assertTrue("Error, Kartenwert ist zu hoch", high > cardValue);
			assertTrue("Error, Kartenwert ist zu niedrig",  low  < cardValue);
		}
	}
}
