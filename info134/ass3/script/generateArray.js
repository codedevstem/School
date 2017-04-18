const movieArray = [];
/**
 * 
 */
function makeMovieArray() {
 let search_results = movies_object;
 for(let movie_id in search_results){
  let movie = search_results[movie_id];
  movieArray.push(movie);
 }
}