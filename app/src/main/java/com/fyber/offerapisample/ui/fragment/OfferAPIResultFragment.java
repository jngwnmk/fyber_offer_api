package com.fyber.offerapisample.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.fyber.offerapisample.ui.adapter.OfferAPIResultAdapter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by wonmook on 2016. 9. 22..
 */
public class OfferAPIResultFragment extends Fragment{

    private ListView offerResultLv;
    private OfferAPIResultAdapter offerAPIResultAdapter;
    private ArrayList<Offer> offers = new ArrayList<>();
    private TextView offerResponseCode;
    private TextView offerResponseMsg;
    private ImageButton offerBeforeBtn;
    private ImageButton offerNextBtn;
    private ProgressDialog pDialog;
    private int currentPage;
    private int totalPage;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_offer_api_result,container, false);
        offerResultLv = (ListView)rootView.findViewById(R.id.offer_result_list);
        offerAPIResultAdapter = new OfferAPIResultAdapter(inflater.getContext(),offers);
        offerResultLv.setAdapter(offerAPIResultAdapter);

        offerResponseCode = (TextView)rootView.findViewById(R.id.offer_response_code);
        offerResponseMsg = (TextView)rootView.findViewById(R.id.offer_response_msg);
        offerBeforeBtn = (ImageButton)rootView.findViewById(R.id.offer_before_btn);
        offerNextBtn = (ImageButton)rootView.findViewById(R.id.offer_next_btn);

        offerBeforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = ((EditText)getActivity().findViewById(R.id.offer_api_uid)).getText().toString();
                String apikey = ((EditText)getActivity().findViewById(R.id.offer_api_apikey)).getText().toString();
                String appid = ((EditText)getActivity().findViewById(R.id.offer_api_appid)).getText().toString();
                String pub0 = ((EditText)getActivity().findViewById(R.id.offer_api_pup0)).getText().toString();
                int page = currentPage-1;
                showOfferAPIResult(uid,apikey,appid,pub0,page);
            }
        });

        offerNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = ((EditText)getActivity().findViewById(R.id.offer_api_uid)).getText().toString();
                String apikey = ((EditText)getActivity().findViewById(R.id.offer_api_apikey)).getText().toString();
                String appid = ((EditText)getActivity().findViewById(R.id.offer_api_appid)).getText().toString();
                String pub0 = ((EditText)getActivity().findViewById(R.id.offer_api_pup0)).getText().toString();

                int page = currentPage+1;
                showOfferAPIResult(uid,apikey,appid,pub0,page);
            }
        });

        offerBeforeBtn.setEnabled(false);
        offerNextBtn.setEnabled(false);

        pDialog = new ProgressDialog(this.getActivity());
        pDialog.setMessage("Loading....");
        return rootView;
    }

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
                    Log.d("OfferAPI", object.toString());
                    final OfferAPIResponse offerAPIResponse = (OfferAPIResponse) object;
                    offerResponseCode.setText(getResources().getString(R.string.offer_response_code) +offerAPIResponse.getCode());
                    offerResponseMsg.setText(getResources().getString(R.string.offer_response_message) + offerAPIResponse.getMessage());
                    offers.addAll(offerAPIResponse.getOffers());
                    getActivity().runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            offerAPIResultAdapter.notifyDataSetChanged();
                            pDialog.dismiss();
                            updatePagingBtnStatus(offerAPIResponse.getPages());
                        }
                    });
                }
            }
        });
        pDialog.show();
        offerRequestTask.execute();
    }

    private void updatePagingBtnStatus(int totalPage){
        if(currentPage==1){
            offerBeforeBtn.setEnabled(false);
            offerNextBtn.setEnabled(true);
        } else if(totalPage==currentPage){
            offerBeforeBtn.setEnabled(true);
            offerNextBtn.setEnabled(false);
        } else {
            offerBeforeBtn.setEnabled(true);
            offerNextBtn.setEnabled(true);
        }
    }
}
