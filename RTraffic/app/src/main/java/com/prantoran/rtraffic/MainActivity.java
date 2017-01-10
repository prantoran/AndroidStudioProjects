package com.prantoran.rtraffic;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log; //used for debugging off course
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//used for logging msgs to the console
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    public final static String GOTO_MAPS = "com.rtraffic.MESSAGE";
    public final static String GOTO_MAPS_SRC = "com.rtraffic.SRC";
    public final static String GOTO_MAPS_DES = "com.rtraffic.DES";

    public static final String MA_Tag = "ppp";
    private EditText srcNameVariable;
    private EditText desNameVariable;
    private Button ShowMapBUttonVariable;


    private Button button;
    private TextView textView;
    private LocationManager locationManager;
    private LocationListener locationListener;   //listens for location changes


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button = (Button) findViewById(R.id.gpsButton);
        textView = (TextView) findViewById(R.id.OutputText);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                textView.append("\n "+location.getLatitude()+" "+location.getLongitude());

            }
            //@Override
            public void onProviderDisabled(String s){
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }

        };
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ) {
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

                requestPermissions(new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.INTERNET
                }, 10);



                return;
            }
        }else{
            configureButton();
        }
        Log.i(MA_Tag,"onCreate"); //used for debugging
        addListerOnShowMapButton();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){


            case 10:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    configureButton();
                }
                return;
            default:
                Log.i(MA_Tag,"not ok");
                return;
        }
    }

    private void configureButton(){
        Log.i(MA_Tag,"ok 1");
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                textView.append("\nok 2");
                Log.i(MA_Tag,"ok 3");

                /*
                    Everything works so far (adding listeners, permissions )
                    crashes at requestLocationUpdates
                */
                locationManager.requestLocationUpdates("gps", 5000, 0, (android.location.LocationListener) locationListener);
                //locationManager.requestLocationUpdates(provider, minTime,minDistance,locationListener);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBUttonClick(View v){
        //EditText e1 = (EditText)findViewById(R.id.number1);
       // EditText e2 = (EditText)findViewById(R.id.number2);
        //TextView t1 = (TextView)findViewById(R.id.outputTextView);
        //int num1 = Integer.parseInt(e1.getText().toString());

       // int num2 = Integer.parseInt(e2.getText().toString());
       // Log.i(MA_Tag,Integer.toString(num1));
       // Log.i(MA_Tag,Integer.toString(num2));
        //int sum = num1 + num2;
        //t1.setText(Integer.toString(sum));
    }

    public void onFindPath(View v){
        EditText e1 = (EditText)findViewById(R.id.SrcName);
        EditText e2 = (EditText)findViewById(R.id.DesName);
        TextView t1 = (TextView)findViewById(R.id.OutputText);
        String res = "The best path from "+e1.getText().toString() + " to "+e2.getText().toString() + " is as follows:";
        t1.setText(res);
    }

    public void addListerOnShowMapButton(){
        srcNameVariable =(EditText)findViewById(R.id.SrcName);
        desNameVariable = (EditText)findViewById(R.id.DesName);
        ShowMapBUttonVariable = (Button)findViewById(R.id.ShowMap);
        ShowMapBUttonVariable.setOnClickListener(  //insde the Listener we are creating onclickListener
                new View.OnClickListener(){ //anonymous function
                    public void onClick(View v){
                        Toast.makeText(
                                MainActivity.this,
                                "The path on map from "+srcNameVariable.getText().toString()+" to "+desNameVariable.getText().toString()+":",
                                Toast.LENGTH_SHORT
                        ).show();


                    }
                }
        );
    }

    public void gotoMapsActivity(View v){
        Intent intent = new Intent(this,MapsActivity.class);
        EditText editText = (EditText)findViewById(R.id.SrcName);
        String sourceName = editText.getText().toString();
        editText = (EditText)findViewById(R.id.SrcName);
        String destinationName = editText.getText().toString();
        intent.putExtra(GOTO_MAPS_SRC,sourceName);
        intent.putExtra(GOTO_MAPS_DES,destinationName);
        startActivity(intent);
    }

    public void findMyLocation(){
        //MyLocation mylocation = new MyLocation();
    }


    //PRACTICE //http://www.codebind.com/android-tutorials-and-examples/android-activity-lifecycle/

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MA_Tag,"onStart"); //used for debugging
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(MA_Tag,"onResume"); //used for debugging
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MA_Tag,"onPause"); //used for debugging
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MA_Tag,"onStop"); //used for debugging
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(MA_Tag,"onRestart"); //used for debugging
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(MA_Tag,"onDestroy"); //used for debugging
    }

    //Google Maps stuff
    //private void setUpMap(){

     //   mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
   // }
}

