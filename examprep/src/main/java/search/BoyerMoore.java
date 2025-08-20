package main.java.search;

public interface BoyerMoore {

    static int NO_OF_CHARS = 256;

    default int indexOf(String subString, String string) {
        int m = subString.length();
        int n = string.length();

        int badchar[] = badCharHeur(subString);

        int s = 0; // Verschiebung des Patterns unter dem String
        while (s <= (n - m)) { // Sollte die Verschiebung größer als die Differenz zwischen der Länge des
                               // Strings und des Patterns sein
                               // kann das Pattern ja gar nicht mehr vorkommen, da im String nicht mehr
                               // genügend Character zu betrachten
                               // sind.
            int j = m - 1; // Wir betrachten den String unter dem Pattern von rechts nach links!
            while (j >= 0 && (s + j) < n && subString.charAt(j) == string.charAt(s + j))
                j--;
            if (j < 0)
                return s; // Sollten wir es geschafft haben, das gesamte Pattern mit dem String zu
                          // vergleichen, OHNE >=1 MISMATCH zu finden
                          // haben wir offenbar das Pattern bei Shift s gefunden!
            else
                s += Math.max(1, j - badchar[string.charAt(s + j)]);
            // Sonst sprigen wir um die Zahl der "schlechten Character" die wir zuvor
            // ermittelt haben
        }
        return -1;
    }

    private int[] badCharHeur(String pattern) {
        int badchar[] = new int[NO_OF_CHARS];
        for (int i = 0; i < NO_OF_CHARS; i++)
            badchar[i] = -1;
        for (int i = 0; i < pattern.length(); i++)
            badchar[(int) pattern.charAt(i)] = i;
        return badchar;
    }
}
