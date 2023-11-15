import org.w3c.dom.Node;

import java.io.*;

import java.util.*;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. 
 *
 * @author Your Name (you@auburn.edu)
 */
public class Doublets implements WordLadderGame {

    private int wordCount;
    private TreeSet<String> lexicon = new TreeSet<String>();


    // The word list used to validate words.
    // Must be instantiated and populated in the constructor.
    /////////////////////////////////////////////////////////////////////////////
    // DECLARE A FIELD NAMED lexicon HERE. THIS FIELD IS USED TO STORE ALL THE //
    // WORDS IN THE WORD LIST. YOU CAN CREATE YOUR OWN COLLECTION FOR THIS     //
    // PURPOSE OF YOU CAN USE ONE OF THE JCF COLLECTIONS. SUGGESTED CHOICES    //
    // ARE TreeSet (a red-black tree) OR HashSet (a closed addressed hash      //
    // table with chaining).
    /////////////////////////////////////////////////////////////////////////////

    /**
     * Instantiates a new instance of Doublets with the lexicon populated with
     * the strings in the provided InputStream. The InputStream can be formatted
     * in different ways as long as the first string on each line is a word to be
     * stored in the lexicon.
     */
    public Doublets(InputStream in) {
        try {
            //////////////////////////////////////
            // INSTANTIATE lexicon OBJECT HERE  //
            //////////////////////////////////////
            //TreeSet<String> lexicon = new TreeSet<String>();
            Scanner s =
                    new Scanner(new BufferedReader(new InputStreamReader(in)));
            while (s.hasNext()) {
                String str = s.next();
                lexicon.add(str.toUpperCase());
                /////////////////////////////////////////////////////////////
                // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
                /////////////////////////////////////////////////////////////
                wordCount = lexicon.size();
                s.nextLine();

            }
            in.close();

        } catch (java.io.IOException e) {
            System.err.println("Error reading from InputStream.");
            System.exit(1);
        }
    }

    @Override
    public int getWordCount() {
        return wordCount;
    }

    @Override
    public boolean isWord(String str) {
        return lexicon.contains(str.toUpperCase());
    }

    @Override
    public int getHammingDistance(String str1, String str2) {
        int hammingDistance = 0;
        if (str1.length() != str2.length()) {
            return -1;
        }

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                hammingDistance++;
            }
        }
        return hammingDistance;
    }

    @Override
    public List<String> getNeighbors(String word) {
        word = word.toUpperCase();
        List<String> neighbors = new ArrayList<String>();
        for (String str : lexicon) {
            // removing .toUpperCase()
            if (getHammingDistance(word, str) == 1) {
                neighbors.add(str);
            }
        }
        return neighbors;
    }

    @Override
    public boolean isWordLadder(List<String> sequence) {

        if (sequence.isEmpty()) {
            return false;
        }

        if (sequence.size() == 1) {
            return true;
        }

        for (int i = 0; i < sequence.size() - 1; i++) {
            if (isWord(sequence.get(i).toUpperCase()) &&
                    getHammingDistance(sequence.get(i), sequence.get(i + 1)) == 1) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public List<String> getMinLadder(String start, String end) {
        String finding = start;
        ArrayList<String> badLadder = new ArrayList<String>();

        if (!(isWord(start) && isWord(end))) {
            return badLadder;
        }

        List<String> minLadder = new ArrayList<String>();
        if (start.equals(end)) {
            minLadder.add(start);
            return minLadder;
        }

        if (getNeighbors(start).contains(end.toUpperCase())) {
            minLadder.add(start);
            minLadder.add(end);
            return minLadder;
        }
        else {
            minLadder = breadthFirstSearch(start, end);
        }
        return minLadder;
    }

    private LinkedList<String> breadthFirstSearch(String start, String end) {
        start = start.toUpperCase();
        end = end.toUpperCase();

        HashSet<String> set = new HashSet<String>();
        set.add(start);

        LinkedList<String> path = new LinkedList<>();
        path.add(start);

        Deque<LinkedList<String>> queue = new LinkedList<>();
        queue.add(path);

        while (!queue.isEmpty()) {
            path = queue.removeFirst();
//              System.out.println(path);
            // check for indexing and casing later //
            if (path.getLast().equals(end)) {
                return path;
            }
            List<String> neighbors = getNeighbors(path.getLast());

            for (String neighbor : neighbors) {
                if (!set.contains(neighbor)) {
                    set.add(neighbor);
                    path.add(neighbor);
//                    System.out.println(path);
//                    LinkedList<String> newPath = new LinkedList<String>(path);
//                    newPath.add(neighbor);
                    if (neighbor.equals(end)) {
                        return path;
                    }
                    queue.add(new LinkedList<>(path));
                    path.removeLast();

                }
            }
        }
        return new LinkedList<String>();
    }

}


