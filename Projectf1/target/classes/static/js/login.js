"use strict";


let submitButton = document.getElementById("login");
let employee_id = document.getElementById("employee-id");
let password = document.getElementById("input-password");
let form = document.querySelector("form");



async function login() {
  let data = {
    employee_id: employee_id.value,
    password: password.value,
  };

  console.log(data);

  const result = await fetch("http://localhost:9000/login", {
    method: "POST",
    mode: "no-cors",
    credentials: "include",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(data),
  }).then(response => {
    if (response.status === 200){
      return response.json();
    } else if (response.status === 401) {
      displayInvalidLogin();
    }
  })

  console.log(result)
  if(result[0].roleId === 1) {
    window.location.href = "./manager.html";
  } else if(result[0].roleId === 2) {
    window.location.href = "./employee.html";
  }

}



submitButton.addEventListener("click", login);



//async function login() {
//  let data = {
//    employee_id: employee_id.value,
//    password: password.value,
//  };
//
//  console.log(data);
//
//  const result = await fetch("http://localhost:9000/login", {
//    method: "POST",
//    mode: "no-cors",
//    credentials: "include",
//    headers: {
//      "Content-Type": "application/json",
//    },
//    body: JSON.stringify(data),
//  }).then(response => {
//    if (response.status === 200){
//      return response;
//    } else if (response.status === 401) {
//      console.log("invalid login");
//    }
//  })
//
//
//console.log(result)
//console.log(result[0].roleId)
//  if(result[0].roleId === 1) {
//    window.location.href = "./manager.html";
//  } else if(result[0].roleId === 2) {
//    window.location.href = "./employee.html";
//  }
//
//}
//
//submitButton.addEventListener("click", login);
//
//
