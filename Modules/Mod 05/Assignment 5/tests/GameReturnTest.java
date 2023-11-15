import static org.junit.jupiter.api.Assertions.*;

class GameReturnTest {

    @org.junit.jupiter.api.Test
    void loadLexicon() {
    }

    @org.junit.jupiter.api.Test
    void isValidWord() {
    }

    @org.junit.jupiter.api.Test
    void isValidPrefix() {
        WordSearchGame game = WordSearchGameFactory.createGame();
        game.loadLexicon("wordfiles/words.txt");
        assertTrue(game.isValidPrefix("A"));
    }
}
