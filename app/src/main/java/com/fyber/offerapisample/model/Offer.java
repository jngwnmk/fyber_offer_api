package com.fyber.offerapisample.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.ArrayList;

/**
 * Created by wonmook on 2016. 9. 22..
 * A model class for Offer information
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Offer{
    @JsonProperty("title")
    private String title;

    @JsonProperty("offer_id")
    private long offerId;

    @JsonProperty("teaser")
    private String teaser;

    @JsonProperty("required_actions")
    private String requiredActions;

    @JsonProperty("link")
    private String link;

    @JsonProperty("offer_types")
    private ArrayList<OfferType> offerTypes;

    @JsonProperty("thumbnail")
    private Thumbnail thumbnail;

    @JsonProperty("payout")
    private int payout;

    @JsonProperty("time_to_payout")
    private TimeToPayout timeToPayout;

    @JsonProperty("store_id")
    private String storeId;

    public Offer(){
        this.title = "";
        this.offerId = 0L;
        this.teaser = "";
        this.requiredActions = "";
        this.link = "";
        this.offerTypes = new ArrayList<>();
        this.thumbnail = new Thumbnail();
        this.payout = 0;
        this.timeToPayout = new TimeToPayout();
        this.storeId  = "";
    }

    public Offer(String title, long offerId, String teaser, String requiredActions, String link, ArrayList<OfferType> offerTypes, Thumbnail thumbnail, int payout, TimeToPayout timeToPayout, String storeId) {

        this.title = title;
        this.offerId = offerId;
        this.teaser = teaser;
        this.requiredActions = requiredActions;
        this.link = link;
        this.offerTypes = offerTypes;
        this.thumbnail = thumbnail;
        this.payout = payout;
        this.timeToPayout = timeToPayout;
        this.storeId = storeId;
    }

    private static class OfferType{

        @JsonProperty("offer_type_id")
        private int offerTypeId;

        @JsonProperty("readable")
        private String readable;

        public OfferType(){
            this.offerTypeId = 0;
            this.readable = "";
        }

        public OfferType(int offerTypeId, String readable) {
            this.offerTypeId = offerTypeId;
            this.readable = readable;
        }

        @Override
        public String toString() {
            return "{" +
                    "offerTypeId:" + offerTypeId +
                    ", readable:'" + readable + '\'' +
                    '}';
        }

        public int getOfferTypeId() {
            return offerTypeId;
        }

        public void setOfferTypeId(int offerTypeId) {
            this.offerTypeId = offerTypeId;
        }

        public String getReadable() {
            return readable;
        }

        public void setReadable(String readable) {
            this.readable = readable;
        }


    }

    public static class Thumbnail{

        @JsonProperty("lowres")
        private String lowres;

        @JsonProperty("hires")
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

        @JsonProperty("amount")
        private long amount;

        @JsonProperty("readable")
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
                ", offerId:" + offerId +
                ", teaser:'" + teaser + '\'' +
                ", requiredActions:'" + requiredActions + '\'' +
                ", link:'" + link + '\'' +
                ", offerTypes:" + offerTypes +
                ", thumbnail:" + thumbnail +
                ", payout:" + payout +
                ", timeToPayout:" + timeToPayout +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ArrayList<OfferType> getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(ArrayList<OfferType> offerTypes) {
        this.offerTypes = offerTypes;
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
        return timeToPayout;
    }

    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
}
