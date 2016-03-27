package com.darkenrule.tesismaestria;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.appunta.android.location.LocationFactory;
import com.appunta.android.orientation.Orientation;
import com.appunta.android.orientation.OrientationManager;
import com.appunta.android.orientation.OrientationManager.OnOrientationChangedListener;
import com.appunta.android.point.Point;
import com.appunta.android.point.renderer.PointRenderer;
import com.appunta.android.point.renderer.impl.EyeViewRenderer;
import com.appunta.android.point.renderer.impl.SimplePointRenderer;
import com.appunta.android.ui.AppuntaView.OnPointPressedListener;
import com.appunta.android.ui.CameraView;
import com.appunta.android.ui.EyeView;
import com.appunta.android.ui.RadarView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EyeViewActivity extends Activity implements
        OnOrientationChangedListener, OnPointPressedListener,LocationListener {

    private static ArrayList<Activity> activities=new ArrayList<Activity>();
    private static final int MAX_DISTANCE = 4000;
    private EyeView ar;
    private RadarView cv;
    private CameraView camera;
    private FrameLayout cameraFrame;
    private OrientationManager compass;
    private static TextView textView;
    public static String resultado;

    float x, y, z;
    private List<Point> points;
    private List<Point> cpoints;
    private LocationManager locationManager;
    double longitud = -99.515111;
    double latitud = 14.52561;
    static PuntoModelo[] puntoModelo;
    static boolean inicio = false;
    private double orXant,orYant,orZant;
    static Context context;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activities.add(this);
        setContentView(R.layout.ar);
        resultado = "";
        context = this.getApplicationContext();

        compass = new OrientationManager(this);
        compass.setAxisMode(OrientationManager.MODE_AR);
        compass.setOnOrientationChangeListener(this);
        compass.startSensor(this);

        //parte del LocationManager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);

        ar = (EyeView) findViewById(R.id.augmentedView1);
        cv = (RadarView) findViewById(R.id.radarView1);

        ar.setMaxDistance(MAX_DISTANCE);
        cv.setMaxDistance(MAX_DISTANCE);

        ar.setOnPointPressedListener(this);
        cv.setOnPointPressedListener(this);

       /* PointRenderer arRenderer = new EyeViewRenderer(getResources(), R.drawable.cinepolis, R.drawable.cinepolis);
        do {
            try {
                points = PointsModel.getPoints(arRenderer);
                cpoints = PointsModel.getPoints(new SimplePointRenderer());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }while (puntoModelo==null);

        ar.setPoints(points);
        //ar.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        //ar.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        ar.setPosition(LocationFactory.createLocation(longitud, latitud, 12));// BCN
        ar.setOnPointPressedListener(this);
        cv.setPoints(cpoints);
        //cv.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        cv.setPosition(LocationFactory.createLocation(longitud, latitud, 12));// BCN*/

        //cv.setRotableBackground(R.drawable.arrow);

        try {
            pintarPuntos();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        cameraFrame=(FrameLayout) findViewById(R.id.cameraFrame);

        camera=new CameraView(this);

        cameraFrame.addView(camera);
        textView=(TextView) findViewById(R.id.textView);
        textView.setBackgroundColor(Color.WHITE);
        textView.setText("Longitud: " + longitud + "\n" + "latitud: " + latitud);

        new IniciarJSON();
        new RecuperarJSON();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Etrando al onPause: ", "entrando al onPause");
        compass.stopSensor();
        finish();



    }

    @Override
    protected void onResume() {
        super.onStart();
        compass.startSensor(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activities.remove(this);
    }

    @Override
    public void onOrientationChanged(Orientation orientation) {
        double valor = 0.02;
        //Log.e("Orientation ?????????????????: ", orientation.getX() + " " +orientation.getY() + " " +orientation.getZ());
        if(orXant==0.0){
            orXant = orientation.getX();
            orYant = orientation.getY();
            orZant = orientation.getZ();
            ar.setOrientation(orientation);//parte original
            ar.setPhoneRotation(OrientationManager.getPhoneRotation(this));//parte original
            cv.setOrientation(orientation); //parte original
        }

        if((Math.abs(orientation.getX()-orXant)>valor)&&(Math.abs(orientation.getY()-orYant)>valor)
                && (Math.abs(orientation.getZ()-orZant)>valor)){
            orXant = orientation.getX();
            orYant = orientation.getY();
            orZant = orientation.getZ();
            ar.setOrientation(orientation);
            ar.setPhoneRotation(OrientationManager.getPhoneRotation(this));
            cv.setOrientation(orientation);
        }


    }

    /**
     * Accion que toma el punto seleccionado
     * @param p
     */
    @Override
    public void onPointPressed(Point p) {

        Intent i = new Intent(this, CarteleraActivity.class);
        startActivity(i);
        unselectAllPoints();
        p.setSelected(true);
    }

    private void unselectAllPoints() {
        for (Point point : points) {
            point.setSelected(false);
        }
    }

    public void pintarPuntos() throws JSONException {

        /*PointRenderer arRenderer = new EyeViewRenderer(getResources(), R.drawable.cinepolis, R.drawable.cinepolis);
        do {
            points = PointsModel.getPoints(arRenderer);
            cpoints = PointsModel.getPoints(new SimplePointRenderer());
        }while (puntoModelo==null);
        ar.setPoints(points);
        //ar.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        ar.setPosition(LocationFactory.createLocation(longitud, latitud, 12));// BCN
        cv.setPoints(cpoints);
        //cv.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        cv.setPosition(LocationFactory.createLocation(longitud, latitud, 12));// BCN*/

        PointRenderer arRenderer = new EyeViewRenderer(getResources(), R.drawable.cinepolis, R.drawable.cinepolis);

            try {
                points = PointsModel.getPoints(arRenderer);
                cpoints = PointsModel.getPoints(new SimplePointRenderer());
            } catch (JSONException e) {
                e.printStackTrace();
            }


        ar.setPoints(points);
        //ar.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        //ar.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        ar.setPosition(LocationFactory.createLocation(longitud, latitud, 12));// BCN
        ar.setOnPointPressedListener(this);
        cv.setPoints(cpoints);
        //cv.setPosition(LocationFactory.createLocation(41.383873, 2.156574, 12));// BCN
        cv.setPosition(LocationFactory.createLocation(longitud, latitud, 12));// BCN*/

        cv.setRotableBackground(R.drawable.arrow);

        if(camera!=null) {
            camera.invalidate();
        }
        if(cameraFrame!=null) {
            cameraFrame.invalidate();
        }
        if(ar!=null){
            ar.invalidate();
        }
    }


    //Parte que obtiene la coordenada


    @Override
    public void onLocationChanged(Location location) {

        Log.i("onLocationChanged", "BEGIN");

        if (location == null) {
            Log.i("location", "null");
            return;
        }

        longitud = location.getLongitude();
        Log.i("longitud", String.valueOf(longitud));
        latitud = location.getLatitude();
        Log.i("latitud", String.valueOf(latitud));

        new HttpAsyncTask().execute("http://148.204.66.136/tesis/public/cines");

        try {
            pintarPuntos();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        pintarArregloParaPruebas();


    }

    public void pintarArregloParaPruebas(){
        String vof="";
        if(puntoModelo==null){
            vof="vacio";
        }else if(puntoModelo!=null){
            vof="";
            for(int i =0; i<puntoModelo.length; i++){
                vof += puntoModelo[i].getName() + " " + puntoModelo[i].getLocation().getLongitude() + " " + puntoModelo[i].getLocation().getLatitude() +"\n";
            }
        }

        textView.setText("Longitud: " + longitud + "\n" + "latitud: " + latitud + "\nTexto longitud: "
                + resultado.length() + "\n" + resultado + "\nPuntomodelo: " + vof);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    //parte del JSON que envía

    /**
     * Verifica que este conectado
     *
     * @return
     */
    public boolean isConnected() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    /**
     *
     * @param url
     * @param punto
     * @return
     */
    public String POST(String url, Punto punto) {
        InputStream inputStream = null;
        String result = "";
        try {
            //1 crea el HTTPCLient
            HttpClient httpClient = new DefaultHttpClient();

            //2 hace el post a la url dada
            HttpPost httpPost = new HttpPost(url);

            String json = "";

            //3 construir el objeto JSON
            JSONObject jsonObject = new JSONObject();
            jsonObject.accumulate("longitud", punto.getLongitud());
            jsonObject.accumulate("latitud", punto.getLatitud());

            //4 convertir JSONObject a JSON string
            json = jsonObject.toString();

            //5 set Json a StringEntity (datos crudos)
            StringEntity se = new StringEntity(json);

            //6 set httpst Entity
            httpPost.setEntity(se);

            //7 set algunos encabezados para informar al servidor acerca del tipo del contenido
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json");

            //8 Ejecutar la solicitud del POST a la url dada
            HttpResponse httpResponse = httpClient.execute(httpPost);

            //recibir la respuesta como un inputstream
            inputStream = httpResponse.getEntity().getContent();

            //convertir el inputstream a string
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
            } else {
                result = "no funciono";
            }
        } catch (Exception e) {
            Log.d("Inputstream ", e.getLocalizedMessage());
        }

        resultado = result;

        //11 regresar result
        return result;
    }

    /**
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

        inputStream.close();
        return result;
    }

    /**
     * Clase Asincronica que esta recuperando desde el web service
     */
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        String tempo = "";
        @Override
        protected synchronized String doInBackground(String... params) {
            Punto punto = new Punto();
            punto.setLatitud(latitud);
            punto.setLongitud(longitud);
            return POST(params[0], punto);
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                puntoModelo = null;
                puntoModelo = recuperarJSON(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }




    //hacer la recuperación del JSON y trabajarlo para hacerlo puntos
    public synchronized static PuntoModelo[] recuperarJSON(String json) throws JSONException {

        String location1 = "";
        String re = "";
        String losDos = "";
        String name = "";
        JSONArray array = new JSONArray(json);
        puntoModelo = new PuntoModelo[array.length()];
        for (int i = 0; i < array.length(); i++) {
            JSONObject row = array.getJSONObject(i);
            location1 = row.getString("st_astext").replace("POINT(", "").replace(")", "");
            String latlong[] = location1.split(" ");
            Location location = LocationFactory.createLocation(Double.parseDouble(latlong[1]), Double.parseDouble(latlong[0]), 50.0);
            puntoModelo[i]=(new PuntoModelo(row.getString("name"), location));
            losDos += row.getString("name") + " " + latlong[0] + " " + latlong[1] + "\n";
        }

        return puntoModelo;

    }

    public synchronized static PuntoModelo[] getPuntoModelo() throws JSONException{
        return puntoModelo;
    }





    /**
     * Clase Runnable que optimiza la recuperación de los puntos
     */
    public class RecuperarJSON implements Runnable {
        Thread thread = new Thread(this);

        RecuperarJSON(){
            thread.start();
        }
        @Override
        public void run() {
            while (true) {
                try {
                    getPuntoModelo();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class IniciarJSON implements Runnable{

        Thread thread = new Thread(this);

        IniciarJSON(){
            thread.start();
        }

        @Override
        public void run() {

                new HttpAsyncTask().execute("http://148.204.66.136/tesis/public/cines");
        }
    }



}