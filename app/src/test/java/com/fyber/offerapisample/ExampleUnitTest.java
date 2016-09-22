package com.fyber.offerapisample;

import com.fyber.offerapisample.controller.OfferAPIBuilder;
import com.fyber.offerapisample.model.OfferAPIRequest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void OfferAPIBuilderTest(){
        String OFFER_URL = "http://api.fyber.com/feed/v1/offers.json";
        String API_KEY = "e95a21621a1865bcbae3bee89c4d4f84";
        OfferAPIRequest offerAPIRequest = new OfferAPIRequest();
        offerAPIRequest.setAppid(157);
        offerAPIRequest.setUid("player1");
        offerAPIRequest.setIp("212.45.111.17");
        offerAPIRequest.setLocale("de");
        offerAPIRequest.setDevice_id("2b6f0cc904d137be2e1730235f5664094b831186");
        offerAPIRequest.setPs_time(1312211903);
        offerAPIRequest.setPub0("campaign2");
        offerAPIRequest.setPage(2);
        offerAPIRequest.setTimestamp(1312553361);

        OfferAPIBuilder offerAPIBuilder = OfferAPIBuilder.init(OFFER_URL, API_KEY)
                .setParam("appid", String.valueOf(offerAPIRequest.getAppid()))
                .setParam("uid",offerAPIRequest.getUid())
                .setParam("ip", offerAPIRequest.getIp())
                .setParam("locale",offerAPIRequest.getLocale())
                .setParam("device_id",offerAPIRequest.getDevice_id())
                .setParam("ps_time",String.valueOf(offerAPIRequest.getPs_time()))
                .setParam("pub0",offerAPIRequest.getPub0())
                .setParam("page",String.valueOf(offerAPIRequest.getPage()))
                .setParam("timestamp",String.valueOf(offerAPIRequest.getTimestamp()));

        assertTrue(offerAPIBuilder.buildURI().toString().contains("hashkey=7a2b1604c03d46eec1ecd4a686787b75dd693c4d"));

    }
}