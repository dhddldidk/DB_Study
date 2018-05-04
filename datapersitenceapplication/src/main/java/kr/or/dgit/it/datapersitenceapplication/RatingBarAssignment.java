package kr.or.dgit.it.datapersitenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

public class RatingBarAssignment extends AppCompatActivity {

    RatingBar mRating;
    TextView mRateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar_assignment);
        setTitle(getIntent().getStringExtra("title"));

        mRating = (RatingBar) findViewById(R.id. ratingbar01 );
        mRateText = (TextView) findViewById(R.id. ratetext );
        mRating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mRateText.setText("Now Rate : " + rating); } });

    }
}
