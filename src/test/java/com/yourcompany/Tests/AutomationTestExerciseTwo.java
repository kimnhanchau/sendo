package com.yourcompany.Tests;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AutomationTestExerciseTwo {
    @Test
    public void verifyShareFunction() throws UnirestException {
        String getQueryApi = "http://api.sendo.vn/sendo-stream/buying/livestreams/popular?p=1&s=";
        JsonNode getResponse = Unirest.get(getQueryApi)
                .header("ApiVersion", "4").asJson().getBody();
        String idString = (String) getResponse.getObject().getJSONArray("data")
                .getJSONObject(0).get("id");
        int totalShare = (Integer) getResponse.getObject().getJSONArray("data")
                .getJSONObject(0).get("total_shares");
        String postQueryApi = "https://api.sendo.vn/sendo-stream/buying/livestreams/sharing/".concat(idString);
        JsonNode postResponse = Unirest.post(postQueryApi).asJson().getBody();
        getResponse = Unirest.get(getQueryApi)
                .header("ApiVersion", "4").asJson().getBody();
        idString = (String) getResponse.getObject().getJSONArray("data")
                .getJSONObject(0).get("id");
        int newTotalShare = (Integer) getResponse.getObject().getJSONArray("data")
                .getJSONObject(0).get("total_shares");
        Assert.assertEquals(newTotalShare, totalShare +1);
    }
}
