package kr.or.dgit.it.datapersitenceapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarAssignment extends AppCompatActivity {

    int mYear, mMonth, mDay, mHour, mMinute;
    TextView mTxtDate;
    TextView mTxtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_assignment);
        setTitle(getIntent().getStringExtra("title"));

        mTxtDate = (TextView) findViewById(R.id. txtdate );
        mTxtTime = (TextView) findViewById(R.id. txttime );
        Calendar cal = new GregorianCalendar();
        mYear = cal.get(Calendar. YEAR );
        mMonth = cal.get(Calendar. MONTH );
        mDay = cal.get(Calendar. DAY_OF_MONTH );
        mHour = cal.get(Calendar. HOUR_OF_DAY );
        mMinute = cal.get(Calendar. MINUTE );

        updateNow();

    }

    public void mCalendarClick(View view) {
        switch(view.getId()){
            case R.id. btnchangedate :
                new DatePickerDialog(CalendarAssignment.this, mDateSetListener, mYear, mMonth, mDay).show();
                break;
                case R.id. btnchangetime :
                    new TimePickerDialog(CalendarAssignment.this, mTimeSetListener, mHour, mMinute, false).show();
                    break;
        }

    }

    private void updateNow() {
        String dateTxt = String. format ("%d/%d/%d", mYear, mMonth+1, mDay);
        mTxtDate.setText(dateTxt);
        String timeTxt = String. format ("%d:%d", mHour, mMinute);
        mTxtTime.setText(timeTxt);
    }

    DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth; updateNow();
        }
    };
    TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mHour = hourOfDay;
            mMinute = minute; updateNow();
        }
    };

}
