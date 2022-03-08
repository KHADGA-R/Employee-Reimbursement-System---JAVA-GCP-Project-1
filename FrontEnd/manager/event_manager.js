


var selectedRow = null;

let ers_reimbursement;

let reimbContainer = document.getElementById('tbody');
console.log(reimbContainer);

function populateReimbursement(ers_reimbursement) {
    const table = document.getElementById("tbody");

    
    for(reimb of ers_reimbursement) {

        let table = document.getElementById("reimbdata").getElementsByTagName('tbody')[0];
        //let table = document.createElement('table');
        var row =  table.insertRow(table.length);
        var cell1 = row.insertCell(0);
        cell1.innerHTML = `${getType(reimb.typeId)}`;
        var cell2 = row.insertCell(1);
        cell2.innerHTML = `${reimb.amount}`;
        var cell3 = row.insertCell(2);
        cell3.innerHTML = `${reimb.description}`;
        var cell4 = row.insertCell(3);
        cell4.innerHTML = `${addUser(reimb.author)}`;
        var cell5 = row.insertCell(4);
        cell5.innerHTML = `${getDateTime(reimb.submitted)}`;
        var cell6 = row.insertCell(5);
        cell6.innerHTML = `${getStatus(reimb.statusId)}`;
        var cell7 = row.insertCell(6);
        cell7.innerHTML = `${reimb.resolver}`;
        var cell8 = row.insertCell(7);
        cell8.innerHTML = `${getDateTime(reimb.resolved)}`;
        
        
        cell4 = addUser(cell4, reimb.author);
        cell7 = addUser(cell7, reimb.resolver);
        
        console.log(table); 
        
        function addUser(cell, id) {
            fetch(`${URL}/ers_user/${id}`)
            .then(reimb => {
                if(!reimb.ok) {
                    throw new Error(`Error status: ${reimb.status}`);
                }
                return reimb.json();
            })
            .then(data => {
                console.log(data);
            
            cell.innerHTML = (data.firstName + " " + data.lastName);
              return cell; 
            })

            .catch(err => console.log(err));            

            };
            

      }


    }

    function getDateTime(timestamp) {
        if(timestamp===null){
            return "PENDING";
        };
        
        let date = new Date(timestamp);
        let month = date.getMonth() + 1;
        let day = date.getDate();
        let hour = date.getHours();
        let min = date.getMinutes();
        let sec = date.getSeconds();
        month = (month < 10 ? "0" : "") + month;
        day = (day < 10 ? "0" : "") + day;
        hour = (hour < 10 ? "0" : "") + hour;
        min = (min < 10 ? "0" : "") + min;
        sec = (sec < 10 ? "0" : "") + sec;
        let format = date.getFullYear() + "-" + month + "-" + day + " " +  hour + ":" + min;
        return format;        
        };
        


//--------------------

const URL = 'http://34.67.71.40:7000';


//Refactor the above AJAX with fesh and promises

(()=>{

    let apiUrl = `${URL}/reimbursement/all`;

    fetch(apiUrl)
    .then((res) => res.json())
    .then((data) => populateReimbursement(data));

})();

//lets look after response header
(function responseHeaders(){
    let apiUrl = `${URL}/reimbursement/all`;
    fetch(apiUrl).then((res) => console.log('Headers, ', res.headers));

})();

//We can set request headers by including an object in the fetch call

(function requestHeaders(){
    let apiUrl = `${URL}/reimbursement/all`;
    fetch(apiUrl, {
        headers: {
            'Content-Type': 'application/json'
       }
    })
    .then((res) => res.json())
    .then((data) => console.log(data));
})();


function getStatus(statusId) {
    switch(statusId){
        case 1: return "PENDING";
        case 2: return "ACCEPTED";
        case 3: return "REJECTED";
        default: break;
    }
}

function getType(typeId) {
    switch(typeId) {
        case 1: return "LODGING";
        case 2: return "TRAVEL";
        case 3: return "FOOD";
        case 4: return "OTHER";
        default: break;
    }
}

let ers_user;

function populateUser(ers_user) {
};

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


managerpage.onclick = function(e){
    console.log(e.target);
    console.log(managerpage);
    console.log(this);
}