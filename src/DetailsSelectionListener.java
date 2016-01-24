import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class DetailsSelectionListener implements ListSelectionListener {
    private final MainForm mainForm;
    private List<String> data;

    public DetailsSelectionListener(final MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void setData(final List<String> data) {
        this.data = data;
    }

    @Override
    public void valueChanged(final ListSelectionEvent e) {
        final ListSelectionModel model = ((JList) e.getSource()).getSelectionModel();
        if (model.getMaxSelectionIndex() != -1) {
            mainForm.setDetailedValue(data.get(model.getMaxSelectionIndex()));
        }
    }
}
