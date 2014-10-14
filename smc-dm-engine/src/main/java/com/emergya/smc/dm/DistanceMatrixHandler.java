package com.emergya.smc.dm;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jetty.util.ajax.JSON;
import org.geojson.Geometry;
import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emergya.smc.model.Stop;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopperAPI;
import com.graphhopper.http.GraphHopperWeb;
import com.graphhopper.util.PointList;

/**
*
* @author marcos
*/
@Component
public class DistanceMatrixHandler {
	
	@Value("${smc.dm.graphhopperURL}")
    private String GRAPHHHOPPER_URL;
	
	private GraphHopperAPI gh;
	
	public DistanceMatrixCell[][] calculateDistanceMatrix(final List<Stop> stops){
		DistanceMatrixCell[][] matrix = new DistanceMatrixCell[stops.size()][stops.size()];
		if (gh == null) {
            gh = new GraphHopperWeb();
            gh.load(this.GRAPHHHOPPER_URL);
        }
		for(int i=0; i<stops.size(); i++){
			for(int j=0; j<stops.size(); j++){
				if(i!=j){
					Stop startStop = stops.get(i);
					Stop endStop = stops.get(j);
					GHRequest request = new GHRequest(startStop.getLatitude(), startStop.getLongitude(), endStop.getLatitude(), endStop.getLongitude());
					try {
	                    GHResponse response = gh.route(request);
	                    Double d = response.getDistance();
	                    Long t = response.getMillis();
	                    PointList points = response.getPoints();
	                    LineString route = this.getRoute(points);
	                    DistanceMatrixCell dmc = new DistanceMatrixCell(d, t, route);
	                    matrix[i][j] = dmc;
	                } catch(Exception e){
	                	
	                }
				}else{
					matrix[i][j] = null;
				}
			}
		}
		return matrix;
	}
	
	private LineString getRoute(PointList points){
		LineString route = new LineString();
		for(int p=0; p<points.getSize(); p++){
			Double latitude = points.getLatitude(p);
			Double longitude = points.getLongitude(p);
			route.add(new LngLatAlt(longitude, latitude));
		}
		return route;
	}

}
