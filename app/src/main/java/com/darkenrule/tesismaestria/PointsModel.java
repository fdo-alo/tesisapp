package com.darkenrule.tesismaestria;

import android.util.Log;

import com.appunta.android.location.LocationFactory;
import com.appunta.android.point.Point;
import com.appunta.android.point.impl.SimplePoint;
import com.appunta.android.point.renderer.PointRenderer;
import com.appunta.android.point.renderer.impl.EyeViewRenderer;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PointsModel {

	 //List<Point> points = new ArrayList<>();

	/*static List<Point> getPoints(PointRenderer renderer) {
		List<Point> points = new ArrayList<>();

		points.add(new SimplePoint(01, LocationFactory.createLocation(-99.383973,14.147291,240), renderer, "A"));
		points.add(new SimplePoint(02, LocationFactory.createLocation(41.383635,2.147591,30), renderer, "B"));
		points.add(new SimplePoint(03, LocationFactory.createLocation(41.382379, 2.147548, 37), renderer, "C"));
		points.add(new SimplePoint(04, LocationFactory.createLocation(41.386936,2.147055,25), renderer, "D"));
		points.add(new SimplePoint(05, LocationFactory.createLocation(41.389077,2.147248,34), renderer, "E"));
		points.add(new SimplePoint(06, LocationFactory.createLocation(41.414490,2.179167,34), renderer, "F"));
		points.add(new SimplePoint(07, LocationFactory.createLocation(41.412090,2.188952,74), renderer, "G"));
		points.add(new SimplePoint( 8, LocationFactory.createLocation(41.403011,2.196333,25), renderer, "H"));
		points.add(new SimplePoint( 9, LocationFactory.createLocation(41.408001,2.198221,17), renderer, "I"));
		points.add(new SimplePoint(10, LocationFactory.createLocation(41.411091,2.196933,39), renderer, "J"));
		points.add(new SimplePoint(11, LocationFactory.createLocation(41.406265,2.203071,45), renderer, "K"));
		points.add(new SimplePoint(12, LocationFactory.createLocation(41.403206,2.208134,120), renderer, "L"));
		points.add(new SimplePoint(13, LocationFactory.createLocation(41.401951,2.194530,70), renderer, "M"));
		points.add(new SimplePoint(14, LocationFactory.createLocation(41.395157,2.187965,43), renderer, "N"));
		points.add(new SimplePoint(15, LocationFactory.createLocation(41.400696,2.176463,23), renderer, "O"));
		points.add(new SimplePoint(16, LocationFactory.createLocation(41.407776,2.185389,63), renderer, "P"));
		points.add(new SimplePoint(17, LocationFactory.createLocation(41.412701,2.195217,57), renderer, "Q   "));
		points.add(new SimplePoint(18, LocationFactory.createLocation(41.409485,2.198350,78), renderer, "R   "));
		points.add(new SimplePoint(19, LocationFactory.createLocation(41.407066,2.202642,33), renderer, "S   "));
		points.add(new SimplePoint(20, LocationFactory.createLocation(41.405521,2.205946,32), renderer, "T   "));
		points.add(new SimplePoint(21, LocationFactory.createLocation(41.402657,2.199766,75), renderer, "U   "));
		points.add(new SimplePoint(22, LocationFactory.createLocation(41.400276,2.199165,256), renderer, "V   "));
		points.add(new SimplePoint(23, LocationFactory.createLocation(41.403076,2.192856,45), renderer, "W   "));
		points.add(new SimplePoint(24, LocationFactory.createLocation(41.399536,2.190625,66), renderer, "X   "));
		points.add(new SimplePoint(25, LocationFactory.createLocation(41.402946,2.189896,55), renderer, "Y   "));
		points.add(new SimplePoint(26, LocationFactory.createLocation(41.401047,2.184703,77), renderer, "Z   "));
		points.add(new SimplePoint(27, LocationFactory.createLocation(41.403336,2.178180,33), renderer, "0   "));
		points.add(new SimplePoint(28, LocationFactory.createLocation(41.407970,2.180926,44), renderer, "1   "));
		points.add(new SimplePoint(29, LocationFactory.createLocation(41.412540,2.179295,64), renderer, "2   "));
		points.add(new SimplePoint(30, LocationFactory.createLocation(41.413280,2.190282,32), renderer, "3   "));
		points.add(new SimplePoint(31, LocationFactory.createLocation(41.411446,2.187706,99), renderer, "4   "));


		return points;
	}*/


	public static List<Point> getPoints(PointRenderer renderer) throws JSONException {
		List<Point> points = new ArrayList<>();

		if(EyeViewActivity.getPuntoModelo()!=null) {
			PuntoModelo[] puntoModelos = EyeViewActivity.getPuntoModelo();
			Log.d("Entrando a get points:", "entrando a getpoints");
			for (int i = 0; i < puntoModelos.length; i++) {
				if(puntoModelos[i].getName().contains("Cineme")|| puntoModelos[i].getName().contains("CineMe")) {
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.cinemex, R.drawable.cinemex);
				}else if(puntoModelos[i].getName().contains("Cinepo")|| puntoModelos[i].getName().contains("CinePo")){
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.cinepolis, R.drawable.cinepolis);
				}else if(puntoModelos[i].getName().contains("Cinemar")||puntoModelos[i].getName().contains("CineMar")){
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.cinemark, R.drawable.cinemark);
				}else if(puntoModelos[i].getName().contains("Tonal")){
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.tonala, R.drawable.tonala);
				}else if(puntoModelos[i].getName().contains("Xtreme")){
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.xtreme, R.drawable.xtreme);
				}else if(puntoModelos[i].getName().contains("Multimedios C")){
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.mmcinema, R.drawable.mmcinema);
				}else if(puntoModelos[i].getName().contains("Cineteca")){
					renderer = new EyeViewRenderer(EyeViewActivity.context.getResources(), R.drawable.cineteca, R.drawable.cineteca);
				}

				points.add(new SimplePoint(i, LocationFactory.createLocation(puntoModelos[i].getLocation().getLongitude(), puntoModelos[i].getLocation().getLatitude()), renderer, puntoModelos[i].getName()));
				//points.add(new SimplePoint(01, LocationFactory.createLocation(41.383973,2.147291,240), renderer, "A"));
			}
		}
		return points;
	}

}







