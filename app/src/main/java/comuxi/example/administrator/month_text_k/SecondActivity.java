package comuxi.example.administrator.month_text_k;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/6.
 */
public class SecondActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TextView title, content;
    private ImageView imageView;
    ArrayList<Bean> list;

    ArrayList<View> arrayList= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoactivity);

        viewPager = (ViewPager) findViewById(R.id.two_viewpage);
        title = (TextView) findViewById(R.id.two_title);
        content = (TextView) findViewById(R.id.two_conten);


        Intent intent = getIntent();

        list= intent.getParcelableArrayListExtra("list");

        title.setText(list.get(1).getContent());
        title.setText(list.get(1).getContent());


        for (int i = 0; i < 3; i++) {
            View view = LayoutInflater.from(SecondActivity.this).inflate(R.layout.viewpage_item, null);

            imageView = (ImageView) view.findViewById(R.id.view_page_image);

            Glide.with(SecondActivity.this).load("http://img.juhe.cn/joke/201412/19/B9EBF01A3C718DABB4C166356CC839A8.jpg").into(imageView);

            arrayList.add(view);
        }


        ViewPage_Adapter adapter = new ViewPage_Adapter(arrayList);

        viewPager.setAdapter(adapter);

//        viewPager.set


    }
}
