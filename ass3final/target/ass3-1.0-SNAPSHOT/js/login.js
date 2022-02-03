'use strict';
var username;
var password;
var weight;
var height;
var gender;
var amka;
var coordinates;
var userLat;
var userLon;
var coorlist;

function CheckLogin(){
     
    var xhr = new XMLHttpRequest(); 
    
    username = document.getElementById("username").value;
    password = document.getElementById("password").value;
    
    if(username === "admin" && password === "admin12*"){
        window.location ="http://localhost:8080/ass3/admin.html";
    }
    
    console.log(username+ " " + password);
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
                      
            
            document.getElementById('sign-form').style.display = 'none';
            document.getElementById('logout-btn').style.display = 'block';
            document.getElementById('navbar').style.display = 'block';
            document.getElementById('msgwelcome').innerHTML = 'Welcome';
            document.getElementById('footer').style.display = 'none';
            
            
        }else if(xhr.status == 201){
            
            window.location ="http://localhost:8080/ass3/doctor.html";
            
        }else if (xhr.status !== 200) {
            document.getElementById('msglogin').innerHTML = 'NonExist Password or Username';
        }
    };
    
    xhr.open('POST','login');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send('username='+ username +'&password=' + password);
 }

function accountEdit(){
    
    
    var xhr = new XMLHttpRequest(); 
    
   
    console.log(username + " " + password);
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('account').style.display = 'block ';
            document.getElementById('msghealth').style.display = 'none';
            document.getElementById('msgideal').style.display = 'none';
            document.getElementById('exam').style.display = 'none';
            
            
            var json = JSON.parse(xhr.response);
            
            document.getElementById('msgwelcome').innerHTML = '';
            
            document.getElementById('username1').value = json.username;
            document.getElementById('password1').value = json.password;
            document.getElementById('email').value = json.email;
            document.getElementById('firstname').value = json.firstname;
            document.getElementById('lastname').value = json.lastname;
            document.getElementById('birthdate').value = json.birthdate;
            document.getElementById('sex').value = json.gender;
            document.getElementById('country').value = json.country;
            document.getElementById('city').value = json.city;
            document.getElementById('address').value = json.address;
            document.getElementById('telephone').value = json.telephone;
            document.getElementById('weight').value = json.weight;
            document.getElementById('height').value = json.height;
            document.getElementById('amka').value = json.amka;
            document.getElementById('donor').value = json.blooddonor;
            document.getElementById('bloodtype').value = json.bloodtype;
            
            
        }else if (xhr.status !== 200) {
            
           
        }
    };
    var data = username + '\n' +
             password;
     
    xhr.open('POST','userDetails' );
    xhr.send(data);
    
}


function updateUser(){
    var xhr = new XMLHttpRequest(); 
    
    var username = document.getElementById("username1").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password1").value;
    
    
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    
    var birthdate = document.getElementById("birthdate").value;
    
    amka = document.getElementById("amka").value;
    var country = document.getElementById("country").value;
    var address = document.getElementById("address").value;
    var city = document.getElementById("city").value;
    var telephone = document.getElementById("telephone").value;
    height = document.getElementById("height").value;
    weight = document.getElementById("weight").value;
    
    var bloodtype = document.getElementById("bloodtype").value;
    
    
    var gender = document.getElementById('sex').value;
    var blooddonor =document.getElementById('donor').value;
    
    xhr.onload = function () {
        
        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("update was successful");
        }else{
            document.getElementById('msgemail').innerHTML = 'Email Already Exists';
        };
    }
    
    xhr.open('POST','updateUser',true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    console.log(blooddonor);
    
    var data = firstname + '\n' +
            lastname + '\n' +
            telephone + '\n' +
            username + '\n' +
            country + '\n' +
            city + '\n' +
            address + '\n' +
            email + '\n' +
            password + '\n' +
            birthdate + '\n' +
            gender + '\n' +
            amka + '\n' +
            height + '\n' +
            weight + '\n' +
            blooddonor + '\n' +
            bloodtype;
    xhr.send(data);
     
  
    
    
}


