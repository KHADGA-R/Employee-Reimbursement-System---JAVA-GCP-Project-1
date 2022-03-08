
        
// When the user clicks anywhere outside of the approval form, close it
window.onclick = function(event) {
  if (event.target == updatestatus) {
    updatestatus.style.display = "none";
  }
}


function printPressed(event){
    console.log(`Key pressed in approval remarks field: ${event.key}`);  
}




function submitForm(event){
    event.preventDefault();
    console.log("We are preventing the default functionality of the form");
    console.log(`The updateStatus was ${document.getElementById("updatestatus").value}`);
       
}
//----------

const URL = "http://34.67.71.40:7000";

let uid = window.sessionStorage.getItem("id");

// let userProfile = document.getElementById("user-profile");

// (async () => {
//     fetch(`${URL}/ers_user/${uid}`)
//     .then(response => response.json())
//     .then(data =>{
//         user = data;
//         setProfile(user);
//     })
//     .catch((error)=>{
//         console.error('Error: ',error);
//     })
// })();


function updateStatus(event){
    event.preventDefault();
    
    let resolver = uid;
    let statusId = 2;
    

    let reimbursementObj = {
        id:23,
        amount:null,
        submitted:null,
        resolved:null,
        description:null,
        author:null,
        resolver,
        statusId,
        typeId:null
    };

    console.log(reimbursementObj);

    fetch(`${URL}/reimbursement/update`,{
        method: 'put',
        body: JSON.stringify(reimbursementObj)
    }).then(response => response.json())
    .then(data => {
        console.log(data);
        //reload the page
    })
    .catch((error)=>{
        console.error('Error: ',error);
    })
}




