package kr.or.dgit.it.datapersitenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarAssignment extends AppCompatActivity {

    ProgressBar progBar;
    ProgressBar progCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar_assignment);
        setTitle(getIntent().getStringExtra("title"));

        progBar =findViewById(R.id.progressBar);
        progCircle =findViewById(R.id.progressCircle);

    }

    public void mDecrementFirstClick(View view) {
        progBar.incrementProgressBy(-2);
    }

    public void mIncrementFirstClick(View view) {
        progBar.incrementProgressBy(2);
    }

    public void mDecrementSecondClick(View view) {
        progBar.incrementSecondaryProgressBy(-2);
    }

    public void mIncrementSecondClick(View view) {
        progBar.incrementSecondaryProgressBy(2);
    }

    public void mStartClick(View view) {
        progCircle.setVisibility(view.VISIBLE);
    }

    public void mStopClick(View view) {
        progCircle.setVisibility(view.INVISIBLE);
    }
}
