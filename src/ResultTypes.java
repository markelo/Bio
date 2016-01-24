import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
@XmlRootElement(name = "types")
public class ResultTypes {
    private List<ResultType> types;

    @XmlElement(name = "type")
    public List<ResultType> getTypes() {
        return types;
    }

    public void setTypes(final List<ResultType> types) {
        this.types = types;
    }
}
