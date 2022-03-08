let uid = window.sessionStorage.getItem("id");
const URL = 'http://34.67.71.40:7000';
var selectedRow = null;
let ers_reimbursement;
let reimbContainer = document.getElementById('tbody');
let table = document.getElementById("reimbdata").getElementsByTagName('tbody')[0];

function populateReimbursement(ers_reimbursement) {
    const table = document.getElementById("tbody");

    
    for(reimb of ers_reimbursement) {

        var row =  table.insertRow();
        var cell0 = row.insertCell();
        cell0.innerHTML = `${reimb.id}`
        var cell1 = row.insertCell();
        cell1.innerHTML = `${getType(reimb.typeId)}`;
        var cell2 = row.insertCell();
        cell2.innerHTML = `${reimb.amount}`;
        var cell3 = row.insertCell();
        cell3.innerHTML = `${reimb.description}`;
        var cell4 = row.insertCell();
        cell4 = addUser(cell4, reimb.author);
        var cell5 = row.insertCell();
        cell5.innerHTML = `${getStatus(reimb.statusId)}`;
        var cell6 = row.insertCell();
        cell6.innerHTML = `${getDateTime(reimb.submitted)}`;
        var cell7 = row.insertCell();
        cell7.innerHTML = `${getDateTime(reimb.resolved)}`;
        
        function addUser(cell, id) {
            fetch(`${URL}/ers_user/${id}`)
            .then(reimb => {
                if(!reimb.ok) {
                    throw new Error(`Error status: ${reimb.status}`);
                }
                return reimb.json();
            })
            .then(data => {
            
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
    

(()=>{

    let apiUrl = `${URL}/reimbursement/pending`;

    fetch(apiUrl)
    .then((res) => res.json())
    .then((data) => populateReimbursement(data));

})();

//lets look after response header
(function responseHeaders(){
    let apiUrl = `${URL}/reimbursement/pending`;
    fetch(apiUrl).then((res) => console.log('Headers, ', res.headers));

})();

//We can set request headers by including an object in the fetch call

(function requestHeaders(){
    let apiUrl = `${URL}/reimbursement/pending`;
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

let update = document.getElementById('update-form');
update.addEventListener("submit",updateStatus)

function updateStatus(event){
    event.preventDefault();

    let resolver = 2;//changed to uid when connected with the rest of the pages
    let statusId = document.getElementById('reimb-status').value;
    let id = document.getElementById('reimb-id').value;

    let reimbursementObj = {
        id,
        amount:"0",
        submitted:"0",
        resolved:"0",
        description:"0",
        author:"0",
        resolver,
        statusId,
        typeId:"0"
    };

    console.log(reimbursementObj);

    fetch(`${URL}/reimbursement/update`, {
        method: 'put',
        mode: 'cors',
        body: JSON.stringify(reimbursementObj)
    })
    .then(data => {
        location.reload();
        //reload the page
    })
    .catch((error)=>{
        console.error('Error: ',error);
    })
}