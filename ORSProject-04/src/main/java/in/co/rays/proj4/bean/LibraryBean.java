package in.co.rays.proj4.bean;

public class LibraryBean extends BaseBean {
	private String title;
	private String author;
	private String category;
	private long availableCopies;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getAvailableCopies() {
		return availableCopies;
	}

	public void setAvailableCopies(long availableCopies) {
		this.availableCopies = availableCopies;
	}

	@Override
	public String getKey() {
		return author;
	}

	@Override
	public String getValue() {
		return author;
	}
}
