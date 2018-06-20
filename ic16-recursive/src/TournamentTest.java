import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Tournament data type.
 */
public class TournamentTest {
    
    // MISSING testing strategy
    
    @Test public void testWinnerSinglePlayer() {
        Player player = new Player("Alice", 98);
        Tournament singlePlayer = Tournament.single(player);
        Player winner = singlePlayer.winner();
        assertEquals(player, winner);
    }
    
    @Test public void testWinnerOneMatch() {
        Player player1 = new Player("Alice", 98);
        Player player2 = new Player("Bob", 76);
        Tournament oneMatch = Tournament.match(Tournament.single(player1),Tournament.single(player2));
        Player winner = oneMatch.winner();
        assertEquals(player1, winner);
        }
    
    @Test public void testBalancedMatch(){
		Tournament player_1= Tournament.single(new Player("Alex", 34));
		Tournament player_2= Tournament.single(new Player("Faaya", 56));
		Tournament player_3= Tournament.single(new Player("Geleta", 87));
		Tournament player_4= Tournament.single(new Player("Abate", 87));
		
		Tournament match_1= Tournament.match(player_1, player_4);
		Tournament match_2= Tournament.match(player_3, player_2);
		Tournament match_final= Tournament.match(match_1, match_2);
		
		assertTrue(match_final.balanced());
    }
    
}
