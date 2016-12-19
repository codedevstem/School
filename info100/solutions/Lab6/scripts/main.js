var footerText = `
  <p>This page is created by Kristian Os</p>
  <p><a href="mailto:kos003@student.uib.no?subject=feedback"> Send me a mail to give feedback.</a>
  <a href="#" onclick="changeText()">Click her for norsk informasjon.</a></p>
`;
function changeText() {
  var temp = document.getElementById('footer').innerHTML;
  document.getElementById('footer').innerHTML = footerText;
  footerText = temp;
}
function showHide(id) {
  var elem = document.getElementById(id);
  if (elem.style.display === "block") {
    elem.style.display = "none";
  } else {
    elem.style.display = "block";
  }
}

function openFastRegPage(url) {
  window.open(url);
}

function generateJobs(xml) {

  var i;
  var xmlDoc = xml.responseXML;
  var jobs="<h1>Resultatsiden for jobber som er evaluert.</h1>";
  var x = xmlDoc.getElementsByTagName("JOB");
  for (i = 0; i <x.length; i++) {
    jobs += "<section class='result'><h2>" +
    x[i].getElementsByTagName("POSITION")[0].childNodes[0].nodeValue +
    " <button type='button' onclick='showHide(`job" + (i+1) +"Info`)'>Vis mer info</button></h2>" +
    "<span id='job" + (i+1) +"Info'>" +
    "<p>Arbeidsplass: " + x[i].getElementsByTagName("WORKPLACE")[0].childNodes[0].nodeValue +
    "</p><p>Arbeidsgiver: " + x[i].getElementsByTagName("EMPLOYEER")[0].childNodes[0].nodeValue +
    "</p><p>Registrert: " + x[i].getElementsByTagName("DATEREGISTERED")[0].childNodes[0].nodeValue +
    "</p><p>Søknadsfrist: " + x[i].getElementsByTagName("DEADLINE")[0].childNodes[0].nodeValue +
    "</p><p>Karakter: " + Math.floor((Math.random() * 6) + 1) +
    "</p></span></section>";
  }
  document.getElementById("allJobs").innerHTML = jobs;
}
