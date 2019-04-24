package com.example.geek.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.geek.R;
import com.example.geek.base.BaseActivity;
import com.example.geek.presenter.CalendarP;
import com.example.geek.utils.DateUtil;
import com.example.geek.view.CalendarV;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class CalendarActivity extends BaseActivity<CalendarV,CalendarP> implements CalendarV {


    @BindView(R.id.view_calender)
    MaterialCalendarView mCalender;
    @BindView(R.id.toolBar)
    Toolbar mToolbar;

    CalendarDay mDate;

    @Override
    protected CalendarP initPresenter() {
        return new CalendarP();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_calendar;
    }


    @Override
    protected void initEventAndData() {
        mToolbar.setTitle("选择日期");
        setSupportActionBar(mToolbar);
        mCalender.state().edit()
                .setFirstDayOfWeek(Calendar.WEDNESDAY)
                .setMinimumDate(CalendarDay.from(2013, 5, 20))
                .setMaximumDate(CalendarDay.from(DateUtil.getCurrentYear(), DateUtil.getCurrentMonth(), DateUtil.getCurrentDay()))
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
        mCalender.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                mDate = date;
            }
        });
    }

    @OnClick(R.id.tv_calender_enter)
    void chooseDate() {
       /* if (mDate != null) {
            RxBus.getDefault().post(mDate);
        }*/
        finish();
    }


}
