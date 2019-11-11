package com.practice;

import com.practice.tree.SegmentTree;
import com.practice.tree.Trie;

/**
 * @author MoFeng
 */
public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.recursionAdd("cat");
        boolean a = trie.contains("cat");
        System.out.println(a);
    }
}
