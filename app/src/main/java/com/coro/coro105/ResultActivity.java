package com.coro.coro105;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.github.ybq.android.spinkit.SpinKitView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ResultActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, OnCheckedChangeListener {

    @BindView(R.id.greenview)
    TextView greenview;
    @BindView(R.id.yellowview)
    TextView yellowview;
    @BindView(R.id.redview)
    TextView redview;
    @BindView(R.id.nextbtn)
    Button nextbtn;
    @BindView(R.id.spin_kit)
    SpinKitView spinKit;
    @BindView(R.id.messagetv)
    TextView messagetv;
    RadioGroup radioGroup;
    LinearLayout linearLayout;
    @BindView(R.id.imageviewer)
    ViewPager imageviewer;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 6000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);


        ImageAdapter imageAdapter = new ImageAdapter(this);
        imageviewer.setAdapter(imageAdapter);
        final Handler handler = new Handler();
        final Runnable Update = () -> {
            if (currentPage == 5-1) {
                currentPage = 0;
            }
            imageviewer.setCurrentItem(currentPage++, true);
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);



        linearLayout = (LinearLayout) findViewById(R.id.linear);
        radioGroup = (RadioGroup) findViewById(R.id.page_group);
        radioGroup.setOnCheckedChangeListener(this);

        imageviewer.addOnPageChangeListener(this);

        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");


        new Handler().postDelayed(() -> {
            if (score <= 4) {
                greenview.setVisibility(View.VISIBLE);
            } else if (score == 5) {
                yellowview.setVisibility(View.VISIBLE);

            } else {
                redview.setVisibility(View.VISIBLE);

            }

            nextbtn.setVisibility(View.VISIBLE);
            imageviewer.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            spinKit.setVisibility(View.GONE);
            messagetv.setVisibility(View.GONE);


        }, 3000);

        redview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialPhoneNumber(105);
            }
        });
    }

    @OnClick(R.id.nextbtn)
    public void onViewClicked() {

        Intent intent = new Intent(ResultActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab = new AlertDialog.Builder(ResultActivity.this);
        ab.setTitle("coro105");
        ab.setMessage("هل تريد الخروج من التطبيق ؟");
        ab.setPositiveButton("نعم", (dialog, which) -> {
            dialog.dismiss();
            //if you want to kill app . from other then your main avtivity.(Launcher)
            Process.killProcess(Process.myPid());
            System.exit(1);

            //if you want to finish just current activity

            finish();
        });
        ab.setNegativeButton("لا", (dialog, which) -> dialog.dismiss());

        ab.show();
    }

    public void dialPhoneNumber(int phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        // when current page change -> update radio button state
        int radioButtonId = radioGroup.getChildAt(position).getId();
        radioGroup.check(radioButtonId);
    }

    public void onPageScrollStateChanged(int state) {
    }

    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        // when checked radio button -> update current page
        RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(checkedId);
        // get index of checked radio button
        int index = radioGroup.indexOfChild(checkedRadioButton);

        // update current page
        imageviewer.setCurrentItem((index), true);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
