package Converter;

import java.util.LinkedList;
import java.util.List;

/**
 * Our HashMap implementation. the structure of the map is like below:
 *
 * Actually, the map is a table, each slot/cell in the table is a list of Node
 * [0] => [Node]->[Node]->[Node] [1] => [2] => [Node] [3] =>
 * [Node]->[Node]->[Node]
 *
 *
 */
public class HashMap {

    private List<LinkedList<Node>> table;
    // number of slots in the table.
    private int capacity;
    // number of keys in the table.
    private int numKeys;

    // if reach the maxLoad, the table will be rehashed.
    private double maxLoad;

    public HashMap() {
        maxLoad = 0.8;
        capacity = 10;
        numKeys = 0;
        // create the table
        table =  new LinkedList<LinkedList<Node>>();

        // set every row in the table to an empty LinkedList
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<Node>());
        }
    }

    // the hash function.
    private int hash(char c) {

        return (c - '\0') % capacity;
    }

    // find a char in the table.
    public Node find(char c) {
        int offset = hash(c);
        // find in the list.
        for (Node node : table.get(offset)) {
            if (node.getMarleyChar() == c) {
                return node;
            }
        }

        // not found.
        return null;
    }

    // put a char-char to the table.
    public void put(char c, char c2) {
        int offset = hash(c);

        if (find(c) != null) {
            // already in the table, need to update it.
            for (Node node : table.get(offset)) {
                if (node.getMarleyChar() == c) {
                    node.setEnglishChar(c2);
                    return;
                }
            }
            return;
        }

        // not in the table, need to insert it.
        // check if should rehash the table.
        if (numKeys > capacity * maxLoad) {
            rehash();
        }

        // create new node and add to list.
        Node node = new Node(c, c2);
        offset = hash(c);
        table.get(offset).add(node);

        // increase number of keys
        this.numKeys++;
    }

    // rehash/resize the table.
    private void rehash() {
        int oldCapacity = capacity;

        // double the size
        capacity *= 2;

        // create the new table
        List<LinkedList<Node>> newTable =  new LinkedList<LinkedList<Node>>();;

        // set every row in the table to an empty LinkedList
        for (int i = 0; i < capacity; i++) {
            newTable.add(new LinkedList<Node>());
        }

        // copy old table to new table
        // for each row in the table
        for (int i = 0; i < oldCapacity; i++) {
            // for each entry of the row
            for (Node entry : table.get(i)) {
                // add to new table
                char key = entry.getMarleyChar();

                int offset = hash(key);

                newTable.get(offset).add(entry);
            }
        }

        // switch to the new table
        table = newTable;

    }

}
