const URL = "http://34.67.71.40:7000";

//include a check here if there is session information;
// if there is session information then the user is logged in;else is not logged in
let uid = window.sessionStorage.getItem("id");

let userProfile = document.getElementById("user-profile");

(async () => {
    fetch(`${URL}/ers_user/${uid}`)
    .then(response => response.json())
    .then(data =>{
        user = data;
        setProfile(user);
    })
    .catch((error)=>{
        console.error('Error: ',error);
    })
})();

let passToggle = document.getElementById('floating-password');

function toggleView(){
    var getting
}

let update = document.getElementById('form-profile');
update.addEventListener("submit", updateUser);

function confirmUpdate(){
    var txt;
    if(window.confirm("Confirm Changes?")){
        return true;
    } else {
        return false;
    }
}

function updateUser(event){
    event.preventDefault();
    if(!confirmUpdate()){
        return;
    }
    
    let id = uid;
    let username = document.getElementById('floating-username').value;
    let password = document.getElementById('floating-password').value;
    let firstName = document.getElementById('floating-first').value;
    let lastName =  document.getElementById('floating-last').value;
    let email = document.getElementById('floating-email').value;

    let userObj = {
        id,
        username,
        password,
        firstName,
        lastName,
        email,
        roleId: "1"
    };

    console.log(userObj);

    fetch(`${URL}/ers_user/${uid}`,{
        method: 'put',
        body: JSON.stringify(userObj)
    }).then(response => response.json())
    .then(data => {
        location.reload();
    })
    .catch((error)=>{
        console.error('Error: ',error);
    })
}

function getRole(roleId){
    switch(roleId){
        case 1: return "EMPLOYEE";
        case 2: return "MANAGER";
        default: break;
    }
}

function setProfile(user){
let firstField = document.getElementById('floating-first').value = `${user.firstName}`;
let lastField = document.getElementById('floating-last').value = `${user.lastName}`;
let userField = document.getElementById('floating-username').value = `${user.username}`;
let emailField = document.getElementById('floating-email').value = `${user.email}`;
let passField = document.getElementById('floating-password').value = `${user.password}`;
let roleField = document.getElementById('floating-role').value = `${getRole(user.roleId)}`;
}
