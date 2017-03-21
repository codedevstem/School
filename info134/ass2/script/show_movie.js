let movie;
let movieGenres;
let movieAvgRating;
let descFlag;
let query_params;
let movieRatings = [];
let commentFlag = false;
function panic(message) {
    // window.history.back();
    alert(message);
}
/**
 * @description | Started on finilization of the HTML. 
 */
window.onload = function() {
    query_params = get_query_string_parameters();
    if (!query_params.id) {
        panic("No id given");
        return;
    }else{
        makeMovieArray();
        getMovieIdAndGenre();
        createTitleHeaders();
        createButtonsSection(getElemId("infoSection"));
        createDetailedInfoSection(getElemId("detailedInfo"));
        if(movieAvgRating == null){
            getElemId("currentRating").innerHTML = "Denne filmen har ikke blitt ratet enda";
        }else{
            getElemId("currentRating").innerHTML = "Andre har i gjennomsnitt gitt denne filmen: " + movieAvgRating.toFixed(1);
        }
        if(movieRatings.length > 0){
            let otherRatings = document.createElement("section");
            let newRatingSection = insertRatings(otherRatings)
            getElemId("detailedInfo").appendChild(newRatingSection);
            toggleShowComments(newRatingSection);
        }

        getElemId("submitRating").addEventListener("click", function(){giveRatingToMovie()}, false);
        //Tests for movies with youtube link. 
        if(movie.id == "3823" || movie.id == "3818" || movie.id == "1024" || movie.id == "3597" || movie.id == "2274" ||
        movie.id == "2754" || movie.id == "46" || movie.id == "3308" || movie.id == "3429" || movie.id == "83" ) {
            let youtubeElem = document.createElement("iframe");
            youtubeElem.height = "315";
            youtubeElem.src = "https://www.youtube.com/embed/"+movie.youtube_trailer_id;
            youtubeElem.allowFullscreen = true;
            getElemId("trailer").appendChild(youtubeElem);
        }
        //Creates images b,c and d. Just in case
        getElemId("images").appendChild(createImageElem("b"));
        getElemId("images").appendChild(createImageElem("c"));
        getElemId("images").appendChild(createImageElem("d"));
    }
    
};
/**
 * @description | Creates the ratings and inserts them
 */
function insertRatings (parent) {
    let individualRatings = document.createElement("section");
    individualRatings.id = "individualRatings";
    individualRatings.addEventListener("click", function() {toggleShowComments(individualRatings)},false);
    let otherRatingsHeader = document.createElement("h3");
    otherRatingsHeader.innerHTML = "Lukk Kommentarer og stjerner";
    individualRatings.appendChild(otherRatingsHeader);
    movieRatings.forEach(rating => {
        let individualRating = document.createElement("div");
        let ratingHeader = document.createElement("div");
        let ratingUser = document.createElement("p");
        ratingUser.innerHTML = "Bruker: " + rating.username;
        ratingHeader.appendChild(ratingUser);
        let ratingRating = document.createElement("p");
        ratingRating.innerHTML = "Stjerner: " + rating.rating;
        ratingHeader.appendChild(ratingRating);   
        individualRating.appendChild(ratingHeader);             
        if(rating.comment != undefined){
            let ratingComment = document.createElement("p");
            ratingComment.innerHTML = "Kommentar: " + rating.comment;
            individualRating.appendChild(ratingComment);                
        }
        individualRatings.appendChild(individualRating);
    });
    return individualRatings;         
}
function toggleShowComments(parent) {
    parent.innerHTML = "";
    toReplaceNode = parent;
    if(commentFlag){
        getElemId("detailedInfo").replaceChild(insertRatings(toReplaceNode),toReplaceNode);
    } else {
        let newSection = document.createElement("section");
        newSection.addEventListener("click", function() {toggleShowComments(newSection)},false);
        newSection.id = "individualRatings";
        let emptyHeader = document.createElement("h3");
        emptyHeader.innerHTML = "Se Kommentarer og stjerner";
        newSection.appendChild(emptyHeader);
        getElemId("detailedInfo").replaceChild(newSection, parent);
    }
    commentFlag = !commentFlag;
}
/**
 * @description | inserts the movie title on top of document 
 */
function createTitleHeaders() {
    getElemId('oTitle').innerHTML = movie.otitle + " (" + movie.year + ")";
    if(movie.etitle != null && movie.etitle != movie.otitle){
        getElemId('eTitle').innerHTML = movie.etitle + " (" + movie.year + ")";
    }
    if(movie.ntitle != null && movie.ntitle != movie.otitle){
        getElemId('nTitle').innerHTML = movie.ntitle + " (" + movie.year + ")";
    }
}
/**
 * @description | Creates and image node, assigns a src and alt property and also
 * assigns an onerror event that hides the image if it can not be loaded. 
 * 
 * @param {String} letter | Indicator of the image to get from the URL. 
 * 
 * @returns {Node} image | an image node to be appended onto its parent.
 */
function createImageElem(letter){
    let image = document.createElement("img");
    image.src = "https://nelson.uib.no/o/" + parseInt(movie.id / 1000) + "/" + movie.id + letter + ".jpg";
    image.alt = "Cover image of " + movie.otitle;
    image.onerror = ()=>{image.style.display = "none"};
    return image;
}
/**
 * @description | Gets the movie id of from the object.json file, 
 * Compares it to genres.json to find if it has any genres. 
 * Compares it to reviews.json to find if it has been rated
 * If it has been rated it is then calculated.
 */
