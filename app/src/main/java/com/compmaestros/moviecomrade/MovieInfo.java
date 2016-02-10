package com.compmaestros.moviecomrade;

/**
 * Represents the parameters about a movie retrieved from the API call to theMovieDB.
 */
public class MovieInfo {

    // Complete url of the movie poster.
    private String fullPosterPath;

    // Id is used to retrieve more information about the movie.
    // Example: http://api.themoviedb.org/3/movie/281957/videos?api_key={API_KEY}
    // 281957 is the id here.
    private String movieId;

    private String overView;

    private String releaseDate;

    private String originalTitle;

    private String voteAverage;

    /**
     * Converts the relative poster path to complete url.
     * @param relativePosterPath is relative poster path retrieved from API call. It is in the form:
     *                           \/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg
     * @param movieId movieId retrieved from Json String.
     */
    public MovieInfo(String relativePosterPath,
                     String movieId,
                     String overView,
                     String releaseDate,
                     String originalTitle,
                     String voteAverage) {

        fullPosterPath = convertToFullPath(relativePosterPath);
        this.movieId = movieId;
        this.overView = overView;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.voteAverage = voteAverage;
    }

    /**
     * API response provides a relative path to a movie poster image e.g.
     * \/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg
     * Here you'll need to trim the backward slash. So, resulting relative image path is
     * /oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg
     * After that, You need to prepend a base path to this relative path to build the complete url.
     * The base URL will look like: http://image.tmdb.org/t/p/
     * Then ‘size’, which will be one of the following: "w92", "w154", "w185", "w342", "w500",
     * "w780", or "original". For most phones “w185” is recommended.
     * So resulting example image url that should be:
     * http://image.tmdb.org/t/p/w185//oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg
     */
    private String convertToFullPath(String relativePosterPath) {
        final String baseUrl = "http://image.tmdb.org/t/p/w185/";
        String fullUrl = relativePosterPath.substring(1);
        fullUrl = baseUrl.concat(fullUrl);
        return fullUrl;
    }

    /** Returns full path to movie poster */
    public String getFullPosterPath() {
        return fullPosterPath;
    }

    /**
     * Returns movieId which can be used to retrieve more information about the movie.
     * Example: http://api.themoviedb.org/3/movie/281957/videos?api_key={API_KEY}
     * 281957 is the id here.
     */
    public String getMovieId() {
        return movieId;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getOriginalTitle() {

        return originalTitle;
    }

    public String getReleaseDate() {

        return releaseDate;
    }

    public String getOverView() {

        return overView;
    }

    @Override
    public String toString() {
        return "Full Poster path: " + fullPosterPath + "\n" +
                "Movie ID: " + movieId + "\n" +
                "Overview: " + overView + "\n" +
                "Release Date: " + releaseDate + "\n" +
                "Original Title: " + originalTitle + "\n" +
                "Vote Average: " + voteAverage;
    }

    // TODO: Write a JUnit test class for this class.
    // Test this class
    public static void main(String[] args) {
        MovieInfo movieInfo = new MovieInfo(
                "\\/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg",
                "281957",
                "In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.",
                "2015-12-25",
                "The Revenant",
                "7.28");
        System.out.println(movieInfo);

        System.out.println(movieInfo.getMovieId());
        System.out.println(movieInfo.getFullPosterPath());
        System.out.println(movieInfo.getOriginalTitle());
        System.out.println(movieInfo.getOverView());
        System.out.println(movieInfo.getReleaseDate());
        System.out.println(movieInfo.getVoteAverage());

    }

}
