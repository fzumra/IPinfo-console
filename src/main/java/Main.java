import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    private static final String BASE_URL = "https://api.ipify.org/?format=json";

    public static void main(String[] args) {
        String result = retrievePublicIP();
        if (result != null) {
            System.out.println("Result: " + result);
        }
    }

    private static String retrievePublicIP() {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(BASE_URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder result = new StringBuilder();
            var inputStreamReader = new InputStreamReader(connection.getInputStream());
            var bufferedReader = new BufferedReader(inputStreamReader);
            for (String line; (line = bufferedReader.readLine()) != null; ) {
               result.append(line);
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
