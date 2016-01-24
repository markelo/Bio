import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Main {

    public static void main(String[] args) throws Exception {
        final HttpService service = HttpService.getInstance();
        final String parametersXml = service.getParameters();
        final MainForm mainForm = new MainForm();
        final JAXBContext context = JAXBContext.newInstance(Parameters.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Parameters parameters = (Parameters) unmarshaller.unmarshal(IOUtils.toInputStream(parametersXml));
        mainForm.setListData(parameters.getIds());
        service.getParameterDetails("program");
    }
}
