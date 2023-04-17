package by.htp.main.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "news_status")
public class NewsStatus implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_news_status")
	private int idNewsStatus;
	
	@Column(name = "news_status_name")
	private String newsStatus;
	
	@OneToMany(mappedBy = "newsStatus", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
			CascadeType.REFRESH })
	private List<News> news;

	public NewsStatus() {
		super();
	}

	public NewsStatus(int idNewsStatus, String newsStatus) {
		super();
		this.idNewsStatus = idNewsStatus;
		this.newsStatus = newsStatus;
	}

	public int getIdNewsStatus() {
		return idNewsStatus;
	}

	public void setIdNewsStatus(int idNewsStatus) {
		this.idNewsStatus = idNewsStatus;
	}

	public String getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
	}

	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idNewsStatus, news, newsStatus);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewsStatus other = (NewsStatus) obj;
		return idNewsStatus == other.idNewsStatus && Objects.equals(news, other.news)
				&& Objects.equals(newsStatus, other.newsStatus);
	}
	
	

}
