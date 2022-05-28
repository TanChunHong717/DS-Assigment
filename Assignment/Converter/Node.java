package Converter;

public class Node {

    private char marleyChar;
    private char englishChar;

    public Node(char marleyChar, char englishChar) {
        this.marleyChar = marleyChar;
        this.englishChar = englishChar;
    }

    public char getEnglishChar() {
        return englishChar;
    }

    public char getMarleyChar() {
        return marleyChar;
    }

    public void setMarleyChar(char marleyChar) {
        this.marleyChar = marleyChar;
    }

    public void setEnglishChar(char englishChar) {
        this.englishChar = englishChar;
    }

	

}
