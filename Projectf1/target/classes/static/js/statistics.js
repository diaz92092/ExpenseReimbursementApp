window.onload = function(){
  getMost(),
  getMean(),
  getLeast()
}

async function getMost(){
  let url = 'http://localhost:9000/most';
  let response_body = await fetch(url);
  let mostValues = await response_body.json();

    console.log(mostValues);
     console.log(mostValues[0][1]);
      console.log(mostValues[0].value);
     document.getElementById('most').innerHTML += `<tr>
                                                       <td>${mostValues[0][0]}</td>
                                                        <td>$${mostValues[0][1].toFixed(2)}</td>
                                                        </tr>`;


  return mostValues;

}


async function getLeast(){
  let url = 'http://localhost:9000/least';
  let response_body = await fetch(url);
  let leastValues = await response_body.json();

    console.log(leastValues);
     console.log(leastValues[0][1]);
     console.log("hi")
      console.log(leastValues[0].value);
     document.getElementById('least').innerHTML += `<tr>
                                                       <td>${leastValues[0][0]}</td>
                                                        <td>$${leastValues[0][1].toFixed(2)}</td>
                                                        </tr>`;


  return leastValues;

}


async function getMean(){
  let url = 'http://localhost:9000/mean';
  let response_body = await fetch(url);
  let meanValues = await response_body.json();

    console.log(meanValues);
     console.log(meanValues[0]);
     document.getElementById('mean').innerHTML += `<tr>
                                                       <td>$${meanValues[0].toFixed(2)}</td> </tr>`;


  return meanValues;

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

let logoutE = document.getElementById("logout");
logoutE.addEventListener("click", logout);
//async function getLeast(){
//  let url = 'http://localhost:9000/least';
//  let response_body = await fetch(url);
//  let leastValues = await response_body.json();
//console.log(leastValues);
//  return leastValues;
//}