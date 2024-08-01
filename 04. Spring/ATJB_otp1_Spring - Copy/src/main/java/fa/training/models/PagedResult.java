package fa.training.models;

import java.util.List;

public class PagedResult<T> {
    private int currentPage;
    private int totalPages;
    private List<T> results;

    public PagedResult(int currentPage, int totalPages, List<T> results) {
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.results = results;
    }

    // Getters and Setters
    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
