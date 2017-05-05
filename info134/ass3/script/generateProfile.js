/* Author: 102 */

makeMovieArray();
window.onload = function() {
    const loanedMoviesList = document.getElementById('loanedMoviesList');
    const wantMoviesList = document.getElementById('wantMoviesList');
	for(let i = 0; i < 10; i++){
    	fillInRatingSection(loanedMoviesList);
		fillInRatingSection(wantMoviesList);

	}

}

function fillInRatingSection(list){
	let movie = randomPick(movieArray);
    let listItem = document.createElement("li");
	listItem.id = movie.id;
	let itemLink = document.createElement("a");
	itemLink.href = "../pages/movieDetails.html?id=" + movie.id;
	let linkImage = document.createElement("img");
	linkImage.src = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg";
	linkImage.alt = "Cover image of " + movie.otitle;
	linkImage.addEventListener("error", replacePicture);
	let imageText = document.createElement("p");
	imageText.classList.add("imageText");
	imageText.innerHTML = movie.otitle;
	itemLink.appendChild(linkImage);
	itemLink.appendChild(imageText);
	listItem.appendChild(itemLink);
	list.appendChild(listItem);

}
function replacePicture() {
	this.src="../images/notFound.png";
}
function randomPick(array) {
	return array[Math.floor(Math.random() * array.length)];
}
