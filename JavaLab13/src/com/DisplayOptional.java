package com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DisplayOptional implements LocaleCommand {
    String countryCode;

    public DisplayOptional(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String execute() {
        try {

            URL url = new URL("http://www.oorsprong.org/websamples.countryinfo/CountryInfoService.wso/CapitalCity/JSON/debug?sCountryISOCode=" + countryCode);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println("Capital of " + countryCode + ": " + output);
            }
            conn.disconnect();

        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return "";
    }
}
