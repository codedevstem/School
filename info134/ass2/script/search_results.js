/* Her kan dere implementere en søkefunksjon. For eksempel:
function search_for_X() {
}
*/

/* Her kan dere implementere en display function som viser resulatetene av søket. For eksempel:
function display_X() {
}
*/
function display(resultArray, objectToAppend) {
	resultArray.forEach(movie => {
		
		/*list item*/
		listItem = document.createElement("li");
		listItem.classList.add("movieInstance");
		
		listLink = document.createElement("a");
		listLink.href = "show_movie.html?id=" + movie.id;
		
		linkExtraSection = document.createElement("section");
		linkExtraSection.classList.add("extraInfo");
		
		extraSectionTitle = document.createElement("p");
		extraSectionTitle.appendChild(document.createTextNode(movie.otitle));
		for(movie)
		extraSectionRating = document.createElement("p");
		extraSectionTitle.appendChild(document.createTextNode(movie))
		linkImage = document.createElement("img");
		linkImage.src = "https://nelson.uib.no/o/"+ parseInt(movie.id/1000) + "/" + movie.id + ".jpg";
		linkImage.alt = "Cover image of " + movie.otitle;
	
		listLink.appendChild(linkExtraSection);
		listLink.appendChild(linkImage);
	
		listItem.appendChild(listLink);

		objectToAppend.appendChild(listItem);
	})
	/*Example entery for a movie returned from search
			<a href="../movies/intouchables.html">
				<img src="../images/intouchables/cover.jpg" alt="Intouchables sitt cover">
				<section class="extraInfo">
					<p>Intouchables</p>
					<p>Stjerner: 5.0</p>
				</section>
				<section class="addButtonsSection">
					<button class="addToList" type="submit">Lei Film</button>
					<button class="addToWish" type="submit">Ønsker</button>
			</section>
			</a>
		 */
}
function searchForTitle(search_results, query_params) {
	const resultArray = [];
	for(movie_id in search_results){
		let movie = search_results[movie_id];
		if(movie.otitle.toLowerCase().includes(query_params.toLowerCase())){
			resultArray.push(movie);
		}
	}
	return resultArray;
}


window.onload = function() {
	query_params = get_query_string_parameters();
	const resultList = document.querySelector('#res_list');
	search_results = movies_object;
	let filteredList;
	
	if (query_params.film_title) {
        film_title = document.getElementById("film_title");
		filteredList = searchForTitle(search_results, query_params.film_title);
		
    }
	
	if (query_params.actor) {
        actor = document.getElementById("actor");
		actor.innerHTML = query_params.actor;
    }
	
	if (query_params.director) {
		director = document.getElementById("director");
		director.innerHTML = query_params.director;
    }
	
	if (query_params.genre) {
        genre = document.getElementById("genre");
		genre.innerHTML = query_params.genre;
    }
	
	if (query_params.country) {
        country = document.getElementById("country");
		country.innerHTML = query_params.country;
    }
	if(filteredList.length < 1) console.log('Empty search result');
	display(filteredList, resultList);
	//Her kan dere for eksempel kalle en (display) funksjon som viser søkeresultater 
}