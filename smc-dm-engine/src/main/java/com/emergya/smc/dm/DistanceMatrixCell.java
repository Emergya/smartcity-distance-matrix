package com.emergya.smc.dm;

import java.io.Serializable;

import org.geojson.LineString;

/**
*
* @author marcos
*/
public class DistanceMatrixCell implements Serializable {

	private Double distance;
	private Long time;
	private LineString route;
	
	public DistanceMatrixCell(Double d, Long t, LineString r){
		this.distance = d;
		this.time = t;
		this.route = r;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public LineString getroute() {
		return route;
	}

	public void setroute(LineString route) {
		this.route = route;
	}
	
}
