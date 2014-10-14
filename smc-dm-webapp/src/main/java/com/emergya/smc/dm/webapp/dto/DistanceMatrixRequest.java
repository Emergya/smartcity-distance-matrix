package com.emergya.smc.dm.webapp.dto;

import java.io.Serializable;
import java.util.List;

import com.emergya.smc.model.Stop;

/**
*
* @author marcos
*/
public class DistanceMatrixRequest implements Serializable {

	private List<Stop> stops;

	public List<Stop> getStops() {
		return stops;
	}

	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}
}
