package eclipse.demo.api;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class SoupKitchen {

    @PostMapping("/soupKitchen")
    public void findKitchen() throws IOException {
        StringBuilder sb = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_free_mlsv_api");
        sb.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=yFZtYw82i34Si9efOl96ZDk69kYsYnwOk9K%2FOu9OmgzPcEGt6y9DH7tqm%2Bki0TUpNMVyVR7%2FDlORn5gZh6%2Bp2Q%3D%3D");
        sb.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");
        sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=100");
        sb.append("&" + URLEncoder.encode("type", "UTF-8") + "=json");

        URL url = new URL(sb.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300){
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }else{
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while((line = rd.readLine()) != null){
            stringBuilder.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(stringBuilder.toString());
    }


}
