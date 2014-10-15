package com.emergya.smc.dm;

import java.util.List;

import javax.annotation.PostConstruct;

import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emergya.smc.model.Stop;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.PointList;

/**
*
* @author marcos
*/
@Component
public class DistanceMatrixHandler {
	
	@Value("${smc.dm.osmFilePath}")
	private String OSM_FILE_PATH;
	@Value("${smc.dm.graphPath}")
	private String GRAPH_PATH;
	@Value("${smc.dm.vehicle}")
	private String VEHICLE;
	@Value("${smc.dm.weighting}")
	private String WEIGHTING;
	
	private GraphHopper hopper;
	
	public DistanceMatrixCell[][] calculateDistanceMatrix(final List<Stop> stops){
		DistanceMatrixCell[][] matrix = new DistanceMatrixCell[stops.size()][stops.size()];
		
		for(int i=0; i<stops.size(); i++){
			for(int j=0; j<stops.size(); j++){
				if(i!=j){
					Stop startStop = stops.get(i);
					Stop endStop = stops.get(j);
					GHRequest request = new GHRequest(startStop.getLatitude(), startStop.getLongitude(), endStop.getLatitude(), endStop.getLongitude()).
						    setWeighting(this.WEIGHTING).
						    setVehicle(this.VEHICLE);
					try {
	                    GHResponse response = hopper.route(request);
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
	
	@PostConstruct
	public void init(){
		hopper = new GraphHopper().forServer();
		hopper.setInMemory(true);
		hopper.setOSMFile(this.OSM_FILE_PATH);
		hopper.setGraphHopperLocation(this.GRAPH_PATH);
		hopper.setEncodingManager(new EncodingManager(this.VEHICLE));
		
		hopper.importOrLoad();
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
