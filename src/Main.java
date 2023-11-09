import AbstractionTest.AbstractClass;
import AbstractionTest.AbstractionTest;
import Tries.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        var trie = new Trie();
        trie.insert("car");
        trie.insert("card");
        trie.insert("care");
        trie.insert("careful");
        trie.insert("egg");
        System.out.println(trie.findWords(""));
        System.out.println(trie.countWords());
    }

    
}

