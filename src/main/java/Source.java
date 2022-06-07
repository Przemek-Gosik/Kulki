import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;

public class Source {
    String temp_url;
    URL url;
    Source(String temp_url)throws Exception{
        this.temp_url=temp_url;
        setUrl(this.temp_url);
    }

    private void setUrl(String temp_url) throws  Exception{
        this.url = new URL(temp_url);
    }

    public InputStream sendRequest() throws Exception{
        return url.openStream();
    }
    public String getSource() throws Exception{
        String source = new BufferedReader(new InputStreamReader(
                this.sendRequest()))
                .lines().collect(Collectors.joining("\n"));
        return source;
    }

}
