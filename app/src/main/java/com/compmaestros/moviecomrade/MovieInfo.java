package com.compmaestros.moviecomrade;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents the parameters about a movie retrieved from the API call to theMovieDB.
 */
public class MovieInfo implements Parcelable {

    private static final String POSTER_IMAGE_SIZE = "w185";
    private static final String BACKDROP_IMAGE_SIZE = "w342";

    // Complete url of the movie poster.
    private String fullPosterPath;

    private String fullBackdropPath;

    // Id is used to retrieve more information about the movie.
    // Example: http://api.themoviedb.org/3/movie/281957/videos?api_key={API_KEY}
    // 281957 is the id here.
    private String movieId;

    private String overview;

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
                     String relativeBackdropPath,
                     String movieId,
                     String overview,
                     String releaseDate,
                     String originalTitle,
                     String voteAverage) {

        fullPosterPath = convertToFullPath(relativePosterPath, POSTER_IMAGE_SIZE);
        fullBackdropPath = convertToFullPath(relativeBackdropPath, BACKDROP_IMAGE_SIZE);
        this.movieId = movieId;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.voteAverage = voteAverage;
    }

    protected MovieInfo(Parcel in) {
        fullPosterPath = in.readString();
        fullBackdropPath = in.readString();
        movieId = in.readString();
        overview = in.readString();
        releaseDate = in.readString();
        originalTitle = in.readString();
        voteAverage = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullPosterPath);
        dest.writeString(fullBackdropPath);
        dest.writeString(movieId);
        dest.writeString(overview);
        dest.writeString(releaseDate);
        dest.writeString(originalTitle);
        dest.writeString(voteAverage);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MovieInfo> CREATOR = new Creator<MovieInfo>() {
        @Override
        public MovieInfo createFromParcel(Parcel in) {
            return new MovieInfo(in);
        }

        @Override
        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };

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
     *
     * Back drop path: http://image.tmdb.org/t/p/w342//uETWtwsE1QjfoFqRQqFLnSjppPA.jpg
     */
    private String convertToFullPath(String relativePosterPath, String imageSize) {
        String baseUrl = "http://image.tmdb.org/t/p/";
        baseUrl += imageSize + "/";
        String fullUrl = relativePosterPath.substring(1);
        fullUrl = baseUrl.concat(fullUrl);
        return fullUrl;
    }

    /** Returns full path to movie poster */
    public String getFullPosterPath() {
        return fullPosterPath;
    }

    /** Returns full backdrop path of the movie */
    public String getFullBackdropPath() {
        return fullBackdropPath;
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

    public String getOverview() {

        return overview;
    }

    public String getReleaseYear() {
        return releaseDate.substring(0, 4);
    }

    @Override
    public String toString() {
        return "Full Poster path: " + fullPosterPath + "\n" +
                "Movie ID: " + movieId + "\n" +
                "Overview: " + overview + "\n" +
                "Release Date: " + releaseDate + "\n" +
                "Original Title: " + originalTitle + "\n" +
                "Vote Average: " + voteAverage;
    }

    // TODO: Write a JUnit test class for this class.
    // Test this class
    public static void main(String[] args) {
        MovieInfo movieInfo = new MovieInfo(
                "\\/oXUWEc5i3wYyFnL1Ycu8ppxxPvs.jpg",
                "\\/uETWtwsE1QjfoFqRQqFLnSjppPA.jpg",
                "281957",
                "In the 1820s, a frontiersman, Hugh Glass, sets out on a path of vengeance against those who left him for dead after a bear mauling.",
                "2015-12-25",
                "The Revenant",
                "7.28");
        System.out.println(movieInfo);

        System.out.println(movieInfo.getMovieId());
        System.out.println(movieInfo.getFullBackdropPath());
        System.out.println(movieInfo.getFullPosterPath());
        System.out.println(movieInfo.getOriginalTitle());
        System.out.println(movieInfo.getOverview());
        System.out.println(movieInfo.getReleaseDate());
        System.out.println(movieInfo.getVoteAverage());
        System.out.println(movieInfo.getReleaseYear());
    }
}
