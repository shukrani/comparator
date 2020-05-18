package com.dkatails.comparator.imp;

import com.dkatails.comparator.DataComparator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;

/**
 * ApiResponseComparator: Executes the API using HTTP Connection and compares the result of API.
 *
 */
public class ApiResponseComparator implements DataComparator<String> {

    // object mapper is used to parse incoming json string to Map<String, Object>.
    private ObjectMapper objectMapper;

    public ApiResponseComparator() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public boolean compare(String object1, String object2) {
        if(object1.isEmpty() || object2.isEmpty())
            return false;

        try{
            return executeAPI(object1).equals(executeAPI(object2));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private Map<String, Object> executeAPI(String path) throws IOException {

        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.addRequestProperty("User-Agent", "Code");
        connection.setDoOutput(true);
        connection.setConnectTimeout(10_000);

        Scanner scanner= new Scanner(connection.getInputStream());
        StringBuilder builder = new StringBuilder();
        if(connection.getResponseCode() == 200){
            while (scanner.hasNext()){
                builder.append(scanner.next());
            }
        }

        return objectMapper.readValue(builder.toString(), Map.class);

    }

}
