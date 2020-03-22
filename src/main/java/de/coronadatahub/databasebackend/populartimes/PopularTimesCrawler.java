/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2019., Johannes Hiry
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.coronadatahub.databasebackend.populartimes;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import de.coronadatahub.databasebackend.populartimes.utils.GooglePlace;
import de.coronadatahub.databasebackend.populartimes.utils.LatLong;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class PopularTimesCrawler {

    private int radius;         // search distance in meters
    private boolean allPlaces;      // include/exclude places without popular times
    private String apiKey;
    private String type;           // type of the place that is used as filter
    private String keyword;
    private boolean postFilter;
    //if true and keyword is provided, the results will be filtered to ensure that name contains keyword

    private ArrayList<LatLong> qRadar;  //list containing lat/long values to search for objects

    private ArrayList<GooglePlace> placesList; //list containing found googlePlaces objects


    //google stuff
    String radarUrl;
    String detailUrl = "https://maps.googleapis.com/maps/api/place/details/json?placeid=%s&key=%s";

    String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_1 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12B435 mobile/iPhone OS/iPhone/iPhone6,1/8.1.1/KBS kong/1.0.8";

    public PopularTimesCrawler(String apiKey) {

        this.allPlaces = false;
        this.postFilter = false;

        this.qRadar = new ArrayList<>();
        this.placesList = new ArrayList<>();

        this.apiKey = apiKey;

        radarUrl = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=" + apiKey + "&input=%s&inputtype=textquery";
        detailUrl = "https://maps.googleapis.com/maps/api/place/details/json?key=" + apiKey + "&place_id=%s";
    }

    /**
     * get place information and popular times for a provided frame of min/max Coordinates via radius search
     *
     * @return
     */
    public ArrayList<GooglePlace> getDataOfFrame(LatLong minCoord, LatLong maxCoord, int radius) {

        ArrayList<GooglePlace> result;
        this.radius = radius;

        System.out.println("Starting radial search...");
        qRadar = getCircleCenters(minCoord, maxCoord, radius);
        ArrayList<String> ids = getIDs(qRadar);

        Set<String> uniqeIDs = new HashSet<String>(ids); //ensure unique values
        ids.clear();
        ids.addAll(uniqeIDs);

        System.out.println(ids.size() + " places to process...");

        result = getDetail(ids);

        return result;
    }

    /**
     * get place information and popular times by uniqe google id
     *
     * @param id
     * @return
     */
    public GooglePlace getDataFromID(String id) {

        ArrayList<String> ids = new ArrayList<>();
        ids.add(id);
        GooglePlace result = getDetail(ids).get(0);

        return result;
    }

    /**
     * get place information and popular times by locationName
     *
     * @param locName: location name
     * @param radius:  search radius
     * @return
     */
    public ArrayList<GooglePlace> getDataFromLocName(String locName, int radius) {
        ArrayList<GooglePlace> result = new ArrayList<>();

        this.radius = radius;

        //search for lat/long via google search
        LatLong point = getGeoLocByName(locName);
        ArrayList<LatLong> locList = new ArrayList<>();
        locList.add(point);

        //get the ids from api
        ArrayList<String> ids = getIDs(locList);

        //get the details
        result = getDetail(ids);

        return result;
    }

    /**
     * uses google/search to determine the lat/long values for api search
     * Example: https://www.google.de/search?tbm=map&hl=de&tch=1&q=Wuppertal-Elberfeld
     *
     * @param locName
     * @return
     */
    private LatLong getGeoLocByName(String locName) {

        JsonArray data = getSearchData(locName, "");

        JsonArray info = (JsonArray) ((JsonArray) data.get(1)).get(0);

        LatLong res = new LatLong(info.get(2).getAsDouble(), info.get(1).getAsDouble());

        return res;

    }

    /**
     * cover the search area with circles for radar search
     * http://stackoverflow.com/questions/7477003/calculating-new-longtitude-latitude-from-old-n-meters
     * radius parameter is taken from constructor
     *
     * @return ArrayList<String> with all ids of the discovered places
     */
    private ArrayList<LatLong> getCircleCenters(LatLong minCoord, LatLong maxCoord, int radius) {
        ArrayList<LatLong> res = new ArrayList<>();

        int r = 6378; //earth radius in km

        double minLat = minCoord.getLat();
        double minLong = minCoord.getLong();
        double maxLat = maxCoord.getLat();
        double maxLong = maxCoord.getLong();

        while (minLong < maxLong) {

            double tmp = minLat;

            while (tmp < maxLat) {
                res.add(new LatLong(tmp, minLong));
                tmp += (0.25 / r) * (radius / Math.PI);
            }

            minLong += (0.25 / r) * (radius / Math.PI) / Math.cos(minLat * Math.PI / radius);
        }
        return res;
    }

    /**
     * get list with unique google ids from places
     * query is executed via google API
     * places - radar search - https://developers.google.com/places/web-service/search?hl=de#RadarSearchRequests
     *
     * @param latLong
     * @return
     */

    private ArrayList<String> getIDs(ArrayList<LatLong> latLong) {
        ArrayList<String> ids = new ArrayList<>();

        for (int i = 0; i < latLong.size(); i++) {
            LatLong tmp = latLong.get(i);

            try {
                String radarString;
                radarString = String.format(radarUrl, URLEncoder.encode("" + tmp.getLat(), StandardCharsets.UTF_8),
                        URLEncoder.encode("" + tmp.getLong(), StandardCharsets.UTF_8),
                        URLEncoder.encode("" + radius, StandardCharsets.UTF_8), URLEncoder.encode(type, StandardCharsets.UTF_8),
                        URLEncoder.encode("" + keyword, StandardCharsets.UTF_8), URLEncoder.encode("" + apiKey, StandardCharsets.UTF_8));
                System.out.println(radarString);


                //                System.out.println(radarString);
                //do the Json stuff
                InputStream inputStream = null;
                String Json = "";

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(radarString);
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8), 8);
                StringBuilder sbuild = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sbuild.append(line);
                }
                inputStream.close();
                System.out.println(sbuild.toString());
                /*Json = sbuild.toString();

                //now parse
                JsonParser parser = new JsonParser();*/
                JsonReader jsonReader = new JsonReader(reader);
                jsonReader.setLenient(true);
                // Object obj = parser.parse(Json);

                Gson gson = new GsonBuilder().create();
                JsonPrimitive jbs = gson.fromJson(jsonReader, JsonPrimitive.class);


                JsonObject jb = jbs.getAsJsonObject();
                //JsonObject jb = (JsonObject) obj;


                //now read
                JsonArray JsonObject1 = (JsonArray) jb.get("results");

                //                System.out.println(JsonObject1.size());

                for (int j = 0; j < JsonObject1.size(); j++) {
                    JsonObject o = (JsonObject) JsonObject1.get(j);
                    ids.add(o.get("place_id").getAsString());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return ids;
    }

    /**
     * requests detailed information from the google API based on the provided ID
     * places api - detail search - https://developers.google.com/places/web-service/details?hl=de
     *
     * @param ids: ArrayList<String> with all places ids that have to be searched for details
     * @return: ArrayList<GooglePlace> with detailed information about popular times and more
     */
    private ArrayList<GooglePlace> getDetail(ArrayList<String> ids) {

        for (int i = 0; i < ids.size(); i++) {

            String id = ids.get(i);

            try {
                String detailString = String.format(detailUrl, URLEncoder.encode("" + id, "UTF-8"),
                        URLEncoder.encode("" + apiKey, "UTF-8"));

                //                System.out.println(detailString);
                InputStream inputStream = null;
                String Json = "";
                double rating = -1.0;
                int reviews = -1;

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(detailString);
                HttpResponse response = client.execute(post);
                HttpEntity entity = response.getEntity();
                inputStream = entity.getContent();

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"), 8);
                StringBuilder sbuild = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sbuild.append(line);
                }
                inputStream.close();
                Json = sbuild.toString();

                //now parse
                JsonParser parser = new JsonParser();
                Object obj = parser.parse(Json);
                JsonObject jb = (JsonObject) obj;

                //now read
                JsonObject JsonObject1 = (JsonObject) jb.get("result");

                String name = JsonObject1.get("name").getAsString(); //location name

                if (postFilter &&
                        keyword != null) //if we have post filter enabled and keyword != null, filter the results
                    if (!(name.toLowerCase().contains(keyword.toLowerCase()))) {
                        System.out.println("Skipped " + name + " due to filter settings.");
                        continue;
                    }

                String formattedAddress = JsonObject1.get("formatted_address").getAsString(); //full location address
                //get the types and convert them
                JsonArray jtypes = (JsonArray) JsonObject1.get("types");
                String[] types = new String[jtypes.size()];
                for (int k = 0; k < jtypes.size(); k++) {
                    types[k] = jtypes.get(k).getAsString();
                }
                LatLong latLong = new LatLong(((JsonObject) ((JsonObject) (JsonObject1.get("geometry")))
                        .get("location")).get("lat").getAsDouble(),
                        ((JsonObject) ((JsonObject) (JsonObject1.get("geometry"))).get("location"))
                                .get("lng").getAsDouble());

                JsonArray data = getSearchData(JsonObject1.get("name").getAsString(),
                        JsonObject1.get("formatted_address").getAsString()); //get data from search request

                //size == 11 means no interesting information available
                //has to be corrected if google changes something
                if (((JsonArray) ((JsonArray) ((JsonArray) data.get(0)).get(1)).get(0)).size() > 11 &&
                        ((JsonArray) ((JsonArray) data.get(0)).get(1)).get(0) != null) {
                    //get information array
                    JsonArray info = (JsonArray) ((JsonArray) ((JsonArray) ((JsonArray) data.get(0)).get(1)).get(0))
                            .get(14);
                    if (info.get(4) != null) {
                        if (((JsonArray) info.get(4)).get(7) != null) {
                            rating = ((JsonArray) info.get(4)).get(7).getAsDouble();
                        }

                        if (((JsonArray) info.get(4)).get(8) != null) {
                            reviews = (int) ((JsonArray) info.get(4)).get(8).getAsLong();
                        }
                    }

                    System.out.println(name);
                    System.out.println(formattedAddress);
                    if (info.get(84) == null) {
                        System.out.println("No information on popular times available!");
                    } else {
                        JsonArray jpopularTimes = (JsonArray) ((JsonArray) info.get(84)).get(0); //get popular times

                        //map popularTimes
                        Map<Integer, Map<Long, Double>> popularTimes = mapPopularTimes(jpopularTimes);

                        //create new googlePlaces object and fill it with data
                        GooglePlace place = new GooglePlace(name, formattedAddress, id, popularTimes, rating, latLong,
                                types, reviews);

                        //add it to ArrayList<GooglePlace>
                        placesList.add(place);

                        //print one element
                        //                System.out.println(placesList.get(0).getName());
                        //                Map<Integer, Map<Long, Double>> pop = placesList.get(0).getPopularTimes();
                        //                for(int z = 0; z < pop.size(); z++) {
                        //                    Map<Long, Double> usages = pop.get(z);
                        //                    System.out.println("Day " + z);
                        //                    for(long u = 0; u < usages.size(); u++) {
                        //                        double usage = usages.get(u);
                        //                        for(int o= 0; o < (int)(usage/2); o++){
                        //                            System.out.print("=");
                        //                        }
                        //                        System.out.print(" " + usage);
                        //                        System.out.println();
                        //                    }
                        //                    System.out.println();
                        //                }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return placesList;
    }

    /**
     * Converts the extracted popularTimes Json data to a corresponding hashMap starting with a 0 as key for sunday and
     * 24 hours of usages
     *
     * @param jpopularTimes
     * @return
     */
    public Map<Integer, Map<Long, Double>> mapPopularTimes(JsonArray jpopularTimes) {

        Map<Integer, Map<Long, Double>> map = new HashMap<>();

        for (int i = 0; i < jpopularTimes.size(); i++) {
            Map<Long, Double> usages = new HashMap<>();

            //preinitialize usage map with zero values
            for (int k = 0; k < 24; k++) {
                usages.put((long) k, 0.0);
            }

            JsonArray day = (JsonArray) jpopularTimes.get(i);
            JsonArray hours = (JsonArray) day.get(1);
            if (hours == null) {
                System.out.println("Day " + i + " is closed or not enough data available.");
                map.put(i, usages);
            } else {
                for (int j = 0; j < hours.size(); j++) {

                    JsonArray hour = (JsonArray) hours.get(j);
                    usages.put(hour.get(0).getAsLong(), Double.valueOf(hour.get(1).getAsLong()));
                }

                map.put(i, usages);
            }
        }

        return map;
    }

    /**
     * Sends a request to google/search and parses Json response to get data
     *
     * @param name:             string with place name
     * @param formattedAddress: string with place address
     */
    private JsonArray getSearchData(String name, String formattedAddress) {

        JsonArray data = new JsonArray();
        try {
            String tbm = "map";
            String hl = "de";
            String tch = "1";
            String q = name + " " + formattedAddress;

            String appender = "tbm=" + tbm + "&hl=" + hl + "&tch=" + tch + "&q=" + URLEncoder.encode(q, "UTF-8");
            String searchUrl = "https://www.google.de/search?" + appender;

            //            System.out.println(searchUrl);

            URL url1 = new URL(searchUrl);
            URLConnection connection = url1.openConnection();
            connection.setRequestProperty("User-Agent", userAgent);
            connection.connect();

            InputStream response = connection.getInputStream();

            String Json = "";
            BufferedReader reader = new BufferedReader(new InputStreamReader(response, "utf-8"), 8);
            StringBuilder sbuild = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sbuild.append(line);
            }
            response.close();
            Json = sbuild.toString();

            int jEnd = Json.lastIndexOf("}");
            if (jEnd >= 0)
                Json = Json.substring(0, jEnd + 1);

            //now parse
            JsonParser parser = new JsonParser();
            Object obj = parser.parse(Json);
            JsonObject jb = (JsonObject) obj;

            //now read
            String jdata = jb.get("d").getAsString(); //read the data String
            jdata = jdata.substring(4, jdata.length()); //cut it to get the JsonArray again

            //reparse
            Object ob = parser.parse(jdata);
            data = (JsonArray) ob;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;

    }

    /**
     * Set optional keyword parameter
     *
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


    /**
     * if true and keyword is provided, the results will be filtered to ensure that name contains keyword
     *
     * @param postFilter
     */
    public void setPostFilter(boolean postFilter) {
        this.postFilter = postFilter;
    }
}