function getMovieIdAndGenre() {
    movieArray.forEach(movies => {
        if(movies.id == query_params.id) {
            movie = movies;
            // check for genres and collect them
            for(let genreMovie in genres_object){
                if(genreMovie == query_params.id){
                    movieGenres = genres_object[genreMovie].toString().replace(/\,/g, ", ");
                }
            }
            let totalRating = 0;
            let numberOfRating = 0;
            //check for ratings and collect data
            for (let review in reviews_object) {
                if (review == movie.id) {
                    for (let user in reviews_object[review]) {
                        movieRatings.push(reviews_object[review][user]);
                        totalRating += reviews_object[review][user].rating;
                        numberOfRating++;
                    }
                }
		    }
            /* checks if it is rated and calculates it 
                else it is given a null value */
            if(numberOfRating > 0){
                movieAvgRating = totalRating / numberOfRating
            } else {
                movieAvgRating = null;
            }

        }
    });
}
/**
 * @description | Event to be fired when someone clicks the button to add to take list.
 */
function toggleTakeList() {
    console.log("added to take list");
}
/**
 * @description | Event to be fired when someone clicks the button to add to wish list.
 */
function toggleWishList(){
    console.log("added to wish list!");
}
/**
 * @description | Shorthand function to retrive a node
 * @param {String} id | The id of the element you want
 * @returns {Node} 
 */
function getElemId(id) {
    return document.getElementById(id);
}
/**
 * @description | Creates a list element to be placed into a list
 * 
 * @param {String} className | Name of the class the list item should have
 * @param {String} content | The content to be placed into innerHTML.
 * @param {String} id | ? | Not needed but assigns an id to the node if recieved
 * 
 * @returns {Node} newElem | a <li> node
 */
function createInfoElem(className, content, id = "") {
    let newElem = document.createElement("li");
    newElem.classList.add(className);
    newElem.id = id;
    newElem.innerHTML = content;
    return newElem;
}
/**
 * @description | Function to append child nodes to the <ul> parent node
 * 
 * @param {Node} parent | the parent node to place all created nodes into.
 */
function createDetailedInfoSection(parent) {
    parent.appendChild(createInfoElem("infoHeader", "Genre"));
    parent.appendChild(createInfoElem("explanation", movieGenres));
    parent.appendChild(createInfoElem("infoHeader", "Lengde"));
    parent.appendChild(createInfoElem("explanation", movie.length + " minutter"));
    parent.appendChild(createInfoElem("infoHeader", "Skuespillere"));
    // Special Case to create the list of actors.
    let actorList = document.createElement("ul");
    actorList.classList.add("explanation");
    // Splits the actors into items
    movie.folk.split(", ").forEach(actors => {
        let actorLink = document.createElement("a");
        actorLink.href = "../pages/search_results.html?actor="+actors;
        // Removes trailing commas
        actorLink.innerHTML = actors.split(',', 1);
        actorList.appendChild(actorLink);
    });
    parent.appendChild(actorList);

    parent.appendChild(createInfoElem("infoHeader", "Kort Sammendrag", "descriptionHeader"));
    // Special case to create description since it must be expandable.  
    let description = createInfoElem("explanation", "", "description");
    // Adding the eventListener.
    description.addEventListener("click", function(){toggleDescLength()}, false);
    parent.appendChild(description);
    parent.appendChild(createInfoElem("infoHeader", "Produsert"));
    parent.appendChild(createInfoElem("explanation", movie.country));
    let directorList = document.createElement("ul");
    directorList.classList.add("explanation");
    movie.dir.split(", ").forEach(director => {
        let directorLink = document.createElement("a");
        directorLink.href = "../pages/search_results.html?director="+director;
        // Removes trailing commas
        directorLink.innerHTML = director.split(',', 1);
        directorList.appendChild(directorLink);
    });
    parent.appendChild(createInfoElem("infoHeader", "regissører"));
    parent.appendChild(directorList);
    toggleDescLength();
}
/**
 * @description | Toggles between long and short description for the current movie.
 */
function toggleDescLength() {
    if(descFlag) {
        getElemId("descriptionHeader").innerHTML = "Langt Sammendrag";
        getElemId("description").innerHTML = movie.description;
    } else {
        getElemId("descriptionHeader").innerHTML = "Kort Sammendrag";        
        getElemId("description").innerHTML = movie.description.substring(0,180) + "... Trykk for å lese mer";
    }
    // Toggles the description flag. 
    descFlag = !descFlag;
}
/**
 * @description | Creates a <div> to place buttons in and creates two buttons in that <div>
 * @param {Node} parent | The parent node to append created child nodes to.
 */
function createButtonsSection(parent) {
    let buttonSection = document.createElement("div");
    buttonSection.classList.add("buttonSection");
    let addToListButton = document.createElement("button");
    addToListButton.classList.add("addToList");
    addToListButton.type = "submit";
    addToListButton.innerHTML = "Add to cart";
    addToListButton.addEventListener("click", toggleTakeList)
    let addToWishButton = document.createElement("button");
    addToWishButton.classList.add("addToWish");
    addToWishButton.type = "submit";
    addToWishButton.innerHTML = "Add to list";
    addToWishButton.addEventListener("click", toggleWishList)
    buttonSection.appendChild(addToListButton);
    buttonSection.appendChild(addToWishButton);
    parent.appendChild(buttonSection);
}
/**
 * @description | Fires when a user submits a rating
 * Does nothing yet, but will be implemented in the future.
 */
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
    }else {
        console.log("0");
    }
}