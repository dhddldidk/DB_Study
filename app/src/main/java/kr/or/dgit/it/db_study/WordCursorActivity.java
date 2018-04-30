package kr.or.dgit.it.db_study;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import kr.or.dgit.it.db_study.dao.WordDbDao;
import kr.or.dgit.it.db_study.dto.Dic;

public class WordCursorActivity extends AppCompatActivity {
    private EditText insertEng;
    private EditText insertHan;
    private EditText updateNum;
    private EditText updateHan;
    private EditText updateENg;
    private EditText deleteNum;


    ListView listView;
    WordDbDao dao;
    WordCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_cursor);
        setTitle(getIntent().getStringExtra("title"));

        insertEng = findViewById(R.id.insert_eng);
        insertHan = findViewById(R.id.insert_han);
        updateNum = findViewById(R.id.update_num);
        updateHan = findViewById(R.id.update_eng);
        updateENg = findViewById(R.id.update_han);
        deleteNum = findViewById(R.id.delete_num);

        listView = findViewById(R.id.listView_word);
        dao = new WordDbDao(this);
        dao.open();

        Cursor cursor = dao.selectItemAll();
        adapter = new WordCursorAdapter(this, R.layout.dic_row, cursor, WordDbDao.SELECTION, new int[]{R.id.dic_num, R.id.dic_eng, R.id.dic_han},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
    }

    public void mInsertOnClick(View view) {
         String newEng = insertEng.getText().toString();
         String newHan = insertHan.getText().toString();
         Dic newDic = new Dic(newEng, newHan);

         dao.insertDic(newDic);
         adapter.changeCursor(dao.selectItemAll());
         insertEng.setText("");
         insertHan.setText("");
    }

    public void mUpdateOnClick(View view) {

    }


    public void mDeleteOnClick(View view) {
    }

    class WordCursorAdapter extends SimpleCursorAdapter{
        LayoutInflater mInflater;//전개자
        int mLayout;

        public WordCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
            mInflater = LayoutInflater.from(context);
            mLayout = layout;
        }

        //만약 데이터가 1000개라면 검색을 한번만 하고 그 값을 들고 있게 하기위해 ViewHolder 클래스를 만들어줌
        class ViewHolder {
            TextView tvid;
            TextView tvEng;
            TextView tvHan;
        }

        //제일 처음에 만들어서 보여주는 갯수만큼 호출됨 만약 13개면 13번만 호출하고 끝
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            //레이아웃을 전개함
            View item_row_layout = mInflater.inflate(mLayout, null);
            ViewHolder viewHolder = new ViewHolder();

            viewHolder.tvid = item_row_layout.findViewById(R.id.dic_num);
            viewHolder.tvEng = item_row_layout.findViewById(R.id.dic_eng);
            viewHolder.tvHan = item_row_layout.findViewById(R.id.dic_han);

            item_row_layout.setTag(viewHolder);
            return item_row_layout;
        }

        //내용만 바꿔서 화면에 올려줌
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();

            viewHolder.tvid.setText(cursor.getInt(0)+"");//index가 0이라도 ""넣어주면 스트링으로 들어감
            viewHolder.tvEng.setText(cursor.getString(1));
            viewHolder.tvHan.setText(cursor.getString(2));
        }
    }
}
