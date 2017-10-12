import static org.mockito.Mockito.when;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class TestAssignment4 {

	@Test
	public void testPlayRound() {
		Player player = new Player("TestPlayer", 100);
		Integer expected = 105;
		Dice d1 = org.mockito.Mockito.mock(Dice.class);
		Dice d2 = org.mockito.Mockito.mock(Dice.class);
		Dice d3 = org.mockito.Mockito.mock(Dice.class);

		when(d1.getValue()).thenReturn(DiceValue.CROWN);
		when(d2.getValue()).thenReturn(DiceValue.ANCHOR);
		when(d3.getValue()).thenReturn(DiceValue.ANCHOR);

		Game game = new Game(d1, d2, d3);
		game.playRound(player, DiceValue.CROWN, 5);
		Integer actual = player.getBalance();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testBalanceExceedsLimitBy() {
		int bet = 5;
		int balance = 5;
		int limit = 0;
		Player player = new Player("TestPlayer", balance);
		player.setLimit(limit);
		Boolean exceeds = player.balanceExceedsLimitBy(bet);

		String msg = "Balance %d minus bet %d did not exceed limit %d";
		Assert.assertTrue(String.format(msg, balance, bet, limit), exceeds);
	}

	@Test
	public void testPlayRound_ReturnWinnings() {
		Player player = new Player("TestPlayer", 100);
		Integer expected = 95;
		Dice d1 = org.mockito.Mockito.mock(Dice.class);
		Dice d2 = org.mockito.Mockito.mock(Dice.class);
		Dice d3 = org.mockito.Mockito.mock(Dice.class);

		when(d1.getValue()).thenReturn(DiceValue.ANCHOR);
		when(d2.getValue()).thenReturn(DiceValue.ANCHOR);
		when(d3.getValue()).thenReturn(DiceValue.ANCHOR);

		// assert than the player's balance doesn't increase
		Game game = new Game(d1, d2, d3);
		int winnings = game.playRound(player, DiceValue.CROWN, 5);
		Integer actual = player.getBalance();
		Assert.assertEquals(expected, actual);

		// assert that the winnings returned is 0
		String msg = "Winnings %d should be equal to 0";
		Assert.assertTrue(String.format(msg, winnings), winnings == 0);
	}

	@Test
	public void testWinLoseRatio() {

		Dice d1 = new Dice();
		Dice d2 = new Dice();
		Dice d3 = new Dice();
		
		Game game = new Game(d1, d2, d3);

		int maxTurns = 1000000;

		int winCount = 0;
		int loseCount = 0;

		String name = "Test Player";
		int balance = 100000000;
		int limit = 0;
		Player player = new Player(name, balance);
		player.setLimit(limit);
		int bet = 5;
				
		for (int turn = 0; turn < maxTurns; turn++) {

			DiceValue pick = DiceValue.getRandom();
			int winnings = game.playRound(player, pick, bet);

			if (winnings > 0) {
				winCount++;
			} else {
				loseCount++;
			}
		}

		double expected = 0.42;
		double actual = (double) (winCount) / ((double)winCount + (double)loseCount);
		Assert.assertEquals(expected, actual, 0.002);

	}
	
	@Test
	public void testRandomDistribution() {

		int[] values = new int[6];
		Random random = new Random();
		
		for (int i=0; i < 10000; i++) {
			values[random.nextInt(6)]++;
		}

		for (int value : values) {
			System.out.println(value);
		}
	}
	
	@Test
	public void testDiceValueDistribution() {

		int[] values = new int[6];
		
		for (int i=0; i < 10000; i++) {
			values[DiceValue.getRandom().ordinal()]++;
		}

		for (int value : values) {
			System.out.println(value);
		}
	}

}
