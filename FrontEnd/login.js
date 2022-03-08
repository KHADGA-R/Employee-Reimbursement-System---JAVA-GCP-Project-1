let apiURL = 'http://34.67.71.40:7000/login';

let login = document.getElementById("form");
login.addEventListener("submit", submitForm);

function submitForm(event){
    event.preventDefault();

    var data = new FormData();
    data.append("username", document.getElementById("username").value);
    data.append("password", document.getElementById("password").value);

    fetch(apiURL,{
      method: 'POST',
      body: data      
    })
    .then(response => response.json())
    .then(data =>{
      console.log(data);
      if(storageAvailable('sessionStorage')){
        window.sessionStorage.setItem("id", `${data.id}`);
        window.sessionStorage.setItem("role", `${data.roleId}`);
      }else{
        //i guess theres no session storage for the user
      }
      
      switch(`${data.roleId}`){
        case '1': location.assign('employee/employee-home.html'); break;
        case '2': location.assign('manager/home.html'); break;
        default: break;// should throw error
      }
    })
    .catch((error)=>{
      console.error('Error: ',error);
      invalidResponse();
      login.reset();
    })
    
}

let errMsg = document.getElementById('login-err-msg-holder');

function invalidResponse(){
  errMsg.style.display = 'block';
}

function storageAvailable(type) {
    var storage;
    try {
        storage = window[type];
        var x = '__storage_test__';
        storage.setItem(x, x);
        storage.removeItem(x);
        return true;
    }
    catch(e) {
        return e instanceof DOMException && (
            // everything except Firefox
            e.code === 22 ||
            // Firefox
            e.code === 1014 ||
            // test name field too, because code might not be present
            // everything except Firefox
            e.name === 'QuotaExceededError' ||
            // Firefox
            e.name === 'NS_ERROR_DOM_QUOTA_REACHED') &&
            // acknowledge QuotaExceededError only if there's something already stored
            (storage && storage.length !== 0);
    }
}