function getBMI(){
    
    const data = null;
    var i ;
    var age = {};
    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    
    document.getElementById('msghealth').style.display='block';
    document.getElementById('exam').style.display='none';
    
    if(weight == null) {
        weight = document.getElementById('weight').value;
        height = document.getElementById('height').value;
    }
    var birth = document.getElementById('birthdate').value;
    
    console.log("this is birth" + birth);
    
    console.log("ypsos "+height +"  baros "+ weight);
    
   
    var now = new Date();
    var bdate = new Date(birth);
    var age = Math.abs(now-bdate);
    age = parseInt(age / (1000*3600*24*365));
    console.log("this is age" + age);

    xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                    console.log(this.responseText);
                    
                    var json = JSON.parse(this.response);
                    console.log(json);
                    document.getElementById('msghealth').innerHTML = "Your BMI is :" + json.data.bmi;
            }
            document.getElementById('msgwelcome').style.display='none';
            document.getElementById('account').style.display='none';
            //document.getElementById('docInfo').style.display='none';
            document.getElementById('health').style.display = 'block';
            
    });

    xhr.open("GET", "https://fitness-calculator.p.rapidapi.com/bmi?age="+age+"&weight="+weight+"&height="+height+"");
    xhr.setRequestHeader("x-rapidapi-host", "fitness-calculator.p.rapidapi.com");
    xhr.setRequestHeader("x-rapidapi-key", "505aa1e186msha079b12fec5c292p13cdbcjsnd29829221d55");

    xhr.send(data);

}


function getIdealWeight(){
    
    
    
    const data = null;

    const xhr = new XMLHttpRequest();
    xhr.withCredentials = true;
    document.getElementById('msgideal').style.display='block';
    
    if(height == null) {
       
        height = document.getElementById('height').value;
    }
    gender = document.getElementById('sex').value;
    
    xhr.addEventListener("readystatechange", function () {
            if (this.readyState === this.DONE) {
                    console.log(this.responseText);
                    
                    var json = JSON.parse(this.response);
                    console.log(json);
                    document.getElementById('msgideal').innerHTML = "Your ideal weight is :" +json.data.Hamwi;
            }
    });

    xhr.open("GET", "https://fitness-calculator.p.rapidapi.com/idealweight?gender="+gender.toLowerCase()+"&height="+height+"");
    xhr.setRequestHeader("x-rapidapi-host", "fitness-calculator.p.rapidapi.com");
    xhr.setRequestHeader("x-rapidapi-key", "505aa1e186msha079b12fec5c292p13cdbcjsnd29829221d55");

    xhr.send(data);
}

function addAmka(){
    
    amka = document.getElementById('amka').value;
    document.getElementById('amka1').value = amka;
    document.getElementById('exam').style.display = 'block';
    document.getElementById('health').style.display = 'none';
    document.getElementById('account').style.display = 'none';
    document.getElementById('footer').style.display = 'none';

}

function addExamUser(){
    
    var xhr = new XMLHttpRequest();
    
    document.getElementById('exam').style.display = 'block';
    
    
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('msgexam').innerHTML = "Exam Added";
           
           
        } else if (xhr.status !== 200) {
            
        }
    };
    
    var amka1 = document.getElementById('amka1').value;
    var test_date = document.getElementById("test_date").value;
    var medical_center = document.getElementById("medical_center").value;
    var vitamin_d3 = document.getElementById("vitamin_d3").value;
    
    
    var vitamin_b12 = document.getElementById("vitamin_b12").value;
    var cholesterol = document.getElementById("cholesterol").value;
    var blood_sugar = document.getElementById("blood_sugar").value;
    var iron = document.getElementById("iron").value;
  
  
    
    
    
    
    
    xhr.open('POST','addExam',true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    var data = test_date + '\n' +
            medical_center + '\n' +
            vitamin_d3 + '\n' +
            vitamin_b12 + '\n' +
            blood_sugar + '\n' +
            cholesterol +  '\n' +
            iron  +  '\n' +
            amka1;
            
    xhr.send(data);
    console.log('test_date=' + test_date 
            + '  &medical_center=' + medical_center + '  &vitamin_d3=' + vitamin_d3
            +'  &vitamin_b12=' + vitamin_b12 + '  &cholesterol=' +cholesterol + 
            +'  &blood_sugar=' + blood_sugar + '  &iron=' + iron + '  &amka1=' + amka1
            );
    
    
}

