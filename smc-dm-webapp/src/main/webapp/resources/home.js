/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var mtsp = {
    stopList: [],
    map: null,
    routesGroup: null,
    initMap: function () {

        var map = L.map('map').setView([37.389254195966004, -5.977935791015625], 13);

        mtsp.routesGroup = L.featureGroup([]);
        mtsp.routesGroup.addTo(map);


        L.tileLayer('http://{s}.tile.openstreetmap.se/hydda/full/{z}/{x}/{y}.png', {
            attribution: 'Tiles courtesy of <a href="http://openstreetmap.se/" target="_blank">OpenStreetMap Sweden</a> &mdash; Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>',
            maxZoom: 18
        }).addTo(map);

        map.on("click", function (e) {
            mtsp.stopList.push({
                name: "Stop",
                latitude: e.latlng.lat,
                longitude: e.latlng.lng
            });

            L.marker([e.latlng.lat, e.latlng.lng]).addTo(map);

            $("#stopList").append("<li>" + e.latlng.lat + ", " + e.latlng.lng + "</li>");
        });

        mtsp.map = map;

    },
    
    calculateRoutes: function () {
        mtsp.routesGroup.clearLayers();
        var calculateRouteThis = this;
        $.ajax({
            method: "POST",
            url: "dm/calculateDM",
            data: JSON.stringify({stops: mtsp.stopList}),
            success: function (data) {
                console.debug(data);
                for(var i=0; i<data.length; i++){
                	for(var j=0; j<data.length; j++){
                		if(i!=j){
                			L.geoJson(data[i][j].route, {"color": calculateRouteThis.get_random_color()}).addTo(calculateRouteThis.map);
                		}
                	}
                }
            },
            dataType: "json",
            contentType: "application/json"
        });
    },
    
    get_random_color: function(){
        var letters = '0123456789ABCDEF'.split('');
        var color = '#';
        for (var i = 0; i < 6; i++ ){
           color += letters[Math.round(Math.random() * 15)];
        }
        return color;
    }
};


window.onload = mtsp.initMap;