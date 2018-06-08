package kr.or.dgit.it.datapersitenceapplication;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class ActionBarActivity extends AppCompatActivity {
    SearchView sView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar);

        android.support.v7.app.ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setIcon(R.drawable.icon);




        String msg = "Build version : "+ Build.VERSION.SDK_INT;
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

        ImageView imageView = findViewById(R.id.imageView);
        registerForContextMenu(imageView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"서버전송");
        menu.add(0,1,0,"보관함에 보관");
        menu.add(0,2,0,"삭제");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                showToast("서버 전송이 선택되었습니다.");
                break;
            case 1:
                showToast("보관함에 보관이 선택되었습니다.");
                break;
            case 2:
                showToast("삭제가 선택되었습니다.");
                break;
        }
        return true;
    }

    private void showToast(String message){
        Toast t = Toast.makeText(this, message,Toast.LENGTH_LONG);
        t.show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Toast t = Toast.makeText(this, "HOME AS UP CLICK", Toast.LENGTH_LONG);
            t.show();
            return true;
        }

        if(item.getItemId()==0){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*MenuItem item1 = menu.add(0,0,0,"슬라이드 쇼");
        item1.setIcon(R.drawable.ic_menu_1);
        MenuItem item2 = menu.add(0,1,0,"앨범에 추가");
        item2.setIcon(R.drawable.ic_menu_2);*/

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder mb = (MenuBuilder) menu;
            mb.setOptionalIconsVisible(true);
        }
        MenuItem searchMenu = menu.findItem(R.id.menu_main_search);
        sView = (SearchView) searchMenu.getActionView();
        sView.setQueryHint("검색어를 입력하세요");
        sView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    private SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
            sView.setQuery("", false);
            sView.setIconified(true);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }

    };


}
