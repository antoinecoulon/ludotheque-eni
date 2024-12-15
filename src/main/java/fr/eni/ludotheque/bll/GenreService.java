package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Genre;

public interface GenreService {
		
	Optional<Genre> getGenreById(int id_genre);
	List<Genre> getAll();
}
