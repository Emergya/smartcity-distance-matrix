package com.emergya.smc.dm.webapp.dto;

import java.io.Serializable;
import java.util.List;

import com.emergya.smc.model.Stop;

/**
*
* @author marcos
*/
public class DistanceMatrixRequest implements Serializable {

	private List<Stop> stopsFrom;
	private List<Stop> stopsTo;

	public List<Stop> getStopsFrom() {
		return stopsFrom;
	}

	public void setStopsFrom(List<Stop> stopsFrom) {
		this.stopsFrom = stopsFrom;
	}
	
	public List<Stop> getStopsTo() {
		return stopsTo;
	}

	public void setStopsTo(List<Stop> stopsTo) {
		this.stopsTo = stopsTo;
	}
}
