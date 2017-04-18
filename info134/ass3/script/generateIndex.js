let reviews;
let numberOfMovies;
let windowWidth;

let newMoviesIndex = 0;
let recentMoviesIndex = 0;
let suggestionMoviesIndex = 0;

let newMoviesArray = [];
let recentMoviesArray = [];
let suggestionMoviesArray = [];

let movieRatingArray = [];
makeMovieArray();
pushToArray(newMoviesArray);
pushToArray(recentMoviesArray);
pushToArray(suggestionMoviesArray);
window.onload = function() {

document.getElementById('newBack').style.opacity = "0";
document.getElementById('recentBack').style.opacity = "0";
document.getElementById('suggestedBack').style.opacity = "0";
reviews = reviews_object;
calculateAvgRatingAll();
windowWidth = document.body.clientWidth;
chooseNumberOfMovies();
bindArrowClicks();


document.getElementById('submitRating').addEventListener("click", function(){
	fillInRatingSection();
}, false);
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

// Filling in new movies in the document
appendList(newMoviesArray, 'newMovies', newMoviesIndex, numberOfMovies);
// Filter out movies that are unlikely to have a picture... not sure how to fix this properly ((Todo?))
filterMovies(recentMoviesArray);
filterMovies(suggestionMoviesArray);


//Shuffeling the movies so they are in random order((This should be based on recent movies, but that is not yet available to me))
recentMoviesArray = shuffle(recentMoviesArray);

//Sorting the movies so they are in sorted in order of rating
suggestionMoviesArray.sort(function(a,b){
	let aRating;
	let bRating;
	movieRatingArray.forEach(pair => {
		if(pair["movieId"] == a.id) aRating = pair["rating"]; 
		if(pair["movieId"] == b.id) bRating = pair["rating"]; 
	});
	
	return bRating - aRating;
}); 


// Removing all the other element than the seven first in the list
// Filling in movies in the sections
appendList(recentMoviesArray, 'recentMovies', recentMoviesIndex, numberOfMovies)
appendList(suggestionMoviesArray, 'suggestedMovies', suggestionMoviesIndex, numberOfMovies)
fillInRatingSection();
}

function fillInRatingSection(){
	let movie = randomPick(movieArray);
	const rateMovie = document.getElementById('rateMovie');

	let itemLink = document.createElement("a");
	itemLink.href = "pages/movieDetails.html?id=" + movie.id;
	let linkImage = document.createElement("img");
	linkImage.src = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg";
	linkImage.alt = "Cover image of " + movie.otitle;

	itemLink.appendChild(linkImage);

	rateMovie.replaceChild(itemLink, rateMovie.querySelector('a'));

	let rateMovieTitle = document.createElement("h4");
	rateMovieTitle.innerHTML = movie.otitle;
	let ratingInfo = document.getElementById("ratingInfo");
	ratingInfo.replaceChild(rateMovieTitle, document.querySelector('h4'));
}
function getRatingOfMovie(movie){
	
	return 0;
}
/**
	* 
	* @param {Array} Array of movies 
	*	@returns {Array} Shuffeled Array of movies
	*/
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
/**
	* 
	* @param {Array} array 
	*/
function randomPick(array) {
	return array[Math.floor(Math.random() * array.length)];
}
function calculateAvgRatingAll(){
	let movieratingPairs;
	let totalRating = 0;
	let numberOfRating = 0;
	let isFound;
	movieArray.forEach(movie =>{
		movieratingPairs = {};	
		isFound = false;
		for(let review in reviews){
			if(movie.id == review){
				isFound = true;
				for(let user in reviews[review]) {
					totalRating += reviews[review][user].rating;
					numberOfRating++;
				}
				break;
			}
		}
		if(!isFound){
			movieratingPairs["movieId"] = movie.id;
			movieratingPairs["rating"] = 0;
		}else{
			movieratingPairs["movieId"] = movie.id;
			movieratingPairs["rating"] = (totalRating / numberOfRating);
		}
		totalRating = 0;
		numberOfRating = 0;
		movieRatingArray.push(movieratingPairs); 
	});
}
/**
	*	@description Fills in <ul> list in the document with movies
	* 
	* @param {Array} array | list of filtered movies 
	* @param {String} listName | name of tag that the list shall be inserted into
	* @param {Int} fromIndex | starting index of movies to append
	* @param {Int} numberOfElements | number of elements to append to listName
	*
	* Comment | all movies have picture and CORS is not enabled so i can't check the url.
	*
	*/		
