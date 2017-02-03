package com.wehelpyou.wehelpvolunteers;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class MapsFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    //private GoogleMap mMap;
    private SupportMapFragment smf;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //FirebaseApp.initializeApp(Context);
        FirebaseMessaging.getInstance();//.subscribeToTopic("news");
        String token = FirebaseInstanceId.getInstance().getToken();
        FragmentManager fm = getChildFragmentManager();
        smf = (SupportMapFragment)fm.findFragmentById(R.id.map);
        if(smf==null){
            smf = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map,smf).commit();

        }
        else{
            smf.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {



                    googleMap.addMarker(new MarkerOptions().position(new LatLng(29.702182, -98.83438)).title("Texas"));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(-34.56564, 151.34545)).title("Sydney"));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(29.56564, -82.34545)).title("Gainesville"));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(29.56564, -82.34545), 6.0f));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(29.8172, -82.1401)).title("Ocala"));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(25.7617, -80.1917)).title("Miami"));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(28.5383, -81.3792)).title("Orlando"));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(33.56564, -84.38845)).title("Atlanta"));
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(30.56564, -81.34545)).title("Jacksonville"));

                    googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                    googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                        @Override
                        public void onInfoWindowClick(Marker marker) {
                            Toast.makeText(getContext(), "Sent", Toast.LENGTH_SHORT).show();

                            sendNotification();
                        }
                    });

                    /*googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                             // if marker source is clicked
                            // display toast
                            return true;
                            }

                    });*/
                }
            });

        }
    }


    public static void sendNotification(){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://powerful-harbor-20743.herokuapp.com/sendnoti", new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        /*mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}
