package com.fyber.offerapisample.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wonmook on 2016. 9. 22..
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class OfferAPIResponse {

    private String code;
    private String message;
    private int count;
    private int pages;
    private Information information;
    private List<Offer> offers;

    public OfferAPIResponse(){
        this.code = "";
        this.message = "";
        this.count = 0;
        this.pages = 0;
        this.information = new Information();
        this.offers= new ArrayList<>();
    }

    public OfferAPIResponse(String code, String message, int count, int pages, Information information, ArrayList<Offer> offers) {
        this.code = code;
        this.message = message;
        this.count = count;
        this.pages = pages;
        this.information = information;
        this.offers = offers;
    }

    @JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
    private static class Information{
        private String app_name;
        private int appid;
        private String virtual_currency;
        private boolean virtual_currency_sale_enabled;
        private String country;
        private String language;
        private String support_url;

        public Information(){
            this.app_name = "";
            this.appid = 0;
            this.virtual_currency = "";
            this.virtual_currency_sale_enabled = false;
            this.country = "";
            this.language = "";
            this.support_url = "";
        }

        public Information(String app_name, int appid, String virtual_currency, boolean virtual_currency_sale_enabled, String country, String language, String support_url) {
            this.app_name = app_name;
            this.appid = appid;
            this.virtual_currency = virtual_currency;
            this.virtual_currency_sale_enabled = virtual_currency_sale_enabled;
            this.country = country;
            this.language = language;
            this.support_url = support_url;
        }

        @Override
        public String toString() {
            return "{" +
                    "app_name:'" + app_name + '\'' +
                    ", appid:" + appid +
                    ", virtual_currency:'" + virtual_currency + '\'' +
                    ", virtual_currency_sale_enabled:" + virtual_currency_sale_enabled +
                    ", country:'" + country + '\'' +
                    ", language:'" + language + '\'' +
                    ", support_url:'" + support_url + '\'' +
                    '}';
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

        public boolean isVirtual_currency_sale_enabled() {
            return virtual_currency_sale_enabled;
        }

        public void setVirtual_currency_sale_enabled(boolean virtual_currency_sale_enabled) {
            this.virtual_currency_sale_enabled = virtual_currency_sale_enabled;
        }

        public String getVirtual_currency() {
            return virtual_currency;
        }

        public void setVirtual_currency(String virtual_currency) {
            this.virtual_currency = virtual_currency;
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

        public String getSupport_url() {
            return support_url;
        }

        public void setSupport_url(String support_url) {
            this.support_url = support_url;
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
