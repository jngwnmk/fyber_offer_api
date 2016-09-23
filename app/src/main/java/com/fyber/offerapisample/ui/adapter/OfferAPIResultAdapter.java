package com.fyber.offerapisample.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyber.offerapisample.R;
import com.fyber.offerapisample.model.Offer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by wonmook on 2016. 9. 22..
 * ListView Adapter to show each offer on {@link com.fyber.offerapisample.ui.fragment.OfferAPIResultFragment}
 */
public class OfferAPIResultAdapter extends BaseAdapter{

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Offer> offers;


    public  OfferAPIResultAdapter(Context context, ArrayList<Offer> offers){
        this.context = context;
        this.offers = offers;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return offers.size();
    }

    @Override
    public Object getItem(int position) {
        return offers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OfferWrapper offerWrapper=null;
        if(row==null){
            row = inflater.inflate(R.layout.item_offer_api_result, null);
            offerWrapper = new OfferWrapper(row);
            row.setTag(offerWrapper);
        } else {
            offerWrapper = (OfferWrapper)row.getTag();

        }

        Offer offer = (Offer)getItem(position);
        offerWrapper.getTitleTv().setText(offer.getTitle());
        offerWrapper.getTeaserTv().setText(offer.getTeaser());
        offerWrapper.getPayoutTv().setText(context.getString(R.string.offer_response_item_payout)
                                            + offer.getPayout());

        if(offer.getThumbnail().getHires()!=null && !"".equals(offer.getThumbnail().getHires()))
            Picasso.with(context).load(offer.getThumbnail().getHires())
                    .placeholder(R.drawable.ic_image_black_48dp)
                    .fit().centerCrop()
                    .into(offerWrapper.getThumbnailEv());

        return row;
    }

    private class OfferWrapper{
        private View base;
        private TextView title;
        private TextView teaser;
        private TextView payout;
        private ImageView thumbnail;


        public OfferWrapper(View base){
            this.base = base;
        }

        public TextView getTitleTv(){
            if(title==null){
                title = (TextView)base.findViewById(R.id.offer_title);
            }
            return title;
        }

        public TextView getTeaserTv(){
            if(teaser==null){
                teaser = (TextView)base.findViewById(R.id.offer_teaser);
            }
            return teaser;
        }

        public TextView getPayoutTv(){
            if(payout==null){
                payout = (TextView)base.findViewById(R.id.offer_payout);
            }
            return payout;
        }


        public ImageView getThumbnailEv(){
            if(thumbnail==null){
                thumbnail = (ImageView)base.findViewById(R.id.offer_thumbnail);
            }
            return thumbnail;
        }

    }
}
