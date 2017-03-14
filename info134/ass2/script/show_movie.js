let movie;
let movieGenres;
let movieAvgRating;
function panic(message) {
    // window.history.back();
    alert(message);
}

window.onload = function() {
    query_params = get_query_string_parameters();
    if (!query_params.id) {
        panic("No id given");
        return;
    }else{
        makeMovieArray();
        getMovieIdAndGenre();
        
        getElemId('oTitle').innerHTML = movie.otitle;
        if(movie.etitle != null && movie.etitle != movie.otitle){
            getElemId('eTitle').innerHTML = movie.etitle;
        }
        if(movie.ntitle != null && movie.ntitle != movie.otitle){
            getElemId('nTitle').innerHTML = movie.ntitle;
        }
        createButtonsSection(getElemId("infoSection"));
        createDetailedInfoSection(getElemId("detailedInfo"));
        if(movieAvgRating == null){
            getElemId("currentRating").innerHTML = "Denne filmen har ikke blitt ratet enda";
        }else{
            getElemId("currentRating").innerHTML = "Andre har gitt denne filmen: " + movieAvgRating.toFixed(1);
        }
        getElemId("submitRating").addEventListener("click", function(){giveRatingToMovie()}, false);
        createMoreInfoSection(getElemId("moreInfo"));
        let youtubeElem = document.createElement("iframe");
        youtubeElem.height = "315";
        youtubeElem.src = "https://www.youtube.com/embed/"+movie.youtube_trailer_id;
        youtubeElem.allowFullscreen = true;
        getElemId("trailer").appendChild(youtubeElem);
        getElemId("images").appendChild(createImageElem("b"));
        getElemId("images").appendChild(createImageElem("c"));
    }
    
};
function createImageElem(letter){
    image = document.createElement("img");
    image.src = "https://nelson.uib.no/o/" + parseInt(movie.id / 1000) + "/" + movie.id + letter + ".jpg";
    image.alt = "Cover image of " + movie.otitle;
    image.onerror = ()=>{image.style.display = "none"};
    return image;
}
function createMoreInfoSection(parent) {
    parent.appendChild(createInfoElem("infoHeader", "Handling"));
    parent.appendChild(createInfoElem("explanation", movie.description));
    parent.appendChild(createInfoElem("infoHeader", "Produsert"));
    parent.appendChild(createInfoElem("explanation", movie.country));
    parent.appendChild(createInfoElem("infoHeader", "regissører"));
    parent.appendChild(createInfoElem("explanation", movie.dir));
}
function getMovieIdAndGenre() {
    movieArray.forEach(movies => {
        if(movies.id == query_params.id) {
            movie = movies;
            for(genreMovie in genres_object){
                if(genreMovie == query_params.id){
                    movieGenres = genres_object[genreMovie].toString().replace(/\,/g, ", ");
                }
            }
            let totalRating = 0;
            let numberOfRating = 0;
            for (review in reviews_object) {
                if (review == movie.id) {
                    for (user in reviews_object[review]) {
                        totalRating += reviews_object[review][user].rating;
                        numberOfRating++;
                    }
                }
		    }
            if(numberOfRating > 0){
                movieAvgRating = totalRating / numberOfRating
            } else {
                movieAvgRating = null;
            }

        }
    });
}
function toggleChopList() {
    console.log("added to take list");
}
function toggleWishList(){
    console.log("added to wish list!");
}
function getElemId(id) {
    return document.getElementById(id);
}
function createInfoElem(className, content) {
    let newElem = document.createElement("li");
    newElem.classList.add(className);
    newElem.innerHTML = content;
    return newElem;
}
function createDetailedInfoSection(parent) {
    parent.appendChild(createInfoElem("infoHeader", "Genre"));
    parent.appendChild(createInfoElem("explanation", movieGenres));
    parent.appendChild(createInfoElem("infoHeader", "Lengde"));
    parent.appendChild(createInfoElem("explanation", movie.length + " minutter"));
    parent.appendChild(createInfoElem("infoHeader", "Skuespillere"));
    // Special Case to create the list of actors.
    let actorList = document.createElement("ul");
    actorList.classList.add("explanation");
    movie.folk.split(", ").forEach(actors => {
        let actorLink = document.createElement("a");
        actorLink.href = "";
        actorLink.innerHTML = actors.split(',', 1);
        actorList.appendChild(actorLink);
    });
    parent.appendChild(actorList);

    parent.appendChild(createInfoElem("infoHeader", "Kort Sammendrag"));
    parent.appendChild(createInfoElem("explanation", movie.description.substring(0,180) + "..."));
}
function createButtonsSection(parent) {
    let buttonSection = document.createElement("div");
    buttonSection.classList.add("buttonSection");
    let addToListButton = document.createElement("button");
    addToListButton.classList.add("addToList");
    addToListButton.type = "submit";
    addToListButton.innerHTML = "Add to cart";
    addToListButton.addEventListener("click", toggleChopList)
    let addToWishButton = document.createElement("button");
    addToWishButton.classList.add("addToWish");
    addToWishButton.type = "submit";
    addToWishButton.innerHTML = "Add to list";
    addToWishButton.addEventListener("click", toggleWishList)
    buttonSection.appendChild(addToListButton);
    buttonSection.appendChild(addToWishButton);
    parent.appendChild(buttonSection);
}
function giveRatingToMovie(){
    if(getElemId("star5").checked){
        console.log("5");
    }else if(getElemId("star4").checked){
        console.log("4");
    }else if(getElemId("star3").checked){
        console.log("3");        
    }else if(getElemId("star2").checked){
        console.log("2");                
    }else if(getElemId("star1").checked){
        console.log("1");                
    }
}