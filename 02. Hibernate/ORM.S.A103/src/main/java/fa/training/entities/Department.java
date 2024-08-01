package fa.training.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "DEPARTMENTS")
public class Department {
	@Id
	@Column(name = "Id")
	private String id;

	@Column(name = "Name")
	private String name;
	
	@OneToMany(mappedBy = "department")
	private List<DepEmp> depEmps;

	public Department() {
		super();
	}

	public Department(String id, String name, List<DepEmp> depEmps) {
		super();
		this.id = id;
		this.name = name;
		this.depEmps = depEmps;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DepEmp> getDepEmps() {
		return depEmps;
	}

	public void setDepEmps(List<DepEmp> depEmps) {
		this.depEmps = depEmps;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + "]";
	}
}
