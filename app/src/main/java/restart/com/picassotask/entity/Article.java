package restart.com.picassotask.entity;

/**
 * Created by Administrator on 2018/2/22.
 */

public class Article {
    private int _id;
    private String title;
    private String author;
    private String content;

    public Article(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public Article() {}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "_id=" + _id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
