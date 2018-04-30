package kr.or.dgit.it.db_study;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kr.or.dgit.it.db_study.database.DBHelper;

public class BasicSQLiteActivity extends AppCompatActivity implements View.OnClickListener {
    EditText titleView;
    EditText contentView;
    Button button;
    Button buttonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_sqlite);
        setTitle(getIntent().getStringExtra("title"));
        titleView = findViewById(R.id.type_in_title);
        contentView = findViewById(R.id.type_in_memo);
        button = findViewById(R.id.btnSubmit);
        buttonList = findViewById(R.id.btnList);

        button.setOnClickListener(this);
        buttonList.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSubmit){
            String title = titleView.getText().toString();
            String content = contentView.getText().toString();

            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();
            db.execSQL("insert into tb_memo (title, content) values (?,?)", new String[]{title, content});
            db.close();

            Intent intent = new Intent(this, ReadDBActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btnList){
            Intent intent = new Intent(this, ReadDBActivity.class);
            startActivity(intent);
        }

    }
}
