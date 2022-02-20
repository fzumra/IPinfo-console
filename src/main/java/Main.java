import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    private static final String BASE_URL = "https://api.ipify.org/?format=json";

    private static final String getIPInfoUrl(String ip) {
        return "https://ipinfo.io/" + ip + "/geo";
    }

    public static void main(String[] args) {
        String result = retrievePublicIP();
        if (result != null) {
            Gson gson = new Gson();
            IPObject ipObject = gson.fromJson(result, IPObject.class);
            if (ipObject != null && ipObject.ip != null && !ipObject.ip.isEmpty()) {
                var geoLocationJsonString = retrieveGeoLocationInfo(ipObject.ip);
                IPInfo ipInfo = gson.fromJson(geoLocationJsonString, IPInfo.class);
                System.out.println("The public IP address and geo-location information are:");
                System.out.println(ipInfo);
            }
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

    private static String retrieveGeoLocationInfo(String ip) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(getIPInfoUrl(ip));
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
