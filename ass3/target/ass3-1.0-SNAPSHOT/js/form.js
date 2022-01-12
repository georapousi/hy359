//sign up form javascript
//global vars
var password = document.getElementById('password'); 
var password_confirm = document.getElementById('password_confirm');
var lat;
var lon;
var clicked_openmap = false;
var getLat;
var getLon;
var flag = false;
var flag1 = false;
var map;
var markers;


if(navigator.geolocation){

		navigator.geolocation.getCurrentPosition(

			function(position){
				console.log(position);
				getLat = position.coords.latitude;
				getLon = position.coords.longitude;
			}

		);
		

}
//check if passwords match
function validatePassword(){

	console.log(password.value);
  	console.log(password_confirm.value);

  	
  	if(password.value != password_confirm.value) {

    document.getElementById('message').innerHTML = "Passwords don't match!";
 
 	} else {

    document.getElementById('message').innerHTML = "Valid Password";
    }

 	
}

// display eye on passwords
var icon1 = document.getElementById('icon1'),
	icon2 = document.getElementById('icon2');

	icon1.onclick = function () {
		
		if (password.className == 'txt'){
			password.setAttribute('type','text');
			icon1.className = 'fa fa-eye';
			password.className = '';

		}else{

			password.setAttribute('type','password');
			icon1.className = 'fa fa-eye-slash';
			password.className = 'txt';

		}
	}


    if(icon2 != null){
	icon2.onclick = function () {
		
		if (password_confirm.className == 'txt'){
			password_confirm.setAttribute('type','text');
			icon2.className = 'fa fa-eye';
			password_confirm.className = '';

		}else{

			password_confirm.setAttribute('type','password');
			icon2.className = 'fa fa-eye-slash';
			password_confirm.className = 'txt';

		}
	}
    }
//password srength

function passwordStrength() {

	var text = document.getElementById('password').value;
	var uni = new Set();
	var dub = new Set();
	var num = new Set();
	var tmp = 0;
	var counter = 0;

	for(var i=0; i<text.length;i++){
		counter = 0;
		//check multiple chars
		for(var j=0; j<text.length; j++){

			
			if(text.charAt(i) == text.charAt(j)){
				
				counter++;
			}
			
		}
		if(tmp<counter){
			tmp = counter;
		}

		//check for numbers
		if( !isNaN(text.charAt(i)) ){
			num.add(text.charAt(i));
			
			
		}
		//check for unique
		if( uni.has(text.charAt(i)) ){
			dub.add(text.charAt(i));
		}else{
			uni.add(text.charAt(i));
		}
	}
	

	const iterator = dub.values();

	for(i=0; i< dub.size; i++){

		var char = iterator.next().value;
		if(uni.has(char)){
			uni.delete(char);
		}
	}


	var x = 0.8;
	var y = 0.5;

	console.log(uni.size);
	console.log(dub.size);
	console.log(num.size);
	console.log(text.length);
	console.log(tmp);

	if( (num.size/text.length) >= y || (tmp/text.length)>=y){
		document.getElementById('strength').innerHTML = "Password Weak";


	}else if( (uni.size/text.length)>= x){
		document.getElementById('strength').innerHTML = "Password Strong";

	}else{
		document.getElementById('strength').innerHTML = "Password Medium";
	}

}
		
	
// Doctor  infos show on click	

function toggleDoc(){

	if(document.getElementById('toggle').style.display = "none"){
		document.getElementById('toggle').style.display = "block";

	}
	document.getElementsByName('address')[0].placeholder = 'Office Address';
}

function toggleUser(){

	if(document.getElementById('toggle').style.display = "block"){
		document.getElementById('toggle').style.display = "none";	
	}
	document.getElementsByName('address')[0].placeholder = 'Address';
}

//check amka with date birth

function checkDate(){

	
	var reversed = [];
	
	var date = document.getElementById('birthdate').value;
	var amka = document.getElementById('amka').value;

	

	reversed[0] = date[8];
	reversed[1] = date[9];
	reversed[2] = date[5];
	reversed[3] = date[6];
	reversed[4] = date[2];
	reversed[5] = date[3];

	

	for (var i = 0; i < 6; i++) {	
               
               
		if(amka[i]!== reversed[i]){

			document.getElementById('msgdate').innerHTML = "AMKA doesn't match your birth day";
                        break;
		}else{

			document.getElementById('msgdate').innerHTML = "AMKA matches birth day";
		}
                
	}
        
        
             console.log(reversed);
             console.log(amka);
             console.log("one match");
            
            
        
	
}

//checkbox is checked
function CheckBox(){

	if(!document.getElementById('agree').checked){

		document.getElementById('chkBox').innerHTML = "You must check the box above!";
		document.getElementById('chkBox').style.color = 'red';
	}


}

