package org.example.mymap;

import android.os.Bundle;
import android.util.Log;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;

public class MyMap extends MapActivity {
    private MapView map;
    private MapController controller;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initMapView();
        initMyLocation();
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	private void initMapView() {
		map = (MapView) findViewById(R.id.map);
		if (map == null) {
			Log.d("ERROR", "MapView je null");
		}
		controller = map.getController();
		map.setSatellite(false);
		map.setBuiltInZoomControls(true);
	}
	
	private void initMyLocation() {
		final MyLocationOverlay overlay = new MyLocationOverlay(this, map);
		overlay.enableMyLocation();
		overlay.enableCompass();
		overlay.runOnFirstFix(new Runnable() {

			@Override
			public void run() {
				// Zoom to current location
				controller.setZoom(8);
				controller.animateTo(overlay.getMyLocation());
			}
		});
		map.getOverlays().add(overlay);
	}
    
    
}