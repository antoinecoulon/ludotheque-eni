package fr.eni.ludotheque.bll;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import fr.eni.ludotheque.bo.Genre;
import fr.eni.ludotheque.dal.GenreRepository;

@Service("genreService")
public class GenreServiceImpl implements GenreService {

	GenreRepository genreRepository;
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public GenreServiceImpl(GenreRepository genreRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.genreRepository = genreRepository;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public Optional<Genre> getGenreById(int id_genre) {
		return genreRepository.getById(id_genre);
	}
	
   public List<Genre> getAll(){
	   return genreRepository.getAll();
   }
	

	
}
