
//Get the element with id="defaultOpen" and click on it


let registration = document.getElementById("form");
registration.addEventListener("submit", submitForm);

function submitForm(event){
    event.preventDefault();
    console.log();
    console.log(`The username was ${document.getElementById("username").value}`);
    console.log(`The pasword was ${document.getElementById("psw").value}`);
}

function printPressed(event){
    console.log(`Key pressed in username field: ${event.key}`);
    
}

//Add an event listener to the other input field
/*let pswField = document.getElementById("psw");
pswField.addEventListener("keydown", passwordPress);
*/
function passwordPressed(event){
    console.log(`Key pressed in password field: ${event.key}`);
}

function fnamePressed(event){
    console.log(`Key pressed in fname field:${event.key}`);
}

function lnamePressed(event){
    console.log(`Key pressed in lname field:${event.key}`);
}

function emailPressed(event) {
    console.log(`Key pressed in email field: ${event.key}`);
}


//if we want to do some custom logic before sending our data away, we can prevent the form 
//from doing its default action
let login = document.getElementById("form");
login.addEventListener("submit", submitForm);

function submitForm(event){
    event.preventDefault();
    console.log("We are preventing the default functionality of the form");
    console.log(`The username was ${document.getElementById("username").value}`);
    console.log(`The password was ${document.getElementById("psw").value}`);
    console.log(`The role selected is ${document.getElementById("userrole").value}`);
}



const URL = 'http://34.67.71.40:7000';

//Doing it the old fashioned way with XHR object
//For now, we will use immidiately invoked function
(()=> {

    //Step 1. Create the new XHR Object
    let xhttp = new XMLHttpRequest();

    //Step 2. Create a callback function for readystatechange
    xhttp.onreadystatechange = getData = () => {
        if(xhttp.readyState === 4 && xhttp.status === 200) {
            console.log(xhttp.responseText);
            
        }
    }

    let apiURL = `${URL}/ers_user`;

    //Step 3. Open the request
    xhttp.open("GET", apiURL);

    //Step 4. Send the request
    xhttp.send();

        
})();


// async function loadIntoTable (URL, table) {
//     const tableHead = table.querySelector("thead");
//     const tableBody = document.getElementById("tbody");
//      const response = await fetch(apiURL);
       
        let apiURL = `${URL}/ers_user`;

        async function populateResult() {
        const response = await fetch(apiURL);
        console.log(response);
       } 

       populateResult();
//     /*const {headers, rows}  = await response.json();*/

//     //clear the table
//     tableHead.innerHTML = `<tr>${tableHead}</tr>`;
//     tableBody.innerHTML = "<td></td>";

//     //Populate Headers
//     for (const headerText of headers) {
//         const headerElement = document.createElement("th");

//         headerElement.textContent = headerText;
//         tableHead.querySelector("tr").appendChild(headerElement);
//     }

// }

//oadIntoTable(`${URL}/ers_user`, document.querySelector("table"));
