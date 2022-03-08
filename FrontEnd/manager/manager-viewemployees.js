
var selectedRow = null;

let ers_user;

let userContainer = document.getElementById('tbody');
console.log(userContainer);

function populateUser(ers_user) {
    const table = document.getElementById("tbody");

    for(u of ers_user) {

        let table = document.getElementById("userdata").getElementsByTagName('tbody')[0];
        //let table = document.createElement('table');
        var row =  table.insertRow(table.length);
        var cell1 = row.insertCell(0);
        cell1.innerHTML = `${(u.id)}`;
        var cell2 = row.insertCell(1);
        cell2.innerHTML = `${u.username}`;
        var cell3 = row.insertCell(2);
        cell3.innerHTML = `${u.firstName}`;
        var cell4 = row.insertCell(3);
        cell4.innerHTML = `${u.lastName}`;
        var cell5 = row.insertCell(4);
        cell5.innerHTML = `${u.email}`;
             
             
        console.log(table); 
        
        function addUser(cell, id) {
            fetch(`${URL}/ers_user/${id}`)
            .then(u => {
                if(!u.ok) {
                    throw new Error(`Error status: ${u.status}`);
                }
                return u.json();
            })
            .then(data => {
                console.log(data);
            
             return cell; 
            })

            .catch(err => console.log(err));            

            }; 
      }

    }  

//--------------------

const URL = 'http://34.67.71.40:7000';

//Refactor the above AJAX with fesh and promises

(()=>{

    let apiUrl = `${URL}/ers_user`;

    fetch(apiUrl)
    .then((res) => res.json())
    .then((data) => populateUser(data));

})();

//lets look after response header
(function responseHeaders(){
    let apiUrl = `${URL}/ers_user`;
    fetch(apiUrl).then((res) => console.log('Headers, ', res.headers));

})();

//We can set request headers by including an object in the fetch call

(function requestHeaders(){
    let apiUrl = `${URL}/ers_user`;
    fetch(apiUrl, {
        headers: {
            'Content-Type': 'application/json'
       }
    })
    .then((res) => res.json())
    .then((data) => console.log(data));
})();


