package Converter;

/**
 *
 * The UpperCaseConverter will convert one char to upper case.
 *
 */
public class UpperCaseConverter implements ConverterInterface {

    private boolean isOn = false;

    public char convert(char c) {
        if (isOn) {
            c = Character.toUpperCase(c);
        }

        // only convert one character.
        this.setOff();
        return c;

    }

    public void setOn() {
        isOn = true;
    }

    public void setOff() {
        isOn = false;
    }
}
