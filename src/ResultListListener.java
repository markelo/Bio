import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class ResultListListener implements ListSelectionListener {
    private MainForm mainForm;
    private List<String> values;

    public ResultListListener(final MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void setValues(final List<String> values) {
        this.values = values;
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        final ListSelectionModel model = ((JList) e.getSource()).getSelectionModel();
        final HttpService service = HttpService.getInstance();
        final String resultUrl = service.createResultUrl(mainForm.getJobId(), values.get(model.getMaxSelectionIndex()));
        mainForm.setResultUrl(resultUrl);
    }
}
