package kr.or.dgit.it.datapersitenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class OtherWebViewAssignment extends AppCompatActivity {

    WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_web_view_assignment);
        setTitle(getIntent().getStringExtra("title"));

        mWeb = (WebView) findViewById(R.id. web );
        mWeb.setWebViewClient(new MyWebClient());
        WebSettings set = mWeb.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        mWeb.loadUrl("http://www.naver.com");

    }

    public void mWebViewClick(View view) {
        switch(view.getId()){
            case R.id. btngo :
                EditText addr = (EditText) findViewById(R.id. address );
                String url = addr.getText().toString();
                mWeb.loadUrl(url);
                break;
                case R.id. btnback :
                    if (mWeb.canGoBack()) mWeb.goBack();
                    break;
                    case R.id. btnforward :
                        if (mWeb.canGoForward())mWeb.goForward();
                        break;
                        case R.id. btnlocal :
                            mWeb.loadUrl("file:///android_asset/test2.html");
                            break;
        }
    }
    class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }


    }
}
