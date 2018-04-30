package kr.or.dgit.it.datapersitenceapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

public class FileIOActivity extends AppCompatActivity {

    EditText mEt;

    boolean fileReadPermisson;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);
        setTitle(getIntent().getStringExtra("title"));

        mEt = findViewById(R.id.fileName);

        permissionOnCheck();
    }

    public void mOnclickBtn(View view) {
        String content = mEt.getText().toString();

        if(fileReadPermisson && fileWritePermission){
            FileWriter writer;
            try{

                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp";
                File dir = new File(dirPath);

                showToast(dirPath+"왜 안되는 건가요?");

                if(!dir.exists()){
                    dir.mkdir();
                }

                File file = new File(dir+"/myfile.txt");

                if(!file.exists()){
                    file.createNewFile();
                }

                writer = new FileWriter(file,true);
                writer.write(content);
                writer.flush();
                writer.close();

                Intent intent = new Intent(this, ReadFileActivity.class);
                intent.putExtra("filePath", file.getAbsolutePath());
                startActivity(intent);

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            showToast("permission이 부여되지 않아 기능을 실행할 수 없습니다.");
        }
    }

    private void showToast(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();
    }
    public void permissionOnCheck(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            fileReadPermisson=true;
        }
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
            fileWritePermission=true;
        }
        if(!fileReadPermisson || !fileWritePermission){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 200 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                fileReadPermisson = true;
            }
            if(grantResults[1]== PackageManager.PERMISSION_GRANTED){
                fileWritePermission = true;
            }
        }
    }
}
