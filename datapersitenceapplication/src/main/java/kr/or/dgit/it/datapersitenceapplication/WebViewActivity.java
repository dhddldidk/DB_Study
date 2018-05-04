package kr.or.dgit.it.datapersitenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setTitle(getIntent().getStringExtra("title"));

        webView = findViewById(R.id.webView);
        WebSettings settings=webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/test.html");

        webView.addJavascriptInterface(new JavascriptTest(), "android");
        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new MyWebChrome());
    }



    public void mLineChartClick(View view) {
        webView.loadUrl("javascript:lineChart()");
    }

    public void mBarChartClick(View view) {
        webView.loadUrl("javascript:barChart()");
    }

    public void mDaumClick(View view) {
        webView.loadUrl("http://m.daum.net");
    }



    public void mBackBtnClick(View view) {
        if(webView.canGoBack()){
            webView.goBack();
        }
    }

    public void mGoBtnClick(View view) {
        if(webView.canGoForward()){
            webView.goForward();
        }
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast t = Toast.makeText(WebViewActivity.this, url, Toast.LENGTH_LONG);
            t.show();

            return super.shouldOverrideUrlLoading(view,url);
        }
    }

    private class JavascriptTest {

        @JavascriptInterface
        public String getChartData(){
            StringBuffer buffer=new StringBuffer();
            buffer.append("[");
            for(int i=0; i<14; i++){
                buffer.append("["+i+","+Math.sin(i)+"]");
                Log.d("kkang",i+","+Math.sin(i));
                if(i<13)buffer.append(",");
            }
            buffer.append("]");
            return buffer.toString();
        }
    }

    private class MyWebChrome extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast t=Toast.makeText(WebViewActivity.this, message, Toast.LENGTH_LONG);
            t.show();
            result.confirm();
            return true;
        }
    }
}
