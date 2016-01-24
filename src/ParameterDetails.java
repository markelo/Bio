import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
@XmlRootElement(name="parameter")
public class ParameterDetails {

    private List<ParameterValue> values;
    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "description")
    private String description;

    public void setValues(final List<ParameterValue> values) {
        this.values = values;
    }

    @XmlElementWrapper(name = "values")
    @XmlElement(name = "value")
    public List<ParameterValue> getValues() {
        return values;
    }
}
