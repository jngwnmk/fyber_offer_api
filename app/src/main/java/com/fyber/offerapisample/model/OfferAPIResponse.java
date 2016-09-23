package com.fyber.offerapisample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wonmook on 2016. 9. 22..
 * A model class for Offer API response
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OfferAPIResponse {

    private HttpStatus httpCode;

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("count")
    private int count;

    @JsonProperty("pages")
    private int pages;

    @JsonProperty("information")
    private Information information;

    @JsonProperty("offers")
    private List<Offer> offers;


    public OfferAPIResponse(){
        this.httpCode = null;
        this.code = "";
        this.message = "";
        this.count = 0;
        this.pages = 0;
        this.information = new Information();
        this.offers= new ArrayList<>();
    }

    public OfferAPIResponse(HttpStatus httpCode ,String code, String message, int count, int pages, Information information, ArrayList<Offer> offers) {
        this.httpCode = httpCode;
        this.code = code;
        this.message = message;
        this.count = count;
        this.pages = pages;
        this.information = information;
        this.offers = offers;
    }

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    private static class Information{

        @JsonProperty("app_name")
        private String appName;

        @JsonProperty("appid")
        private int appId;

        @JsonProperty("virtual_currency")
        private String virtualCurrency;

        @JsonProperty("virtual_currency_sale_enabled")
        private boolean virtualCurrencySaleEnabled;

        @JsonProperty("country")
        private String country;

        @JsonProperty("language")
        private String language;

        @JsonProperty("support_url")
        private String supportUrl;

        public Information(){
            this.appName = "";
            this.appId = 0;
            this.virtualCurrency = "";
            this.virtualCurrencySaleEnabled = false;
            this.country = "";
            this.language = "";
            this.supportUrl = "";
        }

        public Information(String appName, int appId, String virtualCurrency, boolean virtualCurrencySaleEnabled, String country, String language, String supportUrl) {
            this.appName = appName;
            this.appId = appId;
            this.virtualCurrency = virtualCurrency;
            this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
            this.country = country;
            this.language = language;
            this.supportUrl = supportUrl;
        }

        @Override
        public String toString() {
            return "{" +
                    "appName:'" + appName + '\'' +
                    ", appId:" + appId +
                    ", virtualCurrency:'" + virtualCurrency + '\'' +
                    ", virtualCurrencySaleEnabled:" + virtualCurrencySaleEnabled +
                    ", country:'" + country + '\'' +
                    ", language:'" + language + '\'' +
                    ", supportUrl:'" + supportUrl + '\'' +
                    '}';
        }

        public String getAppName() {
            return appName;
        }

        public void setAppName(String appName) {
            this.appName = appName;
        }

        public int getAppId() {
            return appId;
        }

        public void setAppId(int appId) {
            this.appId = appId;
        }

        public boolean isVirtualCurrencySaleEnabled() {
            return virtualCurrencySaleEnabled;
        }

        public void setVirtualCurrencySaleEnabled(boolean virtualCurrencySaleEnabled) {
            this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
        }

        public String getVirtualCurrency() {
            return virtualCurrency;
        }

        public void setVirtualCurrency(String virtualCurrency) {
            this.virtualCurrency = virtualCurrency;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getSupportUrl() {
            return supportUrl;
        }

        public void setSupportUrl(String supportUrl) {
            this.supportUrl = supportUrl;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "code:'" + code + '\'' +
                ", message:'" + message + '\'' +
                ", count:" + count +
                ", pages:" + pages +
                ", information:" + information +
                ", offers:" + offers +
                '}';
    }

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }
}
