
package Converter;

/**
 * the Converter class. It is consist of 3 concrete converters: MapConverter,
 * UpperCaseConverter and ReverseBufferConverter. While converting a string, all
 * the three converters will do the convert process.
 *
 */
public class Converter {

    private MapConverter mc = new MapConverter();
    private UpperCaseConverter ucmc = new UpperCaseConverter();
    private ReverseBufferConverter rbc = new ReverseBufferConverter();

    /**
     * convert a string
     *
     * @param Marley sentence.
     * @return Paradis sentence.
     */
    public String convert(String string) {
        String ret = "";
        // convert the characters one by one .
        for (int i = 0; i < string.length(); i++) {
            char marleyChar = string.charAt(i);
            if (marleyChar == '(') {
                rbc.setOn();
                continue;
            }

            if (marleyChar == ')') {
                rbc.setOff();
                ret += rbc.getBuffer();
                continue;
            }

            // special chars.
            if (marleyChar == '^') {
                ucmc.setOn();
                continue;
            }

            marleyChar = mc.convert(marleyChar);
            marleyChar = ucmc.convert(marleyChar);

            if (rbc.isOn()) {
                rbc.convert(marleyChar);
            } else {
                ret += marleyChar;
            }

        }

        return ret;
    }

}
