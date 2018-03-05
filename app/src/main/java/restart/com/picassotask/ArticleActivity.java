package restart.com.picassotask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import restart.com.picassotask.entity.Article;

public class ArticleActivity extends AppCompatActivity {

    private TextView author;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        author = findViewById(R.id.ariticle_author_tv);
        title = findViewById(R.id.ariticle_title_tv);
        content = findViewById(R.id.ariticle_content_tv);

        Article article = (Article) getIntent().getSerializableExtra("article");

        author.setText(article.getAuthor());
        title.setText(article.getTitle());
        content.setText(article.getContent());
    }
}
