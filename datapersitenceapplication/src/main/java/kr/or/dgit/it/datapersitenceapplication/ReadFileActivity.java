package kr.or.dgit.it.datapersitenceapplication;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadFileActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);
        String filePath = getIntent().getStringExtra("filePath");

        textView = findViewById(R.id.fileResult);

        File file = new File(filePath);

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuffer buffer = new StringBuffer();
            String line;

            while((line=reader.readLine())!=null) {
                buffer.append(line);
            }
            textView.setText(buffer.toString());
            reader.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
