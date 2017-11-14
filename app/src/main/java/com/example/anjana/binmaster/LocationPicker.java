package com.example.anjana.binmaster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import static com.example.anjana.binmaster.R.id.textLocationView;

public class LocationPicker  extends AppCompatActivity {

    private TextView get_place;
    int PLACE_PICKER_REQUEST=1;
    Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_picker);

        mapButton=(Button)findViewById(R.id.btnMap);
        get_place=(TextView)findViewById(R.id.textLocationView);


        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder =new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent=builder.build(getApplicationContext());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);


                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected  void onActivityResult(int requstCode,int resultCode,Intent data){
        if(requstCode==PLACE_PICKER_REQUEST)
        {
            if (resultCode==RESULT_OK){
                Place place=PlacePicker.getPlace(data,this);
                String address=String.format("Place:%s\n%s",place.getAddress(),place.getLatLng());
                get_place.setText(address);

            }

        }
    }

    public  void goNext(View v){
        Intent i=new Intent(LocationPicker.this,MobileNumberValidation.class);
        startActivity(i);
    }

}

