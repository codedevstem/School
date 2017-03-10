let newMoviesArray = movieArray;
let recentMoviesArray = movieArray;
let SuggestionsMoviesArray = movieArray;
let rateAMovie = movieArray;
for(let i = newMoviesArray.length-1; i>=0; i--){
		let movie = newMoviesArray[i];
		if(movie.reg_date == null) newMoviesArray.splice(newMoviesArray.indexOf(movie), 1);
	}
newMoviesArray.sort(function(a,b){
	let aDate = a.reg_date.substring(0, 4) + '' + a.reg_date.substring(5,7) + '' + a.reg_date.substring(8,10);
	let bDate = b.reg_date.substring(0, 4) + '' + b.reg_date.substring(5,7) + '' + b.reg_date.substring(8,10);
	return bDate - aDate
});
newMoviesArray.splice(7)
console.log(newMoviesArray);