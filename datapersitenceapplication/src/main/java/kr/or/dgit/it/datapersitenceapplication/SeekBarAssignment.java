package kr.or.dgit.it.datapersitenceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarAssignment extends AppCompatActivity {

    SeekBar mSeekBar;
    TextView mVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_bar_assignment);
        setTitle(getIntent().getStringExtra("title"));

        mSeekBar=findViewById(R.id.seekBar);
        mVolume=findViewById(R.id.seekBarVolume);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mVolume.setText("Now Volume : "+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
