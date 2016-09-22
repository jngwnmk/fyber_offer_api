package com.fyber.offerapisample.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyber.offerapisample.model.OfferAPIRequest;
import com.fyber.offerapisample.model.OfferAPIResponse;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

/**
 * Created by wonmook on 2016. 9. 22..
 */
public class OfferRequestTask extends AsyncTask<Void, Void, OfferAPIResponse>{

    private OfferAPIRequest offerAPIRequest= null;
    private TaskCompleteListener taskCompleteListener = null;
    private static String OFFER_URL = "http://api.fyber.com/feed/v1/offers.json";
    private Context mContext;
    public OfferRequestTask(Context context, OfferAPIRequest offerAPIRequest, TaskCompleteListener taskCompleteListener){
        this.mContext = context;
        this.offerAPIRequest = offerAPIRequest;
        this.taskCompleteListener = taskCompleteListener;
    }

    @Override
    protected OfferAPIResponse doInBackground(Void... params) {

        try{

            try {
                offerAPIRequest.setGoogle_ad_id(AdvertisingIdClient.getAdvertisingIdInfo(mContext).getId());
                offerAPIRequest.setGoogle_ad_id_limited_tracking_enabled(AdvertisingIdClient.getAdvertisingIdInfo(mContext).isLimitAdTrackingEnabled());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            }

            OfferAPIBuilder offerAPIBuilder = OfferAPIBuilder.init(OFFER_URL, offerAPIRequest.getAPIKEY())
                    .setParam("appid", String.valueOf(offerAPIRequest.getAppid()))
                    .setParam("uid", offerAPIRequest.getUid())
                    .setParam("ip", offerAPIRequest.getIp())
                    .setParam("locale",offerAPIRequest.getLocale())
                    .setParam("google_ad_id",offerAPIRequest.getGoogle_ad_id())
                    .setParam("google_ad_id_limited_tracking_enabled", String.valueOf(offerAPIRequest.isGoogle_ad_id_limited_tracking_enabled()))
                    .setParam("page",String.valueOf(offerAPIRequest.getPage()))
                    .setParam("timestamp", String.valueOf(offerAPIRequest.getTimestamp()))
                    .setParam("offer_types",offerAPIRequest.getOffer_type())
                    .setParam("pub0",offerAPIRequest.getPub0())
                    .setParam("os_version",offerAPIRequest.getOs_version());



            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            URI offerAPIURI = offerAPIBuilder.buildURI();
            Log.d("OfferAPI",offerAPIURI.toString());

            ResponseEntity<String> response = restTemplate.getForEntity(offerAPIURI, String.class);
            if(checkSignature(response, offerAPIRequest.getAPIKEY())){
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.readValue(response.getBody(), OfferAPIResponse.class);
            } else {
                OfferAPIResponse offerAPINonValidResponse = new OfferAPIResponse();
                offerAPINonValidResponse.setCode("No Valid Response");
                offerAPINonValidResponse.setMessage("No Valid Response");
                return offerAPINonValidResponse;
            }

        }
        catch (HttpStatusCodeException e){
            OfferAPIResponse offerAPIBadResponse = new OfferAPIResponse();
            try {
                JSONObject errorResponse = new JSONObject(e.getResponseBodyAsString());
                offerAPIBadResponse.setCode(errorResponse.getString("code"));
                offerAPIBadResponse.setMessage(errorResponse.getString("message"));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return offerAPIBadResponse;
        } catch (Exception e){
            Log.e("OfferRequestTask", e.getMessage(), e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(OfferAPIResponse response){
        taskCompleteListener.onTaskCompleted(response);
    }

    private static final String HASH_SIGNATURE = "X-Sponsorpay-Response-Signature";

    private boolean checkSignature(ResponseEntity<String> response, String apikey) {
        String hash = response.getHeaders().get(HASH_SIGNATURE).get(0);
        Log.d("OfferAPI", response.getBody());
        String responseBodyAndAPIKey = response.getBody() + apikey;
        Log.d("OfferAPI", new String(Hex.encodeHex(DigestUtils.sha1(responseBodyAndAPIKey))));
        Log.d("OfferAPI", hash);
        return hash.equals(new String(Hex.encodeHex(DigestUtils.sha1(responseBodyAndAPIKey))));
    }

}
