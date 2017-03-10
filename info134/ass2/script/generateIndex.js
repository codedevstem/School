window.onload = function() {
makeArray();
let newMoviesArray = [];
movieArray.forEach(movie =>{
	newMoviesArray.push(movie)
})
let recentMoviesArray = [];

movieArray.forEach(movie =>{
	recentMoviesArray.push(movie)
})
let suggestionsMoviesArray = [];
movieArray.forEach(movie =>{
	suggestionsMoviesArray.push(movie)
})
let rateAMovie = randomPick(movieArray);
// Filtering and sorting new movies array
for(let i = newMoviesArray.length-1; i>=0; i--){
		let movie = newMoviesArray[i];
		if(movie.reg_date == null) newMoviesArray.splice(newMoviesArray.indexOf(movie), 1);
	}
newMoviesArray.sort(function(a,b){
	let aDate = a.reg_date.substring(0, 4) + '' + a.reg_date.substring(5,7) + '' + a.reg_date.substring(8,10);
	let bDate = b.reg_date.substring(0, 4) + '' + b.reg_date.substring(5,7) + '' + b.reg_date.substring(8,10);
	return bDate - aDate
});

newMoviesArray.splice(7);
// Filling in new movies in the document
const newMovies = document.getElementById('newMovies');

newMoviesArray.forEach(movie => {
	let listItem = document.createElement("li");
	let itemLink = document.createElement("a");
	itemLink.href = "pages/show_movie.html?id=" + movie.id;
	let linkImage = document.createElement("img");
	linkImage.src = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg";
	linkImage.alt = "Cover image of " + movie.otitle;
	itemLink.appendChild(linkImage);
	listItem.appendChild(itemLink);
	newMovies.appendChild(listItem);
});
for(let i = recentMoviesArray.length-1; i>=0; i--){
		let movie = recentMoviesArray[i];
		if(movie.reg_date == "") recentMoviesArray.splice(recentMoviesArray.indexOf(movie), 1);
	}
// Filling in movies in the recentmovies section
recentMoviesArray = shuffle(recentMoviesArray);
const recentMovies = document.getElementById('recentMovies');
recentMoviesArray.splice(7);
recentMoviesArray.forEach(movie =>{
	let listItem = document.createElement("li");
	let itemLink = document.createElement("a");
	itemLink.href = "pages/show_movie.html?id=" + movie.id;
	let linkImage = document.createElement("img");
	linkImage.src = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg";
	linkImage.alt = "Cover image of " + movie.otitle;
	itemLink.appendChild(linkImage);
	listItem.appendChild(itemLink);
	recentMovies.appendChild(listItem);
});
for(let i = suggestionsMoviesArray.length-1; i>=0; i--){
		let movie = suggestionsMoviesArray[i];
		if(movie.reg_date == "") suggestionsMoviesArray.splice(suggestionsMoviesArray.indexOf(movie), 1);
	}
suggestionsMoviesArray = shuffle(suggestionsMoviesArray);

const suggestionsMovies = document.getElementById('suggestedMovies');
suggestionsMoviesArray.splice(7);
suggestionsMoviesArray.forEach(movie =>{
	let listItem = document.createElement("li");
	let itemLink = document.createElement("a");
	itemLink.href = "pages/show_movie.html?id=" + movie.id;
	let linkImage = document.createElement("img");
	linkImage.src = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg";
	linkImage.alt = "Cover image of " + movie.otitle;
	itemLink.appendChild(linkImage);
	listItem.appendChild(itemLink);
	suggestionsMovies.appendChild(listItem);
});
const rateMovie = document.getElementById('rateMovie');


let itemLink = document.createElement("a");
itemLink.href = "pages/show_movie.html?id=" + rateAMovie.id;
let linkImage = document.createElement("img");
linkImage.src = "https://nelson.uib.no/o/"+ parseInt(rateAMovie.id/1000) + "/" + rateAMovie.id + ".jpg";
linkImage.alt = "Cover image of " + rateAMovie.otitle;
itemLink.appendChild(linkImage);

rateMovie.replaceChild(itemLink, rateMovie.querySelector('a'));

let rateMovieTitle = document.createElement("h4");
rateMovieTitle.innerHTML = rateAMovie.otitle;
let ratingInfo = document.getElementById("ratingInfo");
ratingInfo.replaceChild(rateMovieTitle, document.querySelector('h4'));
}


// Shuffle an array
function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;
		// go through the array.
  while (0 !== currentIndex) {

    // Pick a element
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    //Swap section
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}
function randomPick(array) {
	return array[Math.floor(Math.random() * array.length)];
}