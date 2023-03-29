package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String,Movie> movies=new HashMap<>();
    HashMap<String,Director> directors=new HashMap<>();
    HashMap<String,List<String>> directorMovies=new HashMap<>();
    public void addMovie(Movie movie) {
        movies.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        directors.put(director.getName(),director);
    }

    public void addMovieDirectorPair(String movieName, String directorName) {
        if(directorMovies.containsKey(directorName)){
            directorMovies.get(directorName).add(movieName);
        }else{
            List<String> list = new ArrayList<>();
            list.add(movieName);
            directorMovies.put(directorName,list);
        }
    }

    public Movie getMovieByName(String name) {
        return movies.get(name);
    }

    public Director getDirectorByName(String name) {

        return directors.get(name);
    }

    public List<String> getMoviesByDirectorName(String director) {
        if(directorMovies.containsKey(director)){
            return directorMovies.get(director);
        }else{
            return new ArrayList<String>();
        }
    }

    public List<String> findAllMovies() {
        List<String> movielist=new ArrayList<>();
        for(Movie m:movies.values()){
            movielist.add(m.getName());
        }
        return movielist;
    }

    public void deleteDirectorByName(String name) {
        if(directorMovies.containsKey(name)){
            for(String s:directorMovies.get(name)){
                movies.remove(s);
            }
        }
        directors.remove(name);
    }

    public void deleteAllDirectors() {
        for(String k:directors.keySet()){
            if(directorMovies.containsKey(k)){
                for(String v:directorMovies.get(k)){
                    movies.remove(v);
                }
            }
            directors.remove(k);
        }
    }

}
