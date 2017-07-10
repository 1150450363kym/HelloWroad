package comuxi.example.administrator.month_text_k;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/6.
 */
public class MainActivitiAdapter extends BaseAdapter {
    MainActivity mainActivity;
    ArrayList<Bean> arrayList;
    public MainActivitiAdapter(MainActivity mainActivity, ArrayList<Bean> arrayList) {
        this.mainActivity=mainActivity;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        My_View my_view ;
     if(convertView==null){

         my_view = new My_View();

         convertView = LayoutInflater.from(mainActivity).inflate(R.layout.list_view_item,null);

         my_view.imageView = (ImageView) convertView.findViewById(R.id.image_list);

         my_view.title = (TextView) convertView.findViewById(R.id.title_list);

         my_view.content = (TextView) convertView.findViewById(R.id.content_list);

         convertView.setTag(my_view);
     }else{
         my_view= (My_View) convertView.getTag();
     }



        my_view.content.setText(arrayList.get(position).getContent());

        my_view.title.setText(arrayList.get(position).getName());

        Glide.with(mainActivity).load("http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg").into(my_view.imageView);







        return convertView;
    }
    class My_View{

        ImageView imageView;
        TextView title;
        TextView content;


    }


}
