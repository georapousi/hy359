


function CheckLogin(){
     
    var xhr = new XMLHttpRequest(); 
    
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            
            document.getElementById('msglogin').innerHTML = 'NonExist Password or Username';
        } else if (xhr.status !== 200) {
            
                
            document.getElementById('msglogin').innerHTML = 'Correct credentials';
        }
    };
    xhr.open('GET','GetUser?' + "username="+ username +'&password=' + password);
    xhr.send();
 }

