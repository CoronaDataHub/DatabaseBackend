/*
 * *
 *    ____                                          ____            _             _   _           _
 *   / ___|   ___    _ __    ___    _ __     __ _  |  _ \    __ _  | |_    __ _  | | | |  _   _  | |__
 *  | |      / _ \  | '__|  / _ \  | '_ \   / _` | | | | |  / _` | | __|  / _` | | |_| | | | | | | '_ \
 *  | |___  | (_) | | |    | (_) | | | | | | (_| | | |_| | | (_| | | |_  | (_| | |  _  | | |_| | | |_) |
 *   \____|  \___/  |_|     \___/  |_| |_|  \__,_| |____/   \__,_|  \__|  \__,_| |_| |_|  \__,_| |_.__/
 *
 *  	CoronaDataHub ist ein Projekt welches im Rahmen von der Initiative #WirVSVirus-Hackathon vom 20-22 März 2020 ins Leben gerufen wurde.
 *
 *
 */

package de.coronadatahub.databasebackend.services;

//https://coronavirus.app
//API Doku: https://www.notion.so/Covid-19-Coronavirus-API-d1ce9d47e64c473bbc9a034661477e84

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class CoronavriusappRestAPI {

    private String url = "https://coronavirus.app/";
    private String apiKey;
    private HttpClient client;

    public CoronavriusappRestAPI(String apiKey) {
        this.apiKey = apiKey;
        client = HttpClients.custom().build();
    }

    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String getHistory(String placeID) {
        String uri = url + "get-history?id={" + placeID + "}";
        HttpUriRequest request = getRequest(uri);
        try {
            return readAll(new BufferedReader(new InputStreamReader(client.execute(request).getEntity().getContent())));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPlaces() {
        String uri = url + "get-places";
        HttpUriRequest request = getRequest(uri);
        try {
            return readAll(new BufferedReader(new InputStreamReader(client.execute(request).getEntity().getContent())));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private HttpUriRequest getRequest(String uri){
        return RequestBuilder.get().setUri(uri).setHeader(HttpHeaders.CONTENT_TYPE, "application/json").setHeader("authorization", "Bearer " + apiKey + "").build();
    }


}
