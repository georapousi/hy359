'use strict';



/* global getLat, getLon */

var par;


function radioChecked(par) {
    
            
    const rbs = document.querySelectorAll('input[name="'+par+'"]');
    let selectedValue;
    for (const rb of rbs) {
        if (rb.checked) {
            console.log(rb.value);
            selectedValue = rb.value;
            break;
        }
    }
    return selectedValue;
}
 function CheckAmka(){
     
    var xhr = new XMLHttpRequest(); 
    var type = 0;
    var amka = document.getElementById("amka").value;
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('msgamka').innerHTML = 'New';
        } else if (xhr.status !== 200) {
            
                
            document.getElementById('msgamka').innerHTML = 'Already exists';
        }
    };
    xhr.open('GET','GetUser?' + "amka="+ amka +'&type=' + type);
    xhr.send();
 }
 
  function CheckUsername(){
      
    var xhr = new XMLHttpRequest();
    var type = 1;
    var username = document.getElementById("username").value;
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('msgusername').innerHTML ='New';
           
        } else if (xhr.status !== 200) {
            
                
            document.getElementById('msgusername').innerHTML = 'Already exists';
        }
    };
    xhr.open('GET','GetUser?' + "username="+ username +'&type=' + type);
    xhr.send();
 }
 
  function CheckEmail(){
      
    var xhr = new XMLHttpRequest();
    var type = 2;
    var email = document.getElementById("email").value;
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('msgemail').innerHTML = 'New';
           
        } else if (xhr.status !== 200) {
            
                
            document.getElementById('msgemail').innerHTML = 'Already exists';
        }
    };
    xhr.open('GET','GetUser?' + 'email='+ email +'&type=' + type);
    xhr.send();
 }
 
 
function RegisterPOST() {
    
    
    var xhr = new XMLHttpRequest();
    
    var user;
    var specialty;
    
    
    if(document.getElementById('radioUser').checked) {
        user = document.getElementById('radioUser').value;
        specialty = " ";
        doctor_info = " ";
    }else if(document.getElementById('radioDoctor').checked) {
        
        user = document.getElementById('radioDoctor').value;
        
        if(document.getElementById('radioPatho').checked) {
            specialty = document.getElementById('radioPatho').value;
        }else if(document.getElementById('radioPract').checked) {
            specialty = document.getElementById('radioPract').value;
            
        }
    }
    
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            if(document.getElementById('radioDoctor').checked){
                
            document.getElementById('sucResponse').innerHTML = "You have been registered, but you will need to be certified by Admin";
            document.getElementById('sucResponse').style.display = "block";
         
            } else if(document.getElementById('sucResponse').style.display = "none"){
                document.getElementById('sucResponse').style.display = "block";	
            }  
           
           document.getElementById('failedResponse').style.display = "none";
           
           location.href = 'login.html';
           
        } else if (xhr.status !== 200) {
            document.getElementById('sucResponse').style.display = "none";
                
            document.getElementById('failedResponse').innerHTML = 'Request failed. Returned status of ' + xhr.status ;
        }
    };
    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    
    
    var firstname = document.getElementById("firstname").value;
    var lastname = document.getElementById("lastname").value;
    var doctor_info = document.getElementById("doctor_info").value;
    var birthdate = document.getElementById("birthdate").value;
    
    var amka = document.getElementById("amka").value;
    var country = document.getElementById("country").value;
    var address = document.getElementById("address").value;
    var city = document.getElementById("city").value;
    var telephone = document.getElementById("telephone").value;
    var height = document.getElementById("height").value;
    var weight = document.getElementById("weight").value;
    
    var bloodtype = document.getElementById("bloodtype").value;
    
   
    var gender ;
    var blooddonor;  
     
  
            
    
    if(document.getElementById('radioFem').checked) {
       gender = document.getElementById('radioFem').value;
    }else if(document.getElementById('radioMal').checked) {
       gender = document.getElementById('radioMal').value;
    }else{
        gender = document.getElementById('radioOther').value;
    }
    
   
    if(document.getElementById('radioBloodY').checked) {
       blooddonor = 1;
    }else if(document.getElementById('radioBloodN').checked) {
       blooddonor = 0;
    }
    
    
    
    
    
    xhr.open('POST','GetUser',true);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    
    xhr.send('firstname=' + firstname + '&lastname=' + lastname + '&telephone=' + telephone 
            +'&username=' + username + '&country=' + country + '&city=' + city
            +'&address=' + address + '&email=' + email + '&password=' + password
            +'&user=' + user + '&specialty=' + specialty + '&doctor_info=' + doctor_info
            +'&birthdate=' + birthdate + '&gender=' + gender + '&lat=' + getLat + '&lon=' + getLon
            +'&amka=' + amka + '&height=' + height + '&weight=' + weight
            +'&blooddonor=' + blooddonor + '&bloodtype=' + bloodtype);
    
    
    console.log('firstname=' + firstname +"\n"+ 'lastname=' + lastname +"\n"+ 'telephone=' + telephone 
            +"\n"+ 'username=' + username +"\n"+ 'country=' + country +"\n"+ 'city=' + city
            +"\n"+'address=' + address +"\n"+ 'email=' + email +"\n"+ 'password=' + password
            +"\n"+'user=' + user +"\n"+ 'specialty=' + specialty +"\n"+ 'doctor_info=' + doctor_info
            +"\n"+'birthdate=' + birthdate +"\n"+ 'gender=' + gender +"\n"+ 'lat=' + getLat +"\n"+ 'lon=' + getLon
            +"\n"+'amka=' + amka +"\n"+ 'height=' + height +"\n"+ 'weight=' + weight
            +"\n"+'blooddonor=' + blooddonor +"\n"+ 'bloodtype=' + bloodtype);

    
}
