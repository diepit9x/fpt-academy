package fa.training.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "MOVIE")
public class Movie {

	@Id
	@Column(name = "MOVIE_ID", length = 10, nullable = false)
	private String movieId;
	
	@Column(name = "ACTOR")
	private String actor;
	
	@Column(name = "CONTENT", length = 1000)
	private String content;
	
	@Column(name = "DIRECTOR")
	private String director;
	
	@Column(name = "DURATION", columnDefinition = "DECIMAL(5,2)")
	private float duration;
	
	@Column(name = "FROM_DATE")
	private LocalDate fromDate;
	
	@Column(name = "TO_DATE")
	private LocalDate toDate;
	
	@Column(name = "MOVIE_PRODUCTION_COMPANY")
	private String movieProductionCompany;
	
	@Column(name = "VERSION")
	private String version;
	
	@Column(name = "MOVIE_NAME_ENG", unique = true)
	private String movieNameEng;
	
	@Column(name = "MOVIE_NAME_VN", unique = true)
	private String movieNameVn;
	
	@Column(name = "LARGE_IMAGE")
	private String largeImage;
	
	@Column(name = "SMALL_IMAGE")
	private String smallImage;
	
	@OneToMany(
			mappedBy = "movieTypeId.movie",
			cascade = CascadeType.ALL, 
			fetch = FetchType.LAZY,
			orphanRemoval = true)
	private Set<MovieType>  movieTypes;

	public Movie() {
		super();
	}

	public Movie(String movieId, String actor, String content, String director, float duration, LocalDate fromDate,
			LocalDate toDate, String movieProductionCompany, String version, String movieNameEng, String movieNameVn,
			String largeImage, String smallImage, Set<MovieType> movieTypes) {
		super();
		this.movieId = movieId;
		this.actor = actor;
		this.content = content;
		this.director = director;
		this.duration = duration;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.movieProductionCompany = movieProductionCompany;
		this.version = version;
		this.movieNameEng = movieNameEng;
		this.movieNameVn = movieNameVn;
		this.largeImage = largeImage;
		this.smallImage = smallImage;
		this.movieTypes = movieTypes;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

	public String getMovieProductionCompany() {
		return movieProductionCompany;
	}

	public void setMovieProductionCompany(String movieProductionCompany) {
		this.movieProductionCompany = movieProductionCompany;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMovieNameEng() {
		return movieNameEng;
	}

	public void setMovieNameEng(String movieNameEng) {
		this.movieNameEng = movieNameEng;
	}

	public String getMovieNameVn() {
		return movieNameVn;
	}

	public void setMovieNameVn(String movieNameVn) {
		this.movieNameVn = movieNameVn;
	}

	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(String smallImage) {
		this.smallImage = smallImage;
	}

	public Set<MovieType> getMovieTypes() {
		return movieTypes;
	}

	public void setMovieTypes(Set<MovieType> movieTypes) {
		this.movieTypes = movieTypes;
	}

	@Override
	public String toString() {
		return "Movie [movieId=" + movieId + ", actor=" + actor + ", content=" + content + ", director=" + director
				+ ", duration=" + duration + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", movieProductionCompany=" + movieProductionCompany + ", version=" + version + ", movieNameEng="
				+ movieNameEng + ", movieNameVn=" + movieNameVn + ", largeImage=" + largeImage + ", smallImage="
				+ smallImage + ", movieTypes=" + movieTypes + "]";
	}
}
