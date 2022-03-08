
//Get the element with id="defaultOpen" and click on it

const URL = 'http://34.67.71.40:7000';

//let uid = window.sessionStorage.getItem("id");
let ers_user = document.getElementById("form");
ers_user.addEventListener("submit", submitForm);

function submitForm(event){
    event.preventDefault();

    let username = document.getElementById('username').value;
    let password = document.getElementById('psw').value;
    let firstName = document.getElementById('fname').value;
    let lastName = document.getElementById('lname').value;
    let email = document.getElementById('email').value;
    let roleId = document.getElementById('userrole').value;

    let userObj= {
        id:null,
        username,
        password,
        firstName,
        lastName,
        email,
        roleId,
        
    };

    fetch(`${URL}/ers_user`,{
        method: 'post',
        body: JSON.stringify(userObj)
    }).then(response => response.json())
    .then(data => {
        console.log(data);
        location.assign();
        //send back to employee-reimbursement to see that the new reimbursement request as been created
    })
    .catch((error)=>{
        //console.error('Error: ',error);
    })

       
}
console.log(userObj);


function getRoleId(roleId) {
    switch(roleId){
        case 1: return "EMPLOYEES";
        case 2: return "MANAGER";
        default: break;
    }
}