function appendList(array, listName, fromIndex, numberOfElements){
	let list = document.getElementById(listName);
	for(let i = fromIndex; i < fromIndex+numberOfElements; i++){
		if(array[i] != null){
			let listItem = document.createElement("li");
			listItem.id = array[i].id;
			let itemLink = document.createElement("a");
			itemLink.href = "pages/movieDetails.html?id=" + array[i].id;
			let linkImage = document.createElement("img");
			linkImage.src = "https://nelson.uib.no/o/"+ parseInt(array[i].id/1000) + "/" + array[i].id + ".jpg";
			linkImage.alt = "Cover image of " + array[i].otitle;
			linkImage.addEventListener("error", replacePicture);
			let imageText = document.createElement("p");
			imageText.classList.add("imageText");
			imageText.innerHTML = array[i].otitle;
			itemLink.appendChild(linkImage);
			itemLink.appendChild(imageText);
			listItem.appendChild(itemLink);
			list.appendChild(listItem);
		}
	}
}
function replacePicture() {
	this.src="images/notFound.png";
}
function pushToArray(array){
	movieArray.forEach(movie =>{
		array.push(movie)
	});
}
function filterMovies(array){
	for(let i = array.length-1; i>=0; i--){
		let movie = array[i];
		let image = new Image("https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg"); 
		image.onerror = function() {
			array.splice(array.indexOf(movie), 1);
		}

	}
}
function changeWindowValues() {
	windowWidth = document.body.clientWidth;
	chooseNumberOfMovies();	
	clearSections();
	appendList(newMoviesArray, 'newMovies', newMoviesIndex, numberOfMovies);
	appendList(recentMoviesArray, 'recentMovies', recentMoviesIndex, numberOfMovies)
	appendList(suggestionMoviesArray, 'suggestedMovies', suggestionMoviesIndex, numberOfMovies)
}
function chooseNumberOfMovies() {
 if(windowWidth < 840) {
		numberOfMovies = Math.floor(windowWidth/160);
	} else {
		numberOfMovies = Math.floor(windowWidth/200);
	}
}
function clearSections() {
	document.getElementById('newMovies').innerHTML = '';
	document.getElementById('recentMovies').innerHTML = '';
	document.getElementById('suggestedMovies').innerHTML = '';

}
function increase(id) {
	if(id == "newMovies"){
		newMoviesIndex = increaseIndex(newMoviesIndex,newMoviesArray, 'newMovies');
		document.getElementById('newBack').style.opacity = "1";
	}else if(id == "recentMovies"){
		recentMoviesIndex = increaseIndex(recentMoviesIndex,recentMoviesArray, 'recentMovies');
		document.getElementById('recentBack').style.opacity = "1";
	}else if(id == "suggestedMovies"){
		suggestionMoviesIndex =	increaseIndex(suggestionMoviesIndex,suggestionMoviesArray, 'suggestedMovies');
		document.getElementById('suggestedBack').style.opacity = "1";
	}
}

function increaseIndex(indexCounter, array, idName){
	indexCounter = indexCounter + numberOfMovies;
	if(indexCounter > array.length-1) indexCounter = array.length-numberOfMovies;	
	document.getElementById(idName).innerHTML = '';
	appendList(array, idName, indexCounter, numberOfMovies)
	return indexCounter;
}

function decrease(id) {
	if(id == "newMovies"){
		if(newMoviesIndex - numberOfMovies  == 0  || newMoviesIndex == 0) document.getElementById('newBack').style.opacity = "0";
		else document.getElementById('newBack').style.opacity = "1";
		newMoviesIndex =	decreaseIndex(newMoviesIndex, newMoviesArray, 'newMovies');
	}else if(id == "recentMovies"){
		if(recentMoviesIndex - numberOfMovies == 0 || recentMoviesIndex == 0) document.getElementById('recentBack').style.opacity = "0";
		else document.getElementById('recentBack').style.opacity = "1";
		recentMoviesIndex =	decreaseIndex(recentMoviesIndex, recentMoviesArray, 'recentMovies');
	}else if(id == "suggestedMovies"){
		if(suggestionMoviesIndex - numberOfMovies  == 0  || suggestionMoviesIndex == 0) document.getElementById('suggestedBack').style.opacity = "0";
		else document.getElementById('suggestedBack').style.opacity = "1";
			suggestionMoviesIndex =	decreaseIndex(suggestionMoviesIndex, suggestionMoviesArray, 'suggestedMovies');
	}
}

function decreaseIndex(indexCounter, array, idName){
	indexCounter -= numberOfMovies;
	if(indexCounter < 0) indexCounter = 0;		
	console.log(indexCounter);
	document.getElementById(idName).innerHTML = '';
	appendList(array, idName, indexCounter, numberOfMovies);
	return indexCounter;
}

function bindArrowClicks() {
	document.getElementById('newBack').addEventListener("click", function(){decrease("newMovies")}, false);
	document.getElementById('newForward').addEventListener("click", function(){increase("newMovies")}, false);
	document.getElementById('recentBack').addEventListener("click", function(){decrease("recentMovies")}, false);
	document.getElementById('recentForward').addEventListener("click", function(){increase("recentMovies")}, false);
	document.getElementById('suggestedBack').addEventListener("click", function(){decrease("suggestedMovies")}, false);
	document.getElementById('suggestedForward').addEventListener("click", function(){increase("suggestedMovies")}, false);

}
