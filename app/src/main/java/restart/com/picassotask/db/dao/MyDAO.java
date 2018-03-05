package restart.com.picassotask.db.dao;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import restart.com.picassotask.db.DatabaseHelper;
import restart.com.picassotask.entity.Article;

import static restart.com.picassotask.MainActivity.TAG;


/**
 * Created by Administrator on 2018/3/5.
 */

public class MyDAO {
    private Dao<Article, Integer> dao;

    public MyDAO(Context context) {
        try {
            dao = DatabaseHelper.getInstance(context).getDao(Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入数据
     *
     * @param article
     */
    public int insert(Article article) {
        try {
            int rsp = dao.create(article);
            Log.d(TAG, "insert:  插入成功---id为:" + article.get_id());
            return rsp;
        } catch (SQLException e) {
            Log.d(TAG, "insert:  插入失败---id为:" + article.get_id());
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * 删除
     *
     * @param _id
     * @return
     */
    public int delete(int _id) {
        try {
            int rsp = dao.deleteById(_id);
            Log.d(TAG, "delete: 删除成功---");
            return rsp;
        } catch (SQLException e) {
            Log.d(TAG, "delete: 删除失败---");

        }
        return 0;
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Article> queryAll() {
        try {
            List<Article> articles = dao.queryForAll();
            return articles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
        查询单个
     */
    public Article query(String _id) {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("title", _id);
            List<Article> article = dao.queryForFieldValues(map);
            if (article.size()>0) {
                return article.get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
