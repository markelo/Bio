import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @since v7.0
 */
public class HttpService {
    private final HttpClient client;
    private String url = "http://www.ebi.ac.uk/Tools/services/rest/fasta/";
    private static HttpService instance = null;


    public static HttpService getInstance() {
        if (instance == null) {
            instance = new HttpService(new DefaultHttpClient());
        }
        return instance;
    }

    private HttpService(final HttpClient client) {
        this.client = client;
    }

    public String getParameters() {
        final HttpGet request = new HttpGet(url + "parameters");
        try {
            final HttpResponse response = client.execute(request);
            return getContentAsString(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setUrl(final String url) {
        this.url = url;
    }

    public String createResultUrl(final String jobId, final String format) {
        return url + "result/" + jobId + "/" + format;
    }

    public List<String> getParameterDetails(final String parameter) {
        final HttpGet request = new HttpGet(url + "parameterdetails/" + parameter);
        System.out.println(request.getURI());
        try {
            final HttpResponse response = client.execute(request);
            final String content = getContentAsString(response);
            final JAXBContext context = JAXBContext.newInstance(ParameterDetails.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final ParameterDetails details = (ParameterDetails) unmarshaller.unmarshal(IOUtils.toInputStream(content));
            System.out.println(details.getValues());
            return details.getValues().stream().map(ParameterValue::getValue).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<String> getResultTypes(final String jobId) {
        final HttpGet request = new HttpGet(url + "resulttypes/" + jobId);
        try {
            final HttpResponse response = client.execute(request);
            final String content = getContentAsString(response);
            final JAXBContext context = JAXBContext.newInstance(ResultTypes.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final ResultTypes resultTypes = (ResultTypes) unmarshaller.unmarshal(IOUtils.toInputStream(content));
            return resultTypes.getTypes().stream().map(ResultType::getIdentifier).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public String submitJob(final Map<String, String> valuesMap) {
        final HttpPost request = new HttpPost(url + "run?" + prepareRequestParam(valuesMap));
        request.setHeader("Content-Type", "application/x-www-form-urlencoded");
        try {
            final HttpResponse response = client.execute(request);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            //Should be only one line
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String checkStatus(final String jobId) {
        final HttpGet request = new HttpGet(url + "status/" + jobId);
        try {
            final HttpResponse response = client.execute(request);
            final BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            return reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private String prepareRequestParam(final Map<String, String> valuesMap) {
        final StringBuilder builder = new StringBuilder();
        for(final Map.Entry<String, String> entry : valuesMap.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return builder.toString();
    }

    private String getContentAsString(final HttpResponse response) {
        final InputStream stream;
        final StringBuilder builder = new StringBuilder();
        try {
            stream = response.getEntity().getContent();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
}
