package kr.or.dgit.it.db_study;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.dgit.it.db_study.database.WordDbHelper;

public class AdapterViewActivity extends AppCompatActivity {

    List<String> arDatas;
    ListView arListView;
    private ArrayAdapter<String> arAdapter;


    String TBL_NAME = "dic";
    WordDbHelper mHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapter_view);
        setTitle(getIntent().getStringExtra("title"));

        //원본
        String[] strDatas = getResources().getStringArray(R.array.location);
        arDatas = new ArrayList<>();
        for(String d : strDatas){
            arDatas.add(d);
        }

        //Adapter
        arAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arDatas);

        //ListView
        arListView = findViewById(R.id.listview_array);
        arListView.setAdapter(arAdapter);

        arListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                arDatas.remove(position);
                arAdapter.notifyDataSetChanged();//삭제한 후 데이터를 다시 불러오게함
                Toast.makeText(getApplicationContext(),position +"번째 항목 삭제", Toast.LENGTH_LONG).show();
            }
        });

        /////////////////////////////////////////////////////////////////////////////////////////////////

        //원본
        ArrayList<Map<String, String>> simpleDatas = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("name", "류현진");
        map.put("content", "제발 좀 MLB에서 봤으면 좋겠어");
        simpleDatas.add(map);

        map = new HashMap<>();
        map.put("name", "오승환");
        map.put("content", "돌직구 장난아님");
        simpleDatas.add(map);


        //SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                simpleDatas,
                android.R.layout.simple_list_item_2,
                new String[]{"name","content"},
                new int[]{android.R.id.text1,android.R.id.text2});

        //ListView
        ListView simpleListView = findViewById(R.id.listview_simple);
        simpleListView.setAdapter(simpleAdapter);


        /////////////////////////////////////////////////////////
        //CursorView
        //원본
        ArrayList<Map<String, String>> cusorDatas = new ArrayList<>();
        mHelper = WordDbHelper.getInstance(this);
        db = mHelper.getWritableDatabase();
        Cursor cursor = db.query(TBL_NAME, new String[]{"_id","eng","han"},null,null,null,null,null);
        while(cursor.moveToNext()){
            Map<String, String> data = new HashMap<>();
            data.put("name", cursor.getString(0));//eng
            data.put("content",cursor.getString(1));//han
            cusorDatas.add(data);
        }

        //CusorAdapter
        CursorAdapter cursorAdapter = new SimpleCursorAdapter(
                                        this,
                                        android.R.layout.simple_list_item_activated_2,
                                        cursor,
                                        new String[]{"eng","han"},
                                        new int[]{android.R.id.text1,android.R.id.text2},
                                        CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        //ListView
        ListView cursorView = findViewById(R.id.listview_cursor);
        cursorView.setAdapter(cursorAdapter);
        }



    @Override
    protected void onPause() {
        super.onPause();
        db.close();//back버튼 누르면 db커넥션이 닫힘
    }

}
