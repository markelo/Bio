import org.apache.http.impl.client.DefaultHttpClient;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class SharedSelectionListener implements ListSelectionListener {
    private final MainForm mainForm;
    private List<String> ids;

    public SharedSelectionListener(final MainForm mainForm) {
        this.mainForm = mainForm;
    }

    public void setIds(final List<String> ids) {
        this.ids = ids;
    }
    @Override
    public void valueChanged(final ListSelectionEvent e) {
        final ListSelectionModel selectionModel = ((JList) e.getSource()).getSelectionModel();
        final HttpService service = HttpService.getInstance();
        final List<String> values = service.getParameterDetails(ids.get(selectionModel.getMaxSelectionIndex()));
        mainForm.setDetailsValue(values);
        mainForm.setCurrentId(ids.get(selectionModel.getMaxSelectionIndex()));
    }
}
