package fa.training.entities;

public class SearchData {
    private Course[] course;
    private String keyword;
    
    public SearchData() {
    }

    public SearchData(Course[] course, String keyword) {
	super();
	this.course = course;
	this.keyword = keyword;
    }

    public Course[] getCourse() {
        return course;
    }

    public void setCourse(Course[] course) {
        this.course = course;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
