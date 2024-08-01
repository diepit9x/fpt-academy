package fa.training.entities;


import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "Students")
public class Student {
	@Id
	@GeneratedValue
	@UuidGenerator(style = UuidGenerator.Style.TIME)
	private UUID id;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	private Lession lession; 
	
	public Student() {
	}
	
	public Student(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Student(String name) {
		super();
		this.name = name;
	}


	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lession getLession() {
		return lession;
	}

	public void setLession(Lession lession) {
		this.lession = lession;
		lession.setStudent(this);
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + "]";
	}
}
