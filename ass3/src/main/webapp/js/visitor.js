/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function getDoctors(){
    var xhr = new XMLHttpRequest(); 

    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            var json = xhr.response;
            let obj = JSON.parse(json);
            console.log(obj);
            var length = obj.length;
            var html = [];
            for(var i = 0; i < length; i++) {
                var info = [];
                info.push("<p>",obj[i].firstname,"<br>", obj[i].lastname,"<br>", obj[i].specialty,"<br>",obj[i].telephone, "</p><br>");
                html.push(info.join(""));
            }
            document.getElementById('showDoc').innerHTML = html.join("");
           
        }else if (xhr.status !== 200) {
        }
    };

    xhr.open('GET','getDoctors' );
    xhr.send();
}

function getSimUsers(){
    var xhr = new XMLHttpRequest(); 

    xhr.onload = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {

            var json = xhr.response;
            let obj = JSON.parse(json);
            console.log(obj);
            var length = obj.length;
            var html = [];
            for(var i = 0; i < length; i++) {
                var info = [];
                info.push("<p>",obj[i].firstname,"<br>", obj[i].lastname,"<br>","<br>",obj[i].telephone, "</p><br>");
                html.push(info.join(""));
            }
            document.getElementById('showDoc').innerHTML = html.join("");
                
            }else if (xhr.status !== 200) {

            }
    };
    xhr.open('GET','getUsers' );
    xhr.send();
}