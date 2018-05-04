package kr.or.dgit.it.datapersitenceapplication;

import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SpannableActivity extends AppCompatActivity {

    TextView spanView;
    TextView htmlView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable);
        setTitle(getIntent().getStringExtra("title"));

        spanView = findViewById(R.id.spanView);
        htmlView = findViewById(R.id.htmlView);

        spanView.setMovementMethod(new ScrollingMovementMethod());

        String data="복수초 \n img \n 이른봄 설산에서 만나는 복수초는 모든 야생화 찍사들의 로망이 아닐까 싶다.";

        SpannableStringBuilder builder = new SpannableStringBuilder(data);
        int start=data.indexOf("img");
        if(start>-1){
            int end=start+"img".length();
            Drawable dr = ResourcesCompat.getDrawable(getResources(),R.drawable.img1,null);
            dr.setBounds(0,0,dr.getIntrinsicWidth(), dr.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(dr);
            builder.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        start=data.indexOf("복수초");
        if(start>-1){
            int end=start+"복수초".length();
            StyleSpan styleSpan=new StyleSpan(Typeface.BOLD);
            RelativeSizeSpan sizeSpan=new RelativeSizeSpan(2.0f);
            builder.setSpan(styleSpan,start,end+2,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(sizeSpan, start, end+2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        spanView.setText(builder);

        String html="<font color='RED'>얼레지</font> <br/><img src='img1'/><br/>곰배령에서 만난 봄꽃";
        htmlView.setText(Html.fromHtml(html, new MyImageGetter(), null));
    }
    class MyImageGetter implements Html.ImageGetter {
        @Override
        public Drawable getDrawable(String source) {
            if(source.equals("img1")){
                Drawable dr = ResourcesCompat.getDrawable(getResources(),R.drawable.img2,null);
                dr.setBounds(0,0,dr.getIntrinsicWidth(),dr.getIntrinsicHeight());
                return dr;
            }

            return null;
        }
    }
}