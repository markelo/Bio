import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
@XmlRootElement(name = "type")
public class ResultType {
    private String identifier;

    @XmlElement(name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(final String identifier) {
        this.identifier = identifier;
    }
}
