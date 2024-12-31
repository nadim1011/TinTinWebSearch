import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class TinTinWebSearch {
    private static final String API_KEY = "api";
    private static final String CX = "cx";

    public static void main(String[] args) {
        try {
            Scanner scn=new Scanner(System.in);
            System.out.println("Search in terminal!! It's funny...");
            System.out.print("Search : ");
            String query =scn.nextLine(); // Your search query
            String urlString = "https://www.googleapis.com/customsearch/v1?q="
                    + query.replace(" ", "+")
                    + "&key=" + API_KEY
                    + "&cx=" + CX;

            // Make HTTP request
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray items = jsonResponse.getJSONArray("items");

            // Display search results
            System.out.println("Search Results:");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                System.out.println((i + 1) + ". " + item.getString("title"));
                System.out.println(item.getString("link"));
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
