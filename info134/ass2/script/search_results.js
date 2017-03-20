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



window.onload = function () {
	query_params = get_query_string_parameters();
	const resultList = document.querySelector('#res_list');
	makeMovieArray();
	let filteredList = movieArray;

	if (query_params.film_title) {
		seachTitle = document.getElementById("titleSearch");
		seachTitle.placeholder = 'Siste søk: ' + query_params.film_title;
		searchTermTitle = document.getElementById("film_title");
		searchTermTitle.value = query_params.film_title;
		filteredList = filterTitle(filteredList, query_params.film_title);
	}

	if (query_params.actor) {
		actor = document.getElementById("actor");
		actor.value = query_params.actor;
		filteredList = filterActor(filteredList, query_params.actor);
	}

	if (query_params.director) {
		director = document.getElementById("director");
		director.value = query_params.director;
		filteredList = filterDirector(filteredList, query_params.director);

	}

	if (query_params.genre) {
		genre = document.getElementById("genre");
		genre.value = query_params.genre;
		filteredList = filterGenre(filteredList, query_params.genre);

	}

	if (query_params.country) {
		country = document.getElementById("country");
		country.value = query_params.country;
		filteredList = filterCountry(filteredList, query_params.country);

	}
	if (filteredList.length < 1) console.log('Empty search result');
	display(filteredList, resultList);
	//Her kan dere for eksempel kalle en (display) funksjon som viser søkeresultater 
}
/**
	* @description | filters out the movies not containing the 
	* @param {Array} movies | MovieArray to be filtered
	* @param {SearchTerm} params | Search terms extracted from the string
	*/
function filterTitle(movies, params) {
	for (let i = movies.length - 1; i >= 0; i--) {
		let movie = movies[i];
		if (!(movie.otitle.toLowerCase().includes(params.toLowerCase()))) {
			movies.splice(movies.indexOf(movie), 1);
		}
	}
	return movies;
}

function filterActor(movies, params) {
	for (let i = movies.length - 1; i >= 0; i--) {
		if (movies[i].folk == null){
				movies.splice(movies.indexOf(movies[i]), 1);			
		}
	}
	for (let i = movies.length - 1; i >= 0; i--) {
		if (movies[i].folk != null) {
			let movie = movies[i];
			if (!(movie.folk.toLowerCase().includes(params.toLowerCase()))) {
				movies.splice(movies.indexOf(movie), 1);
			}
		} 
	}
	return movies;
}

function filterDirector(movies, params) {
	for (let i = movies.length - 1; i >= 0; i--) {
		let movie = movies[i];
		if (!(movie.dir.toLowerCase().includes(params.toLowerCase()))) {
			movies.splice(movies.indexOf(movie), 1);
		}
	}
	return movies;
}

function filterGenre(movies, params) {
	for (let i = movies.length - 1; i >= 0; i--) {
		let movieNotFound = true;
		for(genre in genres_object){
			if(movies[i].id == genre) {
				movieNotFound = false;
			}
		}
		if (movieNotFound){
				movies.splice(movies.indexOf(movies[i]), 1);			
		}
	}
	for (let i = movies.length - 1; i >= 0; i--) {
		let movie = movies[i];
		for(genre in genres_object){
			if(movie.id == genre){
				if(!(genres_object[genre].toString().toLowerCase().includes(params.toLowerCase()))) {
					movies.splice(movies.indexOf(movie), 1);
				}
			}
		}
	}
	return movies;
}
function filterCountry(movies, params) {
	for (let i = movies.length - 1; i >= 0; i--) {
		let movie = movies[i];
		if (!(movie.country.toLowerCase().includes(params.toLowerCase()))) {
			movies.splice(movies.indexOf(movie), 1);
		}
	}
	return movies;
}