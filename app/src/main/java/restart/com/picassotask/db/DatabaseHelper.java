package restart.com.picassotask.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import restart.com.picassotask.entity.Article;

/**
 * Created by Administrator on 2018/3/4.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            databaseHelper =  new DatabaseHelper(context);
        }
        return databaseHelper;
    }

    private DatabaseHelper(Context context) {
        super(context, "article.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
        try {
            TableUtils.dropTable(connectionSource, Article.class,true);
            TableUtils.createTable(connectionSource, Article.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
