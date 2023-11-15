import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class GameReturn implements WordSearchGame {

    private TreeSet<String> lexicon = new TreeSet<String>();
    private boolean lexiconLoaded = false;
    private String[][] box;
    private boolean visited[][];
    private int square;



    @Override
    public void loadLexicon(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("fileName is null");
        }

        // Load lexicon into a tree set structure //
        Scanner scan = null;
        try {
            scan = new Scanner(new File(fileName));
            while (scan.hasNext()) {
                lexicon.add(scan.next().toUpperCase());
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        scan.close();
        lexiconLoaded = true;
    }

    @Override
    public void setBoard(String[] letterArray) {
        if (letterArray == null) {
            throw new IllegalArgumentException();
        }
        if (Math.sqrt(letterArray.length) != Math.round(Math.sqrt(letterArray.length))) {
            throw new IllegalArgumentException();
        }
        double math = Math.sqrt(letterArray.length);
        if (math == Math.round(math)) {
            int n2 = (int) math;
            box = new String[n2][n2];

            for (int i = 0; i < n2; i++) {
                for (int j = 0; j < n2; j++) {
                    box[i][j] = letterArray[(i * n2 + j)];
                }
            }
            //board = Arrays.copyOf(letterArray, letterArray.length);
            square = n2;
        }
//        box = letterArray;
//        square = (int) Math.sqrt(letterArray.length);
    }

    @Override
    public String getBoard() {
        String got = "";
        int spot = 0;
        for (String[] element : box) {
            for (String element2 : element) {
                if (spot == square) {
                    got = got + "\n";
                    spot = 0;
                }
                got = got + (" " + element + " ");
                spot++;
            }
        }
        return got;
    }

    @Override
    public SortedSet<String> getAllScorableWords(int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (!lexiconLoaded) {
            throw new IllegalStateException();
        }
        SortedSet<String> words = new TreeSet<String>();
        visited = new boolean[square][square];
        for (String word : lexicon) {
            for (int i = 0; i < square; i++) {
                for (int j = 0; j < square; j++) {
                    if (word.charAt(0) == box[i][j].charAt(0)) {
                        if (word.length() >= minimumWordLength) {
                            fullSearch(word, i, j, "", visited, words);
                        }
                    }
                }
            }
        }

        return words;
    }

    @Override
    public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
        if (minimumWordLength < 1) {
            throw new IllegalArgumentException();
        }
        if (!lexiconLoaded) {
            throw new IllegalStateException();
        }
        int score = 0;
        for (String word : words) {
            // either minimumwordlength or minimumwordlength + 1 //
            score = score + word.length() - minimumWordLength + 1;
        }
        return score;
    }

    @Override
    public boolean isValidWord(String wordToCheck) {
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (!lexiconLoaded) {
            throw new IllegalStateException();
        }
        return lexicon.contains(wordToCheck.toUpperCase());
    }

    @Override
    public boolean isValidPrefix(String prefixToCheck) {
        if (prefixToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (!lexiconLoaded) {
            throw new IllegalStateException();
        }
        // revisit if it doesn't run efficiently //
        for (String word : lexicon) {
            if (word.startsWith(prefixToCheck.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    @Override
    // currently returns correct grid spots, but does not guarantee order //
    public List<Integer> isOnBoard(String wordToCheck) {
        // exception checks //
        if (wordToCheck == null) {
            throw new IllegalArgumentException();
        }
        if (!lexiconLoaded) {
            throw new IllegalStateException();
        }

        // instantiate variables //
        ArrayList<Integer> path = new ArrayList<>();
        visited = new boolean[square][square];
        int yspot = 0;
        int xspot = 0;

        // loop through the board //
        for (String[] element : box) {
            for (String element2 : element) {
                fullSearchPath(wordToCheck, xspot, yspot, path, "", visited);
                xspot++;
            }
            yspot++;
            xspot = 0;
        }
        return path;
    }

    private int index(int row, int col) {
        return row * square + col;
    }

    private void fullSearch(String wordToCheck, int y, int x, String finding, boolean[][] vis, SortedSet<String> words) {
        // chores //
        vis[y][x] = true;
        finding = finding + box[y][x];

        if (finding.equals(wordToCheck)) {
            words.add(finding);
        }
        else if (wordToCheck.startsWith(finding)) {
            for (int row = y-1; row <= y+1 && row < square; row++) {
                for (int col = x-1; col <= x+1 && col < square; col++) {
                    if (row >= 0 && col >= 0 && !vis[row][col]) {
                        fullSearch(wordToCheck, row, col, finding, vis, words);
                    }
                }
            }
        }
        finding = finding.substring(0, finding.length() -1);
        vis[y][x] = false;

    }
    private void fullSearchPath(String wordToCheck, int y, int x, ArrayList<Integer> path, String finding, boolean[][] vis) {
        // chores //
        vis[y][x] = true;
        finding = finding + box[y][x];




        // check every adjacent spot for second letter //
        if (finding.equals(wordToCheck)) {
            int count = 0;
            while (count <= finding.length() - 1) {
                int j = 0;
                int k = 0;
                boolean fresh = true;
                for (String[] s : box) {
                    for (String str : s) {
                        if (count <= finding.length() - 1 && vis[j][k] && box[j][k].charAt(0) == wordToCheck.charAt(count)
                                && fresh) {
                            fresh = false;
                            path.add(k + (j * square));
                            count++;
                        }
                        k++;
                    }
                    j++;
                    k = 0;
                }
            }
        }

        else if (wordToCheck.startsWith(finding)) {
            for (int row = y-1; row <= y+1 && row < square; row++) {
                for (int col = x-1; col <= x+1 && col < square; col++) {
                    if (row >= 0 && col >= 0 && !vis[row][col]) {
                        fullSearchPath(wordToCheck, row, col, path, finding, vis);
                    }

                }
            }
        }
        finding = finding.substring(0, finding.length() -1);
        vis[y][x] = false;
    }



    public static class ExampleGameClient {
        public static void main(String[] args) {
            WordSearchGame game = WordSearchGameFactory.createGame();
            game.loadLexicon("wordfiles/words.txt");
            game.setBoard(new String[]{"E", "E", "C", "A", "A", "L", "E", "P", "H",
                    "N", "B", "O", "Q", "T", "K", "Y"});
            System.out.print("LENT is on the board at the following positions: ");
            System.out.println(game.isOnBoard("LENT"));
            System.out.print("POPE is not on the board: ");
            System.out.println(game.isOnBoard("POPE"));
            System.out.print("PEACE is on the board at the following positions: ");
            System.out.println(game.isOnBoard("PEACE"));
            System.out.println("All words of length 3 or more: ");
            System.out.println(game.getAllScorableWords(3));
            System.out.println(game.getScoreForWords(game.getAllScorableWords(3), 3));
        }
    }
}


