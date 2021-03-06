package com.nyanjuimarvin.basedshare.constants;

public class Constants {
    public static final String SPOTIFY_BASE_URL = "https://api.spotify.com/v1/";
    public static final String MOVIE_DB_BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original";
    public static final String RAWG_BASE_URL = "https://api.rawg.io/api/";

    //Spotify  authentication
    public static final String SPOTIFY_AUTH_URL = "https://accounts.spotify.com/";
    public static final String CLIENT_ID = "e86c3eb22a7644169b9f90ed7dee90ab";
    public static final String CLIENT_SECRET = "f92a1c067edd4ccab9843ccfc18e63ae";
    public static final String SCOPES = "user-read-private user-read-email user-modify-playback-state user-read-playback-position user-library-read streaming user-read-playback-state user-read-recently-played playlist-read-private";
    public static final String REDIRECT_URL = "https://github.com/Nyanjuimarvin/Based-Share/";
    public static final String POST_TOKEN_PATH = "https://accounts.spotify.com/api/token/";
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    public static final String GRANT_TYPE = "client_credentials";

    public static final String RAWG_KEY = "94b30e61ab464d5391dad2327cbde848";
    public static final String MOVIE_DB_KEY = "80c2c0f8c85114414c562a7c20f011c6";

    /* Firebase */
    public static final int REQUEST_CODE = 101;
    public static final String FIREBASE_GAME_NODE = "Games";
    public static final String FIREBASE_FILM_NODE = "Films";

    /* Shared Preferences */
    public static final String USERNAME_KEY = "userName";
    public static final String USER_MAIL_KEY = "userMail";
}
