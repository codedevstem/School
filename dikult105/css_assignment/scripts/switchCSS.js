function switchCSS(first, second) {
  var styleLink = document.getElementById("replaceLink");
  var href = styleLink.getAttribute("href");
  newHref = href == first ? second : first;
  styleLink.setAttribute("href", newHref);
}  
