package fa.training.entities;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "MOVIE_TYPE")
public class MovieType {
	@EmbeddedId
	private MovieTypeId movieTypeId;
	
	@Column(name = "MT_DESCRIPTION")
	private String mtDescription;

	public MovieType() {
		super();
	}

	public MovieType(MovieTypeId movieTypeId, String mtDescription) {
		super();
		this.movieTypeId = movieTypeId;
		this.mtDescription = mtDescription;
	}

	public MovieTypeId getMovieTypeId() {
		return movieTypeId;
	}

	public void setMovieTypeId(MovieTypeId movieTypeId) {
		this.movieTypeId = movieTypeId;
	}

	public String getMtDescription() {
		return mtDescription;
	}

	public void setMtDescription(String mtDescription) {
		this.mtDescription = mtDescription;
	}

	@Override
	public String toString() {
		return "MovieType [movieTypeId=" + movieTypeId + ", mtDescription=" + mtDescription + "]";
	}
	
	
}
