package by.htp.main.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="news")
public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer idNews = 0;
	
	@Column(name="title")
	@Pattern(regexp = "^[a-zA-ZА-Яа-я0-9-\\s.,;:]{5,}$")
	private String title = "";
	
	@Column(name="brief")
	@Pattern( regexp = "^[a-zA-ZА-Яа-я0-9-\\s.,;:]{5,}$")
	private String briefNews = "";
	
	@Column(name="content")
	@Pattern(regexp = "^[a-zA-ZА-Яа-я0-9-\\s.,;:]{10,}$")
	private String content = "";
	
	@Column(name="date_of_creation")
	@Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$")
	private String newsDate = ""; 
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "users_id")
	private User user;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "news_status_id_news_status")
	private NewsStatus newsStatus;

	public News() {
		super();
	}

	public News(String title, String briefNews, String content, String newsDate) {
		super();
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public NewsStatus getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(NewsStatus newsStatus) {
		this.newsStatus = newsStatus;
	}

	@Override
	public int hashCode() {
		return Objects.hash(briefNews, content, idNews, newsDate, newsStatus, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		return Objects.equals(briefNews, other.briefNews) && Objects.equals(content, other.content)
				&& Objects.equals(idNews, other.idNews) && Objects.equals(newsDate, other.newsDate)
				&& Objects.equals(newsStatus, other.newsStatus) && Objects.equals(title, other.title)
				&& Objects.equals(user, other.user);
	}
	
	

}
