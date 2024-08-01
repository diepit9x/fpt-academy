package fa.training.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "MovieTheater", name = "TYPE")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TYPE_ID")
	private int typeId;
	
	@Column(name = "TYPE_NAME", unique = true)
	private String typeName;
	
	@Column(name = "TYPE_DESCRIPTION")
	private String typeDescription;
	
	@OneToMany(
			mappedBy = "movieTypeId.type",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER,
			orphanRemoval = true)
	 private Set<MovieType>  movieTypes;
   
	public Type() {
		super();
	}

	public Type(int typeId, String typeName, String typeDescription, Set<MovieType> movieTypes) {
		super();
		this.typeId = typeId;
		this.typeName = typeName;
		this.typeDescription = typeDescription;
		this.movieTypes = movieTypes;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public Set<MovieType> getMovieTypes() {
		return movieTypes;
	}

	public void setMovieTypes(Set<MovieType> movieTypes) {
		this.movieTypes = movieTypes;
	}

	@Override
	public String toString() {
		return "Type [typeId=" + typeId + ", typeName=" + typeName + ", typeDescription=" + typeDescription
				+ ", movieTypes=" + movieTypes + "]";
	}
}
