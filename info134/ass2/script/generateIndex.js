let reviews = reviews_object;
let numberOfMovies;
let newMoviesIndex = 0;
let recentMoviesIndex = 0;
let suggestionMoviesIndex = 0;
let newMoviesArray = [];
let recentMoviesArray = [];
let suggestionMoviesArray = [];

makeArray();
pushToArray(newMoviesArray);
pushToArray(recentMoviesArray);
pushToArray(suggestionMoviesArray);

window.onload = function() {
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
fillInSection(newMoviesArray, 'newMovies', newMoviesIndex, numberOfMovies);
// Filter out movies that are unlikely to have a picture... not sure how to fix this properly ((Todo?))
filterMovies(recentMoviesArray);
filterMovies(suggestionMoviesArray);


//Shuffeling the movies so they are in random order((This should be based on recent movies, but that is not yet available to me))
recentMoviesArray = shuffle(recentMoviesArray);

//Sorting the movies so they are in sorted in order of rating
suggestionMoviesArray.sort(function(a,b){
	let aRating = getRatingOfMovie(a);
	let bRating = getRatingOfMovie(b);
	return bRating - aRating
}); 

// Removing all the other element than the seven first in the list
// Filling in movies in the sections
fillInSection(recentMoviesArray, 'recentMovies', recentMoviesIndex, numberOfMovies)
fillInSection(suggestionMoviesArray, 'suggestedMovies', suggestionMoviesIndex, numberOfMovies)
fillInRatingSection();
}

function fillInRatingSection(){
	let movie = randomPick(movieArray);
	const rateMovie = document.getElementById('rateMovie');

	let itemLink = document.createElement("a");
	itemLink.href = "pages/show_movie.html?id=" + movie.id;
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
function getRatingOfMovie(movie){
		let totalRating = 0;
			let numberOfRating = 0;
			for(review in reviews){
			if(review == movie.id){
				for(user in reviews[review]) {
					totalRating += reviews[review][user].rating;
					numberOfRating++;
				}
			}
		}
		return totalRating/numberOfRating;
	}
//Fills in a section on the index page based on numbers
function fillInSection(array, listName, fromIndex, numberOfElements){
	let list = document.getElementById(listName);
	for(let i = fromIndex; i < fromIndex+numberOfElements; i++){
		if(array[i] != null){
			let listItem = document.createElement("li");
			let itemLink = document.createElement("a");
			itemLink.href = "pages/show_movie.html?id=" + array[i].id;
			let linkImage = document.createElement("img");
			linkImage.src = "https://nelson.uib.no/o/"+ parseInt(array[i].id/1000) + "/" + array[i].id + ".jpg";
			linkImage.alt = "Cover image of " + array[i].otitle;
			// needed because not all movies have picture and CORS is not enabled so i can't check the url... Please fix...
			linkImage.onerror = function() {
			}
			itemLink.appendChild(linkImage);
			listItem.appendChild(itemLink);
			list.appendChild(listItem);
		}
	}
}
function pushToArray(array){
	movieArray.forEach(movie =>{
		array.push(movie)
	});
}
function filterMovies(array){
for(let i = array.length-1; i>=0; i--){
		let movie = array[i];
		let image = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg"; 
		image.onerror = function() {
			array.splice(array.indexOf(movie), 1);
		}

	}
}
function changeWindowValues() {
	windowWidth = document.body.clientWidth 
	chooseNumberOfMovies();	
	clearSections();
	fillInSection(newMoviesArray, 'newMovies', newMoviesIndex, numberOfMovies);
	fillInSection(recentMoviesArray, 'recentMovies', recentMoviesIndex, numberOfMovies)
	fillInSection(suggestionMoviesArray, 'suggestedMovies', suggestionMoviesIndex, numberOfMovies)
}
function chooseNumberOfMovies() {
	if(windowWidth > 1410){
		numberOfMovies = 11;
	} else if(windowWidth < 840) {
		numberOfMovies = Math.floor(windowWidth/115);
	} else {
		numberOfMovies = Math.floor(windowWidth/150);
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
	}else if(id == "recentMovies"){
		recentMoviesIndex = increaseIndex(recentMoviesIndex,recentMoviesArray, 'recentMovies');
	}else if(id == "suggestedMovies"){
		suggestionMoviesIndex =	increaseIndex(suggestionMoviesIndex,suggestionMoviesArray, 'suggestedMovies');
	}
}

function increaseIndex(indexCounter, array, idName){
	indexCounter = indexCounter + numberOfMovies;
	if(indexCounter > array.length-1) indexCounter = array.length-numberOfMovies;	
	document.getElementById(idName).innerHTML = '';
	fillInSection(array, idName, indexCounter, numberOfMovies)
	return indexCounter;
}

function decrease(id) {
	if(id == "newMovies"){
			decreaseIndex(newMoviesIndex, newMoviesArray, 'newMovies');
	}else if(id== "recentMovies"){
			decreaseIndex(recentMoviesIndex, recentMoviesArray, 'recentMovies');
	}else if(id == "suggestedMovies"){
				decreaseIndex(suggestionMoviesIndex, suggestionMoviesArray, 'suggestedMovies');
	}
}

function decreaseIndex(indexCounter, array, idName){
	indexCounter -= numberOfMovies;
	if(indexCounter < 0) indexCounter = 0;		
	document.getElementById(idName).innerHTML = '';
	fillInSection(array, idName, indexCounter, numberOfMovies)
}

function bindArrowClicks() {
	document.getElementById('newBack').addEventListener("click", function(){decrease("newMovies")}, false);
	document.getElementById('newForward').addEventListener("click", function(){increase("newMovies")}, false);
	document.getElementById('recentBack').addEventListener("click", function(){decrease("recentMovies")}, false);
	document.getElementById('recentForward').addEventListener("click", function(){increase("recentMovies")}, false);
	document.getElementById('suggestedBack').addEventListener("click", function(){decrease("suggestedMovies")}, false);
	document.getElementById('suggestedForward').addEventListener("click", function(){increase("suggestedMovies")}, false);

}
