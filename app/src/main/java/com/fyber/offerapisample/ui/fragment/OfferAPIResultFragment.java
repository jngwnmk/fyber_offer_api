package com.fyber.offerapisample.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.fyber.offerapisample.R;
import com.fyber.offerapisample.controller.OfferRequestTask;
import com.fyber.offerapisample.controller.TaskCompleteListener;
import com.fyber.offerapisample.model.Offer;
import com.fyber.offerapisample.model.OfferAPIRequest;
import com.fyber.offerapisample.model.OfferAPIResponse;
import com.fyber.offerapisample.ui.activity.OfferDetailActivity;
import com.fyber.offerapisample.ui.adapter.OfferAPIResultAdapter;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by wonmook on 2016. 9. 22..
 * A Fragment showing Offer API response as a form of list
 * Enable to navigate pages with buttons.
 */
public class OfferAPIResultFragment extends Fragment{

    private static String DEBUGTAG = OfferAPIResultFragment.class.getSimpleName();
    private static final int INVALID_PAGE = -1;
    private ListView offerResultLv;
    private OfferAPIResultAdapter offerAPIResultAdapter;
    private ArrayList<Offer> offers = new ArrayList<>();
    private TextView offerResponseCode;
    private TextView offerResponseMsg;
    private ImageButton offerBeforeBtn;
    private ImageButton offerNextBtn;
    private ProgressDialog pDialog;
    private int currentPage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_offer_api_result,container, false);
        offerResultLv = (ListView)rootView.findViewById(R.id.offer_result_list);
        offerAPIResultAdapter = new OfferAPIResultAdapter(inflater.getContext(),offers);
        offerResultLv.setAdapter(offerAPIResultAdapter);
        offerResultLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(offers!=null){
                    showOfferDetail(offers.get(i));
                }
            }
        });

        offerResponseCode = (TextView)rootView.findViewById(R.id.offer_response_code);
        offerResponseMsg = (TextView)rootView.findViewById(R.id.offer_response_msg);
        offerBeforeBtn = (ImageButton)rootView.findViewById(R.id.offer_before_btn);
        offerNextBtn = (ImageButton)rootView.findViewById(R.id.offer_next_btn);

        offerBeforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int page = currentPage - 1;
                movePage(page);
            }
        });

        offerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int page = currentPage + 1;
                movePage(page);
            }
        });

        offerBeforeBtn.setEnabled(false);
        offerNextBtn.setEnabled(false);

        pDialog = new ProgressDialog(this.getActivity());
        pDialog.setMessage("Loading....");
        return rootView;
    }

    //Request with params entered in UI form and default system values.
    public void showOfferAPIResult(String uid, String apikey, String appid, String pub0, int page){
        currentPage = page;
        offers.clear();
        getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                offerAPIResultAdapter.notifyDataSetChanged();
            }
        });

        final OfferAPIRequest offerAPIRequest = new OfferAPIRequest();
        offerAPIRequest.setAppid(appid);
        offerAPIRequest.setUid(uid);
        offerAPIRequest.setLocale(getString(R.string.test_locale));
        offerAPIRequest.setIp(getString(R.string.test_ip));
        offerAPIRequest.setOs_version(android.os.Build.VERSION.RELEASE);
        offerAPIRequest.setAPIKEY(apikey);
        offerAPIRequest.setPage(page);
        offerAPIRequest.setTimestamp(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()));
        offerAPIRequest.setOffer_type(getString(R.string.test_offer_type));
        offerAPIRequest.setPub0(pub0);

        OfferRequestTask offerRequestTask = new OfferRequestTask(getContext(), offerAPIRequest, new TaskCompleteListener() {
            @Override
            public void onTaskCompleted(Object object) {

                if (object!=null){
                    Log.d(DEBUGTAG, object.toString());
                    final OfferAPIResponse offerAPIResponse = (OfferAPIResponse) object;

                    //Show response result code and message
                    offerResponseCode.setText(getResources().getString(R.string.offer_response_code_txt) +offerAPIResponse.getCode());
                    offerResponseMsg.setText(getResources().getString(R.string.offer_response_message_txt) + offerAPIResponse.getMessage());

                    //Update offer list and notify to adapter to update listview
                    offers.addAll(offerAPIResponse.getOffers());
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            offerAPIResultAdapter.notifyDataSetChanged();
                            pDialog.dismiss();

                            //If the status of response is not OK, paging buttons are set to be disabled.
                            if(!offerAPIResponse.getHttpCode().equals(HttpStatus.OK)){
                                updatePagingBtnStatus(INVALID_PAGE);
                            } else {
                                updatePagingBtnStatus(offerAPIResponse.getPages());
                            }
                        }
                    });
                }
            }
        });
        pDialog.show();
        offerRequestTask.execute();
    }

    //Update Paging Button Status according to the current page and total page of offers
    private void updatePagingBtnStatus(int totalPage){

        if(totalPage==1 || totalPage==INVALID_PAGE){
            offerBeforeBtn.setEnabled(false);
            offerNextBtn.setEnabled(false);
        } else if(totalPage!=1 && currentPage==1){
            offerBeforeBtn.setEnabled(false);
            offerNextBtn.setEnabled(true);
        } else if(totalPage!=1 && totalPage==currentPage){
            offerBeforeBtn.setEnabled(true);
            offerNextBtn.setEnabled(false);
        } else {
            offerBeforeBtn.setEnabled(true);
            offerNextBtn.setEnabled(true);
        }

    }

    //Move to specific page
    private void movePage(int page){
        String uid = ((EditText) getActivity().findViewById(R.id.offer_api_uid)).getText().toString();
        String apikey = ((EditText) getActivity().findViewById(R.id.offer_api_apikey)).getText().toString();
        String appid = ((EditText) getActivity().findViewById(R.id.offer_api_appid)).getText().toString();
        String pub0 = ((EditText) getActivity().findViewById(R.id.offer_api_pup0)).getText().toString();
        showOfferAPIResult(uid, apikey, appid, pub0, page);
    }

    //Open Offer activity showing detail information
    private void showOfferDetail(Offer offer){
        Intent intent = new Intent(getContext(), OfferDetailActivity.class);
        Bundle extras = new Bundle();
        extras.putString(getString(R.string.offer_api_response_offer_title), offer.getTitle());
        extras.putString(getString(R.string.offer_api_response_offer_teaser), offer.getTeaser());
        extras.putString(getString(R.string.offer_api_response_offer_payout), String.valueOf(offer.getPayout()));
        extras.putString(getString(R.string.offer_api_response_offer_thumbnail), offer.getThumbnail().getHires());

        intent.putExtras(extras);
        startActivity(intent);
    }
}
