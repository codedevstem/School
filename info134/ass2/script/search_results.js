/**
	* @description | Appends and displays filtered list of movies.
	* @param {Array} resultArray | Filtered list of movies to display.
	* @param {Node} objectToAppend | <ul> tag to append movie nodes to.
	*/
function display(resultArray, objectToAppend) {
	reviews = reviews_object;
	resultArray.forEach(movie => {

		listItem = document.createElement("li");
		listItem.classList.add("movieInstance");

		listLink = document.createElement("a");
		listLink.href = "movieDetails.html?id=" + movie.id;

		linkExtraSection = document.createElement("section");
		linkExtraSection.classList.add("extraInfo");

		linkImage = document.createElement("img");
		linkImage.src = "https://nelson.uib.no/o/" + parseInt(movie.id / 1000) + "/" + movie.id + ".jpg";
		linkImage.alt = "Cover image of " + movie.otitle;
		linkImage.addEventListener("error", replacePicture);
		
		extraSectionTitle = document.createElement("p");
		extraSectionTitle.appendChild(document.createTextNode(movie.otitle));
		linkExtraSection.appendChild(extraSectionTitle);
		let totalRating = 0;
		let numberOfRating = 0;
		//Gets the rating if any for the current movie
		for (review in reviews) {
			if (review == movie.id) {
				for (user in reviews[review]) {
					totalRating += reviews[review][user].rating;
					numberOfRating++;
				}
			}
		}
		extraSectionRating = document.createElement("p");
		if (numberOfRating > 0) {
			let avgRating = totalRating / numberOfRating;
			extraSectionRating.appendChild(document.createTextNode(`Stjerner: ${avgRating.toFixed(1)}`));
		} else {
			extraSectionRating.appendChild(document.createTextNode(`Denne filmen er ikke ratet enda`));
		}
		linkExtraSection.appendChild(extraSectionRating);

		listLink.appendChild(linkImage);
		listLink.appendChild(linkExtraSection);

		//Create Button section for each card.
		let buttonSection = document.createElement("section");
		buttonSection.classList.add("buttonSection");
		let addToListButton = document.createElement("button");
		addToListButton.classList.add("addToList");
		addToListButton.type = "submit";
		addToListButton.innerHTML = "Add to cart";
		let addToWishButton = document.createElement("button");
		addToWishButton.classList.add("addToWish");
		addToWishButton.type = "submit";
		addToWishButton.innerHTML = "Add to list";

		buttonSection.appendChild(addToListButton);
		buttonSection.appendChild(addToWishButton);

		listItem.appendChild(listLink);
		listItem.appendChild(buttonSection);

		objectToAppend.appendChild(listItem);
	});
}

function replacePicture() {
	this.style.display = "none";
}


window.onload = function () {
	query_params = get_query_string_parameters();
	const resultList = document.querySelector('#res_list');
	makeMovieArray();
	
	let filteredList = movieArray;
	seachTitle = document.getElementById("titleSearch");
	seachTitle.placeholder = 'Siste søk: ' + query_params.film_title;
	searchTermTitle = document.getElementById("film_title");
	searchTermTitle.value = query_params.film_title;
	if (query_params.film_title != null) {
		filteredList = generalFilter(filteredList, query_params.film_title, "otitle");
	}

	actor = document.getElementById("actor");
	actor.value = query_params.actor;
	if (query_params.actor != null) {
		filteredList = generalFilter(filteredList, query_params.actor, "folk");
	}

	director = document.getElementById("director");
	director.value = query_params.director;
	if (query_params.director != null) {
		filteredList = generalFilter(filteredList, query_params.director, "dir");

	}

	genre = document.getElementById("genre");
	genre.value = query_params.genre;
	if (query_params.genre != null) {
		filteredList = generalFilter(filteredList, query_params.genre, "genre");
	}

	country = document.getElementById("country");
	country.value = query_params.country;
	if (query_params.country != null) {
		filteredList = generalFilter(filteredList, query_params.country, "country");

	}
	if (filteredList.length < 1) console.log('Empty search result');
	if(filteredList.length > 100) {
		let tooManyResults = document.createElement("p");
		tooManyResults.innerHTML = "Too many search results. Narrow your search";
		resultList.appendChild(tooManyResults);
	}else {
		display(filteredList, resultList);
	}
}
/**
	* @description | filters out the movies not containing the 
	* @param {Array} movies | MovieArray to be filtered
	* @param {SearchTerm} params | Search terms extracted from the string
	* @param {String} filterValue | Value to get from the database and compare to.
	*/
function generalFilter(movies, params, filterValue) {
	// Check if it is genre that is beeing filtered
	if(filterValue == "genre") {
		// Loop through
		for (let i = movies.length - 1; i >= 0; i--) {
			let movieNotFound = true;
			// Check if movie har a genre.
			for(genre in genres_object){
				if(movies[i].id == genre) {
					movieNotFound = false;
				}
			}
			// If movie has no genre than remove.
			if (movieNotFound){
					movies.splice(movies.indexOf(movies[i]), 1);			
			}
		}
		// Loop again
		for (let i = movies.length - 1; i >= 0; i--) {
			let movie = movies[i];
			// Subloop in genres
			for(genre in genres_object){
				// Find correct movie
				if(movie.id == genre){
					// Filter 
					if(!(genres_object[genre].toString().toLowerCase().includes(params.toLowerCase()))) {
						movies.splice(movies.indexOf(movie), 1);
					}
				}
			}
		}
		// Return movies
		return movies;
	}
	for (let i = movies.length - 1; i >= 0; i--) {
		let movie = movies[i];
		if (movie[filterValue] != null){
			if (!(movie[filterValue].toLowerCase().includes(params.toLowerCase()))) {
				movies.splice(movies.indexOf(movie), 1);
			}
		} else {
			movies.splice(movies.indexOf(movie), 1);
		}
	}
	return movies;
}
