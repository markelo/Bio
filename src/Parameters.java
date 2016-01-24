import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
@XmlRootElement(name = "parameters")
public class Parameters {
    private List<String> ids;

    @XmlElement(name = "id")
    public List<String> getIds() {
        return ids;
    }

    public void setIds(final List<String> ids) {
        this.ids = ids;
    }
}
