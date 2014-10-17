<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        
        <link rel="stylesheet" href="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css" />
        <link rel="stylesheet" href="resources/style.css" />
         <script src="http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script>
         <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
         <script src="resources/home.js"></script>
    </head>
    <body>
        <div id="map"></div>
        
        <div id="stopListCtr">
            <button type="button" onclick="mtsp.calculateRoutes()">Calculate routes!</button>
            <ul id="stopList">
                
            </ul>
        </div>
        <div id="buttons">
        	<button type="button" onclick="mtsp.getStopsFrom()">Add Stops From</button>
        	<button type="button" onclick="mtsp.getStopsTo()">Add Stops To</button>
        </div>
    </body>
</html>
