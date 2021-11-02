window.onload = function () {
  getAllReimbursements();
};

async function getAllReimbursements() {
  const reimbursements = await fetch("http://localhost:9000/reimbursements", {
    method: "GET",
    credentials: "include",
    mode: "no-cors",
  }).then((response) => {
    if (response.status === 400) {
      window.location.href = "/";
    }
    return response.json();
  });

  console.log(reimbursements);

  reimbursements.forEach((reimRequest) => {
    let resultsReimRequestElement = document.querySelector("reimbursements");

    let d = new Date(reimRequest.expense_date);
    let formatSubmitted = d.toLocaleDateString();



    let resultsReimRequestsElement = document.querySelector(
      "#manager-reimbursements"
    );
    resultsReimRequestsElement.innerHTML += `<tr>
       <td>${reimRequest.reimbursement_id}</td>
       <td>${reimRequest.employee_id}</td>
       <td>${reimRequest.amount}</td>
       <td>${reimRequest.description}</td>
       <td>${formatSubmitted}</td>
       <td>${reimRequest.message}</td>
       <td>${reimRequest.status}</td>
     </tr>`;

    return reimbursements;
  });
}

function logout() {
  fetch("http://localhost:9000/logout", {
    method: "POST",
    mode: "no-cors",
    credentials: "include",
  }).then((response) => {
    if (response.status === 200) {
      // return response.status;
      window.location.href = "../index.html";
    } else if (response.status === 401) {
      return "Please try again!";
    }
  }).then((response => { window.location.href = "./index.html"}));
}

function approve() {
  let reimbursement_id = document.getElementById("input-id");
  let status = document.getElementById("input-status");
  let message = document.getElementById("input-message");


  let data = {
    status: status.value,
    message: message.value
  };

  console.log("data: " + data);

  fetch(`http://localhost:9000/reimbursements/` + reimbursement_id.value, {
    method: "PUT",
    credentials: "include",
    body: JSON.stringify(data),
  }).then((response) => {
    if (response.status === 200) {
      return response;


    } else if (response.status === 401) {
      console.log("Unable to process request. Please try again");
    }

  }).then((response => {window.location.reload()}));

}

let mLogout = document.getElementById("logout");
mLogout.addEventListener("click", logout);

const selectStatus = document.getElementById("status");
selectStatus.addEventListener("change", (event) => {
  let id = event.target.value;

  if (id == "") {
    document.getElementById("manager-reimbursements").innerHTML = "";
    getAllReimbursements();
  } else {
    document.getElementById("manager-reimbursements").innerHTML = "";
    filterStatusById(id);
  }
});

document.getElementById("approve").addEventListener("click", approve);
//submitButton.addEventListener("click", approve);

//submitButton.addEventListener("click", approve);
//let approvalButton = document.getElementById("approval-button");
//approvalButton.addEventListener("click", () => {
//  approveReimbursementRequest();


  //resets form
  // document.getElementById("approval-request-form").reset();
//});
