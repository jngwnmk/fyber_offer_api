package com.fyber.offerapisample.model;

/**
 * Created by wonmook on 2016. 9. 22..
 * A Model class to be used in Offer Mobile API
 * Description)
 *   format: json
 *   appid: Application ID, provided as simple data
 *   uid: User ID, provided as simple data
 *   device_id: use Android advertising identifier
 *   locale: provided as simple data
 *   ip: provided as simple data
 *   offer_types: 112
 *
 * Example)
 *   appid: 2070
 *   uid: spiderman
 *   locale: ‘DE’
 *   ip: ‘109.235.143.113’
 *   API Key: 1c915e3b5d42d05136185030892fbb846c278927
 *
 * See detail on http://developer.fyber.com/content/current/android/offer-wall/offer-api/
 */
public class OfferAPIRequest {
    private String appid;
    private String uid;
    private String ip;
    private String locale;
    private String os_version;
    private long timestamp;
    private long ps_time;
    private String pub0;
    private int page;
    private String hashkey;
    private String device_id;
    private String google_ad_id;
    private boolean google_ad_id_limited_tracking_enabled;
    private String offer_type;
    private String device;

    private String APIKEY;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAPIKEY() {
        return APIKEY;
    }

    public void setAPIKEY(String APIKEY) {
        this.APIKEY = APIKEY;
    }

    public String getOs_version() {
        return os_version;
    }

    public void setOs_version(String os_version) {
        this.os_version = os_version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getPs_time() {
        return ps_time;
    }

    public void setPs_time(long ps_time) {
        this.ps_time = ps_time;
    }

    public String getPub0() {
        return pub0;
    }

    public void setPub0(String pub0) {
        this.pub0 = pub0;
    }


    public String getHashkey() {
        return hashkey;
    }

    public void setHashkey(String hashkey) {
        this.hashkey = hashkey;
    }

    public String getGoogle_ad_id() {
        return google_ad_id;
    }

    public void setGoogle_ad_id(String google_ad_id) {
        this.google_ad_id = google_ad_id;
    }

    public boolean isGoogle_ad_id_limited_tracking_enabled() {
        return google_ad_id_limited_tracking_enabled;
    }

    public void setGoogle_ad_id_limited_tracking_enabled(boolean google_ad_id_limited_tracking_enabled) {
        this.google_ad_id_limited_tracking_enabled = google_ad_id_limited_tracking_enabled;
    }

    public String getOffer_type() {
        return offer_type;
    }

    public void setOffer_type(String offer_type) {
        this.offer_type = offer_type;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }
}
