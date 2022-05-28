package Converter;

/**
 *
 * The ReverseBufferConverter will accumulate chars in the buffer, reverse the
 * buffer at end.
 */
public class ReverseBufferConverter implements ConverterInterface {

    private boolean isOn = false;

    private String buffer;

    public char convert(char c) {
        if (isOn) {
            buffer += c;
        }
        return '\0';
    }

    public void setOn() {
        buffer = "";
        isOn = true;
    }

    public void setOff() {
        // reverse the buffer.
        buffer = new StringBuilder(buffer).reverse().toString();
        isOn = false;
    }

    public boolean isOn() {
        return isOn;
    }

    public String getBuffer() {
        return buffer;
    }

}
