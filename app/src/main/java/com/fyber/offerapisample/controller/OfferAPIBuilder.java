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
 */
public class OfferAPIBuilder {


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

    public URI buildURI() {

        StringBuffer paramStrBuilder = new StringBuffer();
        SortedSet<String> keys = new TreeSet<String>(params.keySet());
        for (String key : keys) {
            String value =  params.get(key);
            paramStrBuilder.append(key);
            paramStrBuilder.append("=");
            paramStrBuilder.append(value);
            paramStrBuilder.append("&");
        }
        paramStrBuilder.append(APIKEY);
        String hashKey = new String(Hex.encodeHex(DigestUtils.sha1(paramStrBuilder.toString())));
        UriComponentsBuilder Uribuilder = UriComponentsBuilder.fromHttpUrl(URL);

        keys = new TreeSet<>(params.keySet());
        for (String key : keys) {
            String value = params.get(key);
            Uribuilder.queryParam(key, value);
        }
        Uribuilder.queryParam("hashkey", hashKey);

        return Uribuilder.build().encode().toUri();

    }

}