//Maps
//check for valid address	
function request(){

	const data = null;

	const xhr = new XMLHttpRequest();
	xhr.withCredentials = true;

	xhr.addEventListener("readystatechange", function () {
		if (this.readyState === this.DONE) {

			console.log(this.responseText);

			if(this.responseText == "{}"){
				console.log('No such address');
				document.getElementById('addressMsg').innerHTML = "No such address";

			}
			else {

				var txt = JSON.parse(this.responseText);

				var display_name = txt["0"].display_name;
				lat = txt["0"].lat;
				lon = txt["0"].lon;
				var result = display_name.includes("Crete");

				if(!result){
					document.getElementById('addressMsg').innerHTML = "Sorry this Map only works in Crete";
				}else{
					document.getElementById('addressMsg').innerHTML = "Valid Address";
				}
				
			}		
		}
	});

	var address = getAddress();
	console.log(address);

	xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/search?q="+ address + "&accept-language=en&polygon_threshold=0.0");
	xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
	xhr.setRequestHeader("x-rapidapi-key", "505aa1e186msha079b12fec5c292p13cdbcjsnd29829221d55");

	xhr.send(data);
}

function reverseRequest(){

	const data = null;

	const xhr = new XMLHttpRequest();
	xhr.withCredentials = true;

	xhr.addEventListener("readystatechange", function () {
		if (this.readyState === this.DONE) {
			console.log(this.responseText);

			if(this.responseText == "{}"){
				document.getElementById('autoMsg').innerHTML = "Coordinates Not Found";
				return;
			}

			try {

		       var coords =  JSON.parse(this.response);
		       var address = JSON.parsed(coords.address);

		       // console.log(JSON.stringify(this.responseText));
		       console.log('response data?', coords);
		     } catch(error) {
		       console.log('Error happened here!');
		       console.error(error);
		     }

		}

		var addr = coords.address.road;

		var city = coords.address.city;

		var country = coords.address.country;

		document.getElementById('address').value = addr;
		document.getElementById('city').value = city;
		document.getElementById('country').value = country;

		console.log(addr,city,country);
		



	});



	xhr.open("GET", "https://forward-reverse-geocoding.p.rapidapi.com/v1/reverse?lat="+getLat+"&lon="+getLon+"&accept-language=en&polygon_threshold=0.0");
	xhr.setRequestHeader("x-rapidapi-host", "forward-reverse-geocoding.p.rapidapi.com");
	xhr.setRequestHeader("x-rapidapi-key", "505aa1e186msha079b12fec5c292p13cdbcjsnd29829221d55");

	xhr.send(data);

	autoMap();

}

function getCoordinates(){

	var lat = position.coords.latitute;
	var lon = position.coords.longitute;

	var coords = lat + ' ' + lon;

	return coords;

}


//get address as one string
 function getAddress(){

	var country = document.getElementById('country').value ;
	var city = document.getElementById('city').value ;
	var address = document.getElementById('address').value ;

	var totalAddress = country +' ' + city + ' ' + address;

	return totalAddress;
 } 

function setPosition(lat, lon){
	var fromProjection = new OpenLayers.Projection("EPSG:4326"); // Transform from WGS 1984
	var toProjection = new OpenLayers.Projection("EPSG:900913"); // to Spherical Mercator Projection
	var position = new OpenLayers.LonLat(lon, lat).transform( fromProjection,
	toProjection);
	return position;
}


function handler(position, message){
	var popup = new OpenLayers.Popup.FramedCloud("Popup",
	position, null,
	message, null,
	true // <-- true if we want a close (X) button, false otherwise
	);
	map.addPopup(popup);
}

function showMap() {

	
	if(clicked_openmap == false){
		document.getElementById('Map').style.display = "block";
		clicked_openmap = true;
	}
	else {
		document.getElementById('Map').style.display = "none";
		clicked_openmap = false;
	}

	if(flag == false) {
		map = new OpenLayers.Map("Map");
		var mapnik = new OpenLayers.Layer.OSM();
		map.addLayer(mapnik);

	}	


		markers = new OpenLayers.Layer.Markers( "Markers" );
		map.addLayer(markers);

		var position=setPosition(lat,lon);
		var mar=new OpenLayers.Marker(position);
		markers.addMarker(mar);
		mar.events.register('mousedown', mar, function(evt) {
			handler(position,'Your Address');

		});

		//Orismos zoom
		const zoom = 7;
		map.setCenter(position, zoom);

		flag = true;	
}
	


function autoMap() {


	if(clicked_openmap == false){
		document.getElementById('Map').style.display = "block";
		clicked_openmap = true;
	}
	else {
		document.getElementById('Map').style.display = "none";
		clicked_openmap = false;
	}

	if(flag1 == false){
		map = new OpenLayers.Map("Map");
		var mapnik = new OpenLayers.Layer.OSM();
		map.addLayer(mapnik);
	}
	

	


	markers = new OpenLayers.Layer.Markers( "Markers" );
	map.addLayer(markers);

	var position=setPosition(getLat,getLon);
	var mar=new OpenLayers.Marker(position);
	markers.addMarker(mar);
	mar.events.register('mousedown', mar, function(evt) {
	handler(position,'Your Address');

});

	//Orismos zoom
	const zoom = 7;
	map.setCenter(position, zoom);
	flag1 = true;

	

	
}
	
//redirect sto login

function RedirectLogin(){
    
    
}



