
        
// When the user clicks anywhere outside of the approval form, close it
window.onclick = function(event) {
  if (event.target == updatestatus) {
    updatestatus.style.display = "none";
  }
}


function printPressed(event){
    console.log(`Key pressed in approval remarks field: ${event.key}`);  
}



let updatestatus = document.getElementById("update-form");
updatestatus.addEventListener("submit", submitForm);

function submitForm(event){
    event.preventDefault();
    console.log("We are preventing the default functionality of the form");
    console.log(`The updateStatus was ${document.getElementById("updatestatus").value}`);
       
}


const URL = "http://34.67.71.40:7000";

let uid = window.sessionStorage.getItem("id");

function submitForm(event){
    event.preventDefault();

    let statusId = document.getElementById('updatestatus').value;
    
    let reimbursementObj = {
        id:null,
        amount:null,
        submitted:null,
        resolved:null,
        description:null,
        author:null,
        resolver:null,
        statusId,
        typeId:null
    }

    fetch(`${URL}/reimbursement/submit`,{
        method: 'put',
        body: JSON.stringify(reimbursementObj)
    }).then(response => response.json())
    .then(data => {
        console.log(data);
        //send back to employee-reimbursement to see that the new reimbursement request as been created
    })
    .catch((error)=>{
        console.error('Error: ',error);
    })
}
   console.log(updatestatus);