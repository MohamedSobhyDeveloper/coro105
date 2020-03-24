package com.coro.coro105;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.github.ybq.android.spinkit.SpinKitView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);


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
            spinKit.setVisibility(View.GONE);
            messagetv.setVisibility(View.GONE);

        }, 3000);


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
        ab.setTitle("coro 105");
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
}
