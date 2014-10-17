package com.emergya.smc.dm.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.emergya.smc.dm.DistanceMatrixCell;
import com.emergya.smc.dm.DistanceMatrixHandler;
import com.emergya.smc.dm.webapp.dto.DistanceMatrixRequest;

/**
*
* @author marcos
*/
@RestController
@RequestMapping("/dm")
public class DistanceMatrixController {
	
	@Autowired
	DistanceMatrixHandler dmhandler;
	
	@ResponseBody    
    @RequestMapping(method = RequestMethod.GET)    
    public String test() {
        return "Try using POST on /calculte path";
    }
	
	@ResponseBody
    @RequestMapping(value = "calculateDM", method = RequestMethod.POST)
    public DistanceMatrixCell[][] calculateMTSP(@RequestBody DistanceMatrixRequest request) {
		DistanceMatrixCell[][] matrix = null;
		if(request.getStopsTo().size() != 0){
			matrix = dmhandler.calculateDistanceMatrix(request.getStopsFrom(), request.getStopsTo());
		}else{
			matrix = dmhandler.calculateDistanceMatrix(request.getStopsFrom(), request.getStopsFrom());
		}
        return matrix;
    }

}
