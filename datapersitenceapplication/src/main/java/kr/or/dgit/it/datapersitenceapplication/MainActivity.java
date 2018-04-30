package kr.or.dgit.it.datapersitenceapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends AppCompatActivity {
    String[] titles;
    String[] activities;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //원본
        titles = getResources().getStringArray(R.array.titles);
        activities = getResources().getStringArray(R.array.activities);

        //어댑터
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,titles);

        //리스트뷰
        mListView = findViewById(R.id.listView);

        //리스트뷰 연결
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName(getPackageName(), getPackageName()+"."+activities[position]);
                intent.putExtra("title", titles[position]);
                startActivity(intent);
            }
        });
    }
}
