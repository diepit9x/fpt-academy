package fa.training.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "MOVIE_TYPE")
public class MovieType3 {
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "TYPE_ID")
	private Type type;
	
	@Id
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "MOVIE_ID")
	private Movie movie;
	
	@Column(name = "MT_DESCRIPTION")
	private String mtDescription;
	 
	public MovieType3() {
		super();
	}

	public MovieType3(Type type, Movie movie, String mtDescription) {
		super();
		this.type = type;
		this.movie = movie;
		this.mtDescription = mtDescription;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public String getMtDescription() {
		return mtDescription;
	}

	public void setMtDescription(String mtDescription) {
		this.mtDescription = mtDescription;
	}

	@Override
	public String toString() {
		return "MovieType [type=" + type + ", movie=" + movie + ", mtDescription=" + mtDescription + "]";
	}

	

}
