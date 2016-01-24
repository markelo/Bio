import org.apache.http.impl.client.DefaultHttpClient;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO: Document this class / interface here
 *
 * @since v7.0
 */
public class MainForm extends JFrame {
    private String url;
    private JList list1;
    private JPanel panel1;
    private JList list2;
    private JButton button1;
    private JLabel jobIdLabel;
    private JTextField textField1;
    private JButton checkResultButton;
    private JLabel statusLabel;
    private JList list3;
    private JTextField resultAddress;
    private SharedSelectionListener listener;
    private DetailsSelectionListener detailsListener;
    private ResultListListener resultListListener;
    private Map<String, String> values;
    private String currentId;
    private HttpService service;
    private String jobId;

    public MainForm() {
        super("Main Form");
        setSize(800, 700);
        setContentPane(panel1);
        listener = new SharedSelectionListener(this);
        detailsListener = new DetailsSelectionListener(this);
        resultListListener = new ResultListListener(this);
        list1.addListSelectionListener(listener);
        list2.addListSelectionListener(detailsListener);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        values = new HashMap<>();
        values.put("email", "sebastian.mialkowski@wp.pl");
        button1.addActionListener(new SubmitJobButton(HttpService.getInstance(), this));
        currentId = "";
        service = HttpService.getInstance();
        checkResultButton.addActionListener(e -> {
            final String status = service.checkStatus(jobId);
            if (status.equals("FINISHED")) {
                final List<String> resultTypes = service.getResultTypes(jobId);
                list3.setListData(resultTypes.toArray());
                resultListListener.setValues(resultTypes);
            }
            statusLabel.setText(status);
        });
        list3.addListSelectionListener(resultListListener);
    }

    public void setCurrentId(final String currentId) {
        this.currentId = currentId;
    }

    public void setListData(final List<String> names) {
        list1.setListData(names.toArray());
        listener.setIds(names);
    }

    public void setResultUrl(final String resultUrl) {
        resultAddress.setText(resultUrl);
    }

    public void setDetailsValue(final List<String> values) {
        list2.setListData(values.toArray());
        detailsListener.setData(values);
    }

    public void setDetailedValue(final String value) {
        values.put(currentId, value);
    }

    public Map<String, String> getValuesMap() {
        values.put("sequence", textField1.getText());
        return values;
    }

    public void setJobId(final String jobId) {
        this.jobId = jobId;
//        jobIdLabel.setText(jobId);
    }

    public String getJobId() {
        return jobId;
    }

    public String getSequence() {
        return textField1.getText();
    }
}
