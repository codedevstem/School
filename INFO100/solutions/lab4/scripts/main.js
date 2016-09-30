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
