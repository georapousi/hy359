/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function updateValues(){
     var xhr = new XMLHttpRequest(); 

    var username = document.getElementById("username").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var telephone = document.getElementById("telephone").value;
    var height = document.getElementById("height").value;
    var weight = document.getElementById("weight").value;

    xhr.onload = function () {

        if (xhr.readyState === 4 && xhr.status === 200) {
            console.log("update was successful");
        }else{
            
        }
    }

    xhr.open('POST','updateDoctor?' + "username=" + username + "&email=" + email+
    "&password="+password+"&telephone="+telephone+"&height="+height+"&weight="+weight);
    xhr.send();
}
