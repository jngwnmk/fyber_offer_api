package com.fyber.offerapisample.controller;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by wonmook on 2016. 9. 22..
 *
 * Fyber Mobile Offer API builder class
 * Example)
 * http://api.fyber.com/feed/v1/offers.json?appid=[APP_ID]&uid=[USER_ID]&ip=[IP_ADDRESS]
 * &locale=[LOCALE]&device_id=[DEVICE_ID]&ps_time=[TIMESTAMP]&pub0=[CUSTOM]&timestamp=[UNIX_TIMESTAMP]
 * &offer_types=[OFFER_TYPES]&google_ad_id=[GAID]&google_ad_id_limited_tracking_enabled=[GAID ENABLED]
 * &hashkey=[HASHKEY]
 */
public class OfferAPIBuilder {

    private final static String EQUAL = "=";
    private final static String AND   = "&";
    private final static String HASHKEY = "hashkey";

    private final TreeMap<String, String> params = new TreeMap<>();
    private String URL;
    private String APIKEY;
    private static OfferAPIBuilder offerAPIBuilder;

    public OfferAPIBuilder(String URL, String APIKEY){
        this.URL = URL;
        this.APIKEY = APIKEY;
    }

    public static OfferAPIBuilder init(String URL, String APIKEY) {
        offerAPIBuilder = new OfferAPIBuilder(URL, APIKEY);
        return offerAPIBuilder;
    }

    public OfferAPIBuilder setParam(String key, String value) {
        params.put(key, value);
        return offerAPIBuilder;
    }

    /**
     * Calculate hashkey with given parameters and compose of full URI with hashkey
     * @return composed URI with calculated hashkey
     */
    public URI buildURI() {

        //1. Concatenate all request parameters
        //NOTE) Since params variable, which is TreeMap, is already sorted by key alphabetically,
        //      just concatenate parameters
        StringBuffer paramStrBuilder = new StringBuffer();
        SortedSet<String> keys = new TreeSet<String>(params.keySet());
        for (String key : keys) {
            String value =  params.get(key);
            paramStrBuilder.append(key);
            paramStrBuilder.append(EQUAL);
            paramStrBuilder.append(value);
            paramStrBuilder.append(AND);
        }

        //2. Concatenate the resulting string with your API Key
        paramStrBuilder.append(APIKEY);

        //3. Hash the resulting string using SHA1
        String hashKey = new String(Hex.encodeHex(DigestUtils.sha1(paramStrBuilder.toString())));

        //4. Compose complete URI with parameter and hashkey
        UriComponentsBuilder Uribuilder = UriComponentsBuilder.fromHttpUrl(URL);

        keys = new TreeSet<>(params.keySet());
        for (String key : keys) {
            String value = params.get(key);
            Uribuilder.queryParam(key, value);
        }
        Uribuilder.queryParam(HASHKEY, hashKey);

        return Uribuilder.build().encode().toUri();

    }

}