function getExam(){
    
    var xhr = new XMLHttpRequest(); 
    
    
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('msgexamget').innerHTML = "Previous Exams";
            
            var json = xhr.response;
            let obj = JSON.parse(json);
            console.log(obj);
            var length = obj.length;
            var html = [];
            for(var i = 0; i < length; i++) {
                var info = [];
                info.push("<p><h1> Exam #",i,"</h1><br>",obj[i].test_date,"<br>", obj[i].medical_center,"<br>", obj[i].vitamin_d3," Vitamin D3",
                            obj[i].vitamin_d3_level,"<br>",obj[i].vitamin_b12," Vitamin b12 ",obj[i].vitamin_b12_level, "<br>" ,
                            obj[i].cholesterol,"  Cholesterol ",obj[i].cholesterol_level,"<br>",obj[i].blood_sugar, "  Blood sugar " ,obj[i].blood_sugar_level, "<br>" ,
                            obj[i].iron, " Iron " ,obj[i].iron_level, "<br>"  ,"</p><br>");

                html.push(info.join(""));
            }
            document.getElementById('lastExams').innerHTML = html.join("");
           
           
        } else if (xhr.status !== 200) {
            
        }
    };
    
    var amka1 = document.getElementById('amka1').value;
 
     
    xhr.open('GET','addExam?amka='+amka1,true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            
    xhr.send();
    
    
    
    
}


function getUserLocation(){
    var xhr = new XMLHttpRequest(); 
    
    var username = document.getElementById('username').value;
    console.log("THIS IS USERNAME LOCATION :" +username);
    
     xhr.onload = function () {
         
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            var json = xhr.response;
            let obj = JSON.parse(json);
            console.log(json);
            userLat = obj.lat;
            userLon = obj.lon;
                                 
           getDoctorsList();
           
        }else if (xhr.status !== 200) {
            console.log("Error trying to find user's location");
        }
    };

    xhr.open('GET','getUserLoc?username='+username );
    xhr.send();
    
    
}

function getDoctorsList(){
    var xhr = new XMLHttpRequest(); 

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            var json = xhr.response;
            let obj = JSON.parse(json);
            console.log(obj);
            var length = obj.length;
            coordinates = [];
            var dis =[];
            coorlist = [];
            for(var i = 0; i < length; i++) {
                var info = [];
                info.push(obj[i].lat,"%2C", obj[i].lon);
                coordinates.push(info.join(""));
                
                
                console.log(obj[i].firstname," ",obj[i].lastname,":",distance(userLat,obj[i].lat,userLon,obj[i].lon),"<br>");

                coorlist.push(obj[i].firstname," ",obj[i].lastname,":",distance(userLat,obj[i].lat,userLon,obj[i].lon),"<br>");

            }
            
            console.log(coordinates.join("%3B"));
            
            document.getElementById('docLoc').innerHTML = coorlist.join("");
            
            getDoctorsLocation();
        }else if (xhr.status !== 200) {
        }
    };

    xhr.open('GET','getDoctors' );
    xhr.send();
}

function getDoctorsLocation(){
    
    const data = null;

        const xhr = new XMLHttpRequest();
        xhr.withCredentials = true;
        var usercoords = [userLat,userLon];
        
        console.log("userLat : "+userLat +"userLot :"+ userLon+ "coordinates : "+coordinates);
        
        xhr.addEventListener("readystatechange", function () {
                if (this.readyState === this.DONE) {
                    
                        console.log(this.response);
                        
                        
                }
        });

        xhr.open("GET", "https://trueway-matrix.p.rapidapi.com/CalculateDrivingMatrix?origins="+usercoords+"&destinations=" + coordinates.join(";"));
        xhr.setRequestHeader("x-rapidapi-host", "trueway-matrix.p.rapidapi.com");
        xhr.setRequestHeader("x-rapidapi-key", "505aa1e186msha079b12fec5c292p13cdbcjsnd29829221d55");
        xhr.send(null);
}


function distance(lat1,lat2, lon1, lon2)
    {
   
        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 =  lon1 * Math.PI / 180;
        lon2 = lon2 * Math.PI / 180;
        lat1 = lat1 * Math.PI / 180;
        lat2 = lat2 * Math.PI / 180;
   
        // Haversine formula
        let dlon = lon2 - lon1;
        let dlat = lat2 - lat1;
        let a = Math.pow(Math.sin(dlat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2)
                 * Math.pow(Math.sin(dlon / 2),2);
               
        let c = 2 * Math.asin(Math.sqrt(a));
   
        // Radius of earth in kilometers. Use 3956
        // for miles
        let r = 6371;
   
        // calculate the result
        return(c * r);
    }
 