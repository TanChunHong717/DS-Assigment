
package Converter;

/**
 * The MapConverter, will convert a char to the corresponding char.
 *
 */
public class MapConverter implements ConverterInterface {

    private HashMap hashmap;

    public MapConverter() {
        hashmap = new HashMap();
        this.populateNodes();
    }

    private void populateNodes() {
        hashmap.put('a', 'j');
        hashmap.put('b', 'c');
        hashmap.put('c', 't');
        hashmap.put('d', 'a');
        hashmap.put('e', 'k');
        hashmap.put('f', 'z');
        hashmap.put('g', 's');
        hashmap.put('h', 'i');
        hashmap.put('i', 'w');
        hashmap.put('j', 'x');
        hashmap.put('k', 'o');
        hashmap.put('l', 'n');
        hashmap.put('m', 'g');
        hashmap.put('n', 'b');
        hashmap.put('o', 'f');
        hashmap.put('p', 'h');
        hashmap.put('q', 'l');
        hashmap.put('r', 'd');
        hashmap.put('s', 'e');
        hashmap.put('t', 'y');
        hashmap.put('u', 'm');
        hashmap.put('v', 'v');
        hashmap.put('w', 'u');
        hashmap.put('x', 'p');
        hashmap.put('y', 'q');
        hashmap.put('z', 'r');

        hashmap.put('$', ' ');
        hashmap.put(',', ',');
    }

    public char convert(char c) {
        Node node = findNode(c);
        if (node == null) {
            return '\0';
        }

        char e = node.getEnglishChar();
        return e;
    }

    private Node findNode(char marleyChar) {
        return hashmap.find(marleyChar);
    }

}
