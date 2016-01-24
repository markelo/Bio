import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class SubmitJobButton implements ActionListener {
    private HttpService service;
    private MainForm mainForm;

    public SubmitJobButton(final HttpService httpService, final MainForm mainForm) {
        this.service = httpService;
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Map<String, String> valuesMap = mainForm.getValuesMap();
        final String jobId = service.submitJob(valuesMap);
        mainForm.setJobId(jobId);
    }
}
