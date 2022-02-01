'use strict';


function CheckLogin(){
     
    var xhr = new XMLHttpRequest(); 
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    console.log(username+ " " + password);
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            console.log(xhr.response);          
            
            document.getElementById('sign-form').style.display = 'none';
            document.getElementById('logout-btn').style.display = 'block';
            document.getElementById('navbar').style.display = 'block';
            document.getElementById('msgwelcome').innerHTML = 'Welcome';
            
            
            
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
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('account').style.display = 'block ';
            var data = xhr.responseText.split(' ');
            console.log(data);
                
            
            
        }else if (xhr.status !== 200) {
            
            document.getElementById('msglogin').innerHTML = 'NonExist Password or Username';
        }
    };
    xhr.open('POST','userDetails' );
    xhr.send('username='+ username +'&password=' + password);
    
}