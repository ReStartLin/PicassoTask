package restart.com.picassotask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import restart.com.picassotask.entity.Article;
import restart.com.picassotask.net.Inter;

public class MainActivity extends AppCompatActivity {
    public static final String URL = "http://www.imooc.com/api/teacher?type=3&cid=";
    public static final String IMG_URL = "http://img.mukewang.com/546418750001a81906000338-590-330.jpg";
    private int index = 1;//当前篇章
    private Article nowArticle;
    private TextView titleTv;
    private TextView authorTv;
    private TextView contentTv;
    private ImageView showImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleTv = findViewById(R.id.title_tv);
        authorTv = findViewById(R.id.author_tv);
        contentTv = findViewById(R.id.content_tv);
        showImg = findViewById(R.id.show_Img);
        findViewById(R.id.next_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();

            }
        });
        findViewById(R.id.before_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBefore();
            }
        });
        findViewById(R.id.collect_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, ""+nowArticle.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        getArticle();
    }

    public void showNext() {
        index = ++index > 30 ? 1 : index;
        Toast.makeText(this, "当前第"+index+"篇", Toast.LENGTH_SHORT).show();
        getArticle();
    }

    public void showBefore() {
        index = --index <= 0 ? 30 : index;
        Toast.makeText(this, "当前第"+index+"篇", Toast.LENGTH_SHORT).show();
        getArticle();
    }
    /*加载*/
    public void getArticle() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                String path = URL + index;
                String result = Inter.get(path);
                try {
                    JSONObject json = new JSONObject(result);
                    JSONObject datajson = json.getJSONObject("data");
                    Gson gson = new Gson();
                    final Article article = gson.fromJson(datajson.toString(),
                            new TypeToken<Article>() {
                            }.getType());
                    article.set_id(index);
                    nowArticle = article;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            titleTv.setText(article.getTitle());
                            authorTv.setText(article.getAuthor());
                            contentTv.setText(article.getContent());
                            Picasso.with(MainActivity.this).load(IMG_URL).into(showImg);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

