package comuxi.example.administrator.month_text_k;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
//    ArrayList<TextBean> arrayList = new ArrayList<>();
    MainActivitiAdapter  adapter;

    private ArrayList<Bean> beans = new ArrayList<>();

//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//
//            adapter.notifyDataSetChanged();
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.my_list_view);
        initData();

        adapter = new MainActivitiAdapter(this,beans);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);

                intent.putParcelableArrayListExtra("list",beans);
                startActivity(intent);


            }
        });


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


//               switch (visibleItemCount){
//                   case  :
//
//
               }




        });

    }



    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url  = new URL("http://172.16.37.18:8080/Month/xiaoshixun.json");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //设置链接超时时间
                    httpURLConnection.setConnectTimeout(15000);
                    //设置读取超时时间
                    httpURLConnection.setReadTimeout(15000);
                    //设置请求参数
                    httpURLConnection.setRequestMethod("POST");
                    //添加Header
                    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                    //接收输入流
                    httpURLConnection.setDoInput(true);
                    //传递参数时需要开启
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.connect();//开启连接
                    InputStream inputStream = httpURLConnection.getInputStream();
                    StringBuilder stringBuilder = new StringBuilder();
                    byte[] bytes = new byte[1024];
                    int len = -1;
                    while ((len = inputStream.read(bytes)) != -1) {
                        stringBuilder.append(new String(bytes, 0, len));
                    }
                    String jsondata = stringBuilder.toString().trim();
                    JSONArray jsonArray = new JSONArray(jsondata);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d("TAG", "数组+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        int t_id = jsonObject.getInt("t_id");
                        String content = jsonObject.getString("content");
                        ArrayList<String> strings = new ArrayList<>();
                        JSONArray imgs = jsonObject.getJSONArray("imgs");
                        for (int a = 0; a < imgs.length()-1; a++) {
                            String string = imgs.getString(a);
                            strings.add(string);
                        }
                        Bean bean = new Bean(name, t_id, content, strings);
                        beans.add(bean);
//                        handler.sendEmptyMessage(300);
                    }


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }



}
