package com.fyber.offerapisample.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyber.offerapisample.R;
import com.squareup.picasso.Picasso;

/**
 * Created by wonmook on 2016. 9. 24..
 * A Activity shows detail information of a offer.
 * For the purpose of demo, it only shows title, teaser, payout, and thumbnail.
 * But it could be improved to show more information about the offer.
 */
public class OfferDetailActivity extends AppCompatActivity {

    private ImageButton closeBtn;
    private ImageView   thumbnailIv;
    private TextView    titleTv;
    private TextView    teaserTv;
    private TextView    payoutTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_detail);

        String thumbnail = getIntent().getExtras().getString(getString(R.string.offer_api_response_offer_thumbnail));
        String title = getIntent().getExtras().getString(getString(R.string.offer_api_response_offer_title));
        String teaser = getIntent().getExtras().getString(getString(R.string.offer_api_response_offer_teaser));
        String payout = getIntent().getExtras().getString(getString(R.string.offer_api_response_offer_payout));

        init();
        showDetail(thumbnail, title, teaser, payout);
    }

    private void init(){
        closeBtn = (ImageButton)findViewById(R.id.close_btn);
        thumbnailIv = (ImageView)findViewById(R.id.detail_thumbnail);
        titleTv = (TextView)findViewById(R.id.detail_title);
        teaserTv = (TextView)findViewById(R.id.detail_teaser);
        payoutTv = (TextView)findViewById(R.id.detail_payout);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void showDetail(String thumbnail, String title, String teaser, String payout){
        titleTv.setText(title);
        teaserTv.setText(teaser);
        payoutTv.setText(getString(R.string.offer_response_item_payout)+payout);

        if(thumbnail!=null && !"".equals(thumbnail))
            Picasso.with(this).load(thumbnail)
                    .placeholder(R.drawable.ic_image_black_48dp)
                    .fit().centerCrop()
                    .into(thumbnailIv);
    }
}
