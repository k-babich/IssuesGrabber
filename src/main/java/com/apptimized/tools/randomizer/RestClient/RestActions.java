package com.apptimized.tools.randomizer.RestClient;

import com.apptimized.tools.randomizer.Utils.ConstStorage;
import com.apptimized.tools.randomizer.Utils.PropertyHandler;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.apptimized.tools.randomizer.Randomizer.UIInterface.Actions.UIActions.showExceptionAlert;
import static com.apptimized.tools.randomizer.Utils.ActionUtils.prepareQuery;
import static com.apptimized.tools.randomizer.Utils.ConstStorage.*;

public class RestActions {

    private static String JIRA_URL = PropertyHandler.getProperty("JIRA_URL") + "/rest/api/2";
    private static String JIRA_USER = PropertyHandler.getProperty("JIRA_USER");
    private static String JIRA_PASS = PropertyHandler.getProperty("JIRA_PASS");
    private static String OK_STATUS = "204;201;200";
    private static String authHeader = "Basic " + new String(
            Base64.encodeBase64((JIRA_USER + ":" + JIRA_PASS).getBytes())
    );

    private static String putJsonRequest(String json, String uri) {
        try{
            HttpPut request = new HttpPut(uri);
            StringEntity params = new StringEntity(json,"UTF-8");
            params.setContentType("application/json");
            request.addHeader(HttpHeaders.AUTHORIZATION, authHeader);
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            request.setEntity(params);
            HttpClient client = HttpClientBuilder.create().build();
            return client.execute(request).toString();
        } catch (IOException e) {
            showExceptionAlert(e);
        }
        return null;
    }

    private static HttpResponse postJsonRequest(String json, String uri) {
        try{
            HttpPost request = new HttpPost(uri);
            StringEntity params = new StringEntity(json,"UTF-8");
            params.setContentType("application/json");
            request.addHeader(HttpHeaders.AUTHORIZATION, authHeader);
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            request.setEntity(params);
            HttpClient client = HttpClientBuilder.create().build();
            return client.execute(request);
        } catch (IOException e) {
            showExceptionAlert(e);
        }
        return null;
    }

    private static HttpResponse getJsonRequest(String uri) {
        try {
            HttpGet request = new HttpGet(uri);
            request.addHeader(HttpHeaders.AUTHORIZATION, authHeader);
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            HttpClient client = HttpClientBuilder.create().build();
            return client.execute(request);
        } catch (IOException e) {
            showExceptionAlert(e);
        }
        return null;
    }

    public static String getQueryResults(String query, int startAt) {
        return getContentFromResponse(getJsonRequest(JIRA_URL + "/search?jql=" + prepareQuery(query) +
                "&maxResults=1000&startAt=" + startAt));
    }

    public static String getQueryResults(String query) {
        return getQueryResults(query, 0);
    }

    public static String getUserInfo(String userName) {
        return getContentFromResponse(getJsonRequest(JIRA_URL + "/user/search?username=" + userName));
    }

    private static String getContentFromResponse(HttpResponse response) {
        try {
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        } catch (IOException e) {
            showExceptionAlert(e);
        }
        return null;
    }



}