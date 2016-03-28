package com.darkenrule.tesismaestria;

import java.util.List;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.appunta.android.location.LocationFactory;
import com.appunta.android.orientation.Orientation;
import com.appunta.android.orientation.OrientationManager;
import com.appunta.android.orientation.OrientationManager.OnOrientationChangedListener;
import com.appunta.android.point.Point;
import com.appunta.android.point.renderer.impl.DrawablePointRenderer;
import com.appunta.android.ui.AppuntaView.OnPointPressedListener;
import com.appunta.android.ui.RadarView;

public class RadarActivity extends Activity implements OnOrientationChangedListener, OnPointPressedListener, OnSeekBarChangeListener {

	TextView textviewAzimuth, textviewPitch, textviewRoll;
	private RadarView radar;
	private OrientationManager compass;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.radar);
		compass = new OrientationManager(this);
		compass.setOnOrientationChangeListener(this);
		radar = (RadarView) findViewById(R.id.radarView1);
		radar.setOnPointPressedListener(this);
		radar.setMaxDistance(1);
		
		DrawablePointRenderer renderer=new DrawablePointRenderer(this.getResources(), R.drawable.marker);
		//List<Point> points=PointsModel.getPoints(renderer);
		//radar.setPoints(points);
		radar.setPosition(LocationFactory.createLocation(41.405098,2.192363));//BCN
		
		radar.setRotableBackground(R.drawable.arrow);
		
		((SeekBar)findViewById(R.id.seekBar1)).setOnSeekBarChangeListener(this);
		
		
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

	}
	
	@Override
	protected void onPause() {
		super.onPause();
		compass.stopSensor();
	}
	@Override
	protected void onResume() {
		super.onResume();
		compass.startSensor(this);
	}
	
	@Override
	public void onOrientationChanged(Orientation orientation) {
		radar.setOrientation(orientation);
		
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
		radar.setMaxDistance(arg1);
		radar.invalidate();
		this.setTitle("Distance: " + arg1 + "m.");
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPointPressed(Point p) {
		Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show();
	}
	

	
}

