import javax.xml.bind.annotation.XmlElement;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class ParameterValue {
    private String value;

    @XmlElement(name = "value")
    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
