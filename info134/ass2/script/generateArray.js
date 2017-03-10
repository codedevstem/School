const movieArray = [];
function makeArray() {
 search_results = movies_object;
 for(movie_id in search_results){
  let movie = search_results[movie_id];
  movieArray.push(movie);
 }
}