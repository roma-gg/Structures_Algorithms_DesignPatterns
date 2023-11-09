package Maps;

import java.util.HashSet;

public class Set {

    String str = "a green apple";

    public String firstRepeatedChar(String str) {
        var set = new HashSet<Character>();
        for (int i = 0; i < str.length(); i++) {
            if (set.contains(str.charAt(i))) {
                return String.valueOf(str.charAt(i));
            }
            else
                set.add(str.charAt(i));
        }
        return "There are no repeated characters";
    }
}
