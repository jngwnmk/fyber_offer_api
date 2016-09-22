package com.fyber.offerapisample.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

/**
 * Created by wonmook on 2016. 9. 22..
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Offer{
    private String title;
    private long offer_id;
    private String teaser;
    private String required_actions;
    private String link;
    private ArrayList<OfferType> offer_types;
    private Thumbnail thumbnail;
    private int payout;
    private TimeToPayout time_to_payout;
    private String store_id;

    public Offer(){
        this.title = "";
        this.offer_id = 0L;
        this.teaser = "";
        this.required_actions = "";
        this.link = "";
        this.offer_types = new ArrayList<>();
        this.thumbnail = new Thumbnail();
        this.payout = 0;
        this.time_to_payout = new TimeToPayout();
        this.store_id  = "";
    }

    public Offer(String title, long offer_id, String teaser, String required_actions, String link, ArrayList<OfferType> offer_types, Thumbnail thumbnail, int payout, TimeToPayout time_to_payout, String store_id) {

        this.title = title;
        this.offer_id = offer_id;
        this.teaser = teaser;
        this.required_actions = required_actions;
        this.link = link;
        this.offer_types = offer_types;
        this.thumbnail = thumbnail;
        this.payout = payout;
        this.time_to_payout = time_to_payout;
        this.store_id = store_id;
    }

    private static class OfferType{
        private int offer_type_id;
        private String readable;

        public OfferType(){
            this.offer_type_id = 0;
            this.readable = "";
        }

        public OfferType(int offer_type_id, String readable) {
            this.offer_type_id = offer_type_id;
            this.readable = readable;
        }

        @Override
        public String toString() {
            return "{" +
                    "offer_type_id:" + offer_type_id +
                    ", readable:'" + readable + '\'' +
                    '}';
        }

        public int getOffer_type_id() {
            return offer_type_id;
        }

        public void setOffer_type_id(int offer_type_id) {
            this.offer_type_id = offer_type_id;
        }

        public String getReadable() {
            return readable;
        }

        public void setReadable(String readable) {
            this.readable = readable;
        }


    }

    public static class Thumbnail{
        private String lowres;
        private String hires;

        public Thumbnail(){
            this.lowres = "";
            this.hires = "";
        }

        public Thumbnail(String lowres, String hires) {
            this.lowres = lowres;
            this.hires = hires;
        }

        @Override
        public String toString() {
            return "{" +
                    "lowres:'" + lowres + '\'' +
                    ", hires:'" + hires + '\'' +
                    '}';
        }

        public String getLowres() {
            return lowres;
        }

        public void setLowres(String lowres) {
            this.lowres = lowres;
        }

        public String getHires() {
            return hires;
        }

        public void setHires(String hires) {
            this.hires = hires;
        }
    }

    private static class TimeToPayout{
        private long amount;
        private String readable;


        public TimeToPayout(){
            this.amount = 0L;
            this.readable = "";
        }

        public TimeToPayout(long amount, String readable) {
            this.amount = amount;
            this.readable = readable;
        }

        @Override
        public String toString() {
            return "{" +
                    "amount:" + amount +
                    ", readable:'" + readable + '\'' +
                    '}';
        }

        public long getAmount() {
            return amount;
        }

        public void setAmount(long amount) {
            this.amount = amount;
        }

        public String getReadable() {
            return readable;
        }

        public void setReadable(String readable) {
            this.readable = readable;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "title:'" + title + '\'' +
                ", offer_id:" + offer_id +
                ", teaser:'" + teaser + '\'' +
                ", required_actions:'" + required_actions + '\'' +
                ", link:'" + link + '\'' +
                ", offer_types:" + offer_types +
                ", thumbnail:" + thumbnail +
                ", payout:" + payout +
                ", time_to_payout:" + time_to_payout +
                '}';
    }

    public ArrayList<OfferType> getOffer_types() {
        return offer_types;
    }

    public void setOffer_types(ArrayList<OfferType> offer_types) {
        this.offer_types = offer_types;
    }

    public TimeToPayout getTime_to_payout() {
        return time_to_payout;
    }

    public void setTime_to_payout(TimeToPayout time_to_payout) {
        this.time_to_payout = time_to_payout;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(long offer_id) {
        this.offer_id = offer_id;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequired_actions() {
        return required_actions;
    }

    public void setRequired_actions(String required_actions) {
        this.required_actions = required_actions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<OfferType> getOfferType() {
        return offer_types;
    }

    public void setOfferType(ArrayList<OfferType> offerType) {
        this.offer_types = offerType;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public TimeToPayout getTimeToPayout() {
        return time_to_payout;
    }

    public void setTimeToPayout(TimeToPayout time_to_payout) {
        this.time_to_payout = time_to_payout;
    }
}
