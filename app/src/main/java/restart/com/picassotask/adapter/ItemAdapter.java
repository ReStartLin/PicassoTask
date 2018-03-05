package restart.com.picassotask.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import restart.com.picassotask.ArticleActivity;
import restart.com.picassotask.R;
import restart.com.picassotask.db.dao.MyDAO;
import restart.com.picassotask.entity.Article;

/**
 * Created by Administrator on 2018/3/5.
 */

public class ItemAdapter extends BaseAdapter {
    private List<Article> articles;
    private Context context;
    private MyDAO dao;

    public ItemAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
        dao = new MyDAO(context);
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item, null);
            viewHolder.textView = convertView.findViewById(R.id.show_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(articles.get(position).getTitle());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, "当前ID为--"+articles.get(position).get_id(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.putExtra("article", articles.get(position));
                context.startActivity(intent);
            }
        });
        viewHolder.textView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(context, "长按", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("取消收藏?");
                builder.setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       int res =  dao.delete(articles.get(position).get_id());
                        if (res > 0) {
                            Toast.makeText(context, "取消收藏成功", Toast.LENGTH_SHORT).show();
                            articles.remove(position);
                        } else {
                            Toast.makeText(context, "取消收藏失败", Toast.LENGTH_SHORT).show();
                        }
                        notifyDataSetChanged();
                    }
                });
                builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setCancelable(false);
                alertDialog.show();

                return true;
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
