package fr.eni.ludotheque.dal;

import java.util.List;
import java.util.Optional;

import fr.eni.ludotheque.bo.Genre;

public interface GenreRepository {

	Optional<Genre> getById(int id);

	List<Genre> getAll();
	
}
