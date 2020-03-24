package com.coro.coro105;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.greenview)
    TextView greenview;
    @BindView(R.id.yellowview)
    TextView yellowview;
    @BindView(R.id.redview)
    TextView redview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");

        if (score<=4){
           greenview.setVisibility(View.VISIBLE);
        }else if (score==5){
          yellowview.setVisibility(View.VISIBLE);
        }else {
            redview.setVisibility(View.VISIBLE);
        }
    }
}
