import static org.mockito.Mockito.when;

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
		
	}

}
