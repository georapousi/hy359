/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//calls all functions to get users and doctors when admin logs in
window.onload = function(){
    getSimUsers();
    getDoctors();
    getCertDoctors();
}

//fetches simple users from database
function getSimUsers(){
    var xhr = new XMLHttpRequest(); 

    xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {

                var json = xhr.response;
                console.log(json);
                let obj = JSON.parse(json);
                printUsersForDelete(obj);
            }else if (xhr.status !== 200) {

            }
    };
    xhr.open('GET','userGet' );
    xhr.send();    
}

//prints simple users for admin to interact with
function printUsersForDelete(object){
    if(typeof(object) == 'object'){
        document.getElementById('userdel').innerHTML += '<ul>';
        console.log(object.length);
        for(var i in object){
            console.log(object[i]);
document.getElementById('userdel').innerHTML += '<li>' + "<div id=u"+i+">Username: " 
                    + object[i].username + "</div> Birthdate: " + object[i].birthdate +
                     "<br><button onclick='deleteUser(u"+i+")'>Delete</button></li>";            
             //printUsersForDelete(object[i].username);
            console.log(i);
        }
    }
}
//deletes user on click
function deleteUser(id){
    console.log("--->" + id);
    var xhr = new XMLHttpRequest(); 
    let newid = id.toString();
    var username = id.innerHTML.split(" ")[1];
    console.log(username);
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           refreshUser();
        } else if (xhr.status !== 200) {
            
        }
    };
    xhr.open('DELETE','DeleteUser?' + "username="+ username);
    xhr.send();
}

//refreshes div when a user is deleted
function refreshUser(){
    document.getElementById("userdel").innerHTML = "";
    getSimUsers();
}

//fetches doctors from database
function getDoctors(){
    var xhr = new XMLHttpRequest(); 

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            var json = xhr.response;
            let obj = JSON.parse(json);
            printDoctorsForDelete(obj);
        }else if (xhr.status !== 200) {
        }
    };

    xhr.open('GET','getDoctors' );
    xhr.send();    
}

//prints doctors for admin to interact with
function printDoctorsForDelete(object){
    if(typeof(object) == 'object'){
        document.getElementById('listdel').innerHTML += '<ul>';
        console.log(object.length);
        for(var i in object){
            console.log(object[i]);
            document.getElementById('listdel').innerHTML += '<li>' + "<div id=d"+i+">Username: " 
                    + object[i].username + "</div> AMKA: " + object[i].amka +
                     "<br><button onclick='deleteDoctor(d"+i+")'>Delete</button></li>";
            printDoctorsForDelete(object[i].username);
            console.log(i);
        }
        document.getElementById('listdel').innerHTML += '</ul>';

    }
}
//deletes doctor on click
function deleteDoctor(id){
    console.log(id);
    var xhr = new XMLHttpRequest(); 
    let newid = id.toString();
    var username =id.innerHTML.split(" ")[1];
    console.log(username);
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           refreshDocs();
        } else if (xhr.status !== 200) {
            
        }
    };
    xhr.open('DELETE','DeleteDoctor?' + "username="+ username);
    xhr.send();
}
//refreshes doctors div on click
function refreshDocs(){
    document.getElementById("listdel").innerHTML = "";
    getDoctors();
}

//fetches doctors from database
function getCertDoctors(){
    var xhr = new XMLHttpRequest(); 

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            var json = xhr.response;
            let obj = JSON.parse(json);
            printDoctorsForCertify(obj);
        }else if (xhr.status !== 200) {
        }
    };

    xhr.open('GET','getDoctors' );
    xhr.send();
}
//prints doctors for admin to certify
function printDoctorsForCertify(object){
    if(typeof(object) == 'object'){
        document.getElementById('certdoc').innerHTML += '<ul>';
        console.log(object.length);
        for(var i in object){
            console.log(object[i]);
            document.getElementById('certdoc').innerHTML += '<li>' + "<p id="+i+">Username: " 
                    + object[i].username + "</p> Certified: " + object[i].certified +
                     "<br><button onclick='certifyDoctor("+i+")'>Certify</button></li>";            
             //printDoctorsForCertify(object[i].username);
            console.log(i);
        }
    }
}

//certifies doctor
function certifyDoctor(id){
    console.log(id);
    var xhr = new XMLHttpRequest(); 
    let newid = id.toString();
    var username = document.getElementById(id).innerHTML.split(" ")[1];
    console.log(username);
     
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
           refreshCert();
        } else if (xhr.status !== 200) {
            
        }
    };
    xhr.open('POST','DeleteDoctor?' + "username="+ username);
    xhr.send();
}

//refreshes certified doctors div on click
function refreshCert(){
    document.getElementById("certdoc").innerHTML = "";
    getCertDoctors();
}