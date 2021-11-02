window.onload = function() {
    //getReimbursementsByEmployeeId(sessionStorage.getItem("2"));
    getAllReimbursements();
};

async function getAllReimbursements() {
    const reimbursements = await fetch("http://localhost:9000/reimbursements/2", {
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
            "#employee-reimbursements"
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

let aEmployee_id = document.getElementById("input-id");
let aAmount = document.getElementById("input-amount");
let aExpense_date = document.getElementById("input-date");
let aDescription = document.getElementById("input-description");

console.log("1")
function addReimbursementRequest() {
    let data = {
        employee_id: aEmployee_id.value,
        amount: aAmount.value,
        expense_date: aExpense_date.value,
        description: aDescription.value,
    };

    console.log(data);

    fetch("http://localhost:9000/reimbursements", {
        method: "POST",
        mode: "no-cors",
        credentials: "include",
        body: JSON.stringify(data),
    }).then((response) => {
        if (response.status === 200) {
            return response;
        } else if (response.status === 401) {
            console.log("Unable to process request. Please try again");
        }
    }).then((response => {
        window.location.href = "./employee.html"
    }));
   }

console.log("2")


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
        }).then((response => {
            window.location.href = "./index.html"
        }));
    }

   document.getElementById("logout").addEventListener("click", logout);


//    document.getElementById("addReimbursementRequest").addEventListener("click", addReimbursementRequest);

/// fix this