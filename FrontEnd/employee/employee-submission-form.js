const URL = "http://34.67.71.40:7000";

let uid = window.sessionStorage.getItem("id");
let reimbursement =  document.getElementById('reimbursement-form');
reimbursement.addEventListener("submit", submitForm);

function confimSubmission(){
    if(window.confirm('Submit Reimbursement?')){
        return true;
    }else{
        return false;
    }
}

function submitForm(event){
    event.preventDefault();
    if(!confimSubmission()){
        return;
    }

    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let author = uid;
    let statusId = 1;
    let typeId = document.getElementById('type').value;

    let reimbursementObj = {
        id:null,
        amount,
        submitted:null,
        resolved:null,
        description,
        author,
        resolver:null,
        statusId:null,
        typeId
    }

    fetch(`${URL}/reimbursement/submit`,{
        method: 'post',
        body: JSON.stringify(reimbursementObj)
    }).then(response => response.json())
    .then(data => {
        console.log(data);
        location.assign('employee-reimbursement.html');
    })
    .catch((error)=>{
        console.error('Error: ',error);
    })
}