/* Author: 102 */
const movieArray = [];
/**
 * Fills up an array with movies that can be manipulated more easily than a nodeless list
 */
function makeMovieArray() {
 let searchResults = movies_object;
 for(let movie_id in searchResults){
  let movie = searchResults[movie_id];
  movieArray.push(movie);
 }
}