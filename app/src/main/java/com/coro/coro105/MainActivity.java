package com.coro.coro105;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.coro.coro105.questions.DbHelper;
import com.coro.coro105.questions.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.question)
    TextView question;
    @BindView(R.id.radio1)
    RadioButton radio1;
    @BindView(R.id.radio0)
    RadioButton radio0;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.nextbtn)
    Button nextbtn;

    List<Question> questionList;
    int score = 0;
    int quid = 0;
    Question currentQuestion;
    @BindView(R.id.question_id)
    TextView questionId;
    @BindView(R.id.imageview)
    ImageView imageview;


    int quest1=0,quest2=0,quest3=0,quest4=0,quest5=0,quest6=0,quest7=0,quest8=0,quest9=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);



        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();
//        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);

        setQuestionView();


        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton answer = findViewById(radioGroup1.getCheckedRadioButtonId());

                if (answer != null) {

                    if (quid==1){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest1=currentQuestion.getScore();
                        }else {
                            quest1=0;
                        }
                    }else if (quid==2){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest2=currentQuestion.getScore();
                        }else {
                            quest2=0;
                        }
                    }else if (quid==3){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest3=currentQuestion.getScore();
                        }else {
                            quest3=0;
                        }
                    }else if (quid==4){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest4=currentQuestion.getScore();
                        }else {
                            quest4=0;
                        }
                    }else if (quid==5){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest5=currentQuestion.getScore();
                        }else {
                            quest5=0;
                        }
                    }else if (quid==6){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest6=currentQuestion.getScore();
                        }else {
                            quest6=0;
                        }
                    }else if (quid==7){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest7=currentQuestion.getScore();
                        }else {
                            quest7=0;
                        }
                    }else if (quid==8){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest8=currentQuestion.getScore();
                        }else {
                            quest8=0;
                        }
                    }else if (quid==9){
                        if (currentQuestion.getAnswer().contentEquals(answer.getText())) {
                            quest9=currentQuestion.getScore();
                        }else {
                            quest9=0;
                        }
                    }


                    if (quid < questionList.size()) {
                        currentQuestion = questionList.get(quid);
                        setQuestionView();
                    } else {

                        score=quest1+quest2+quest3+quest4+quest5+quest6+quest7+quest8+quest9;

                        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score", score);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                    radio1.setChecked(false);
                    radio0.setChecked(false);
                    radioGroup1.clearCheck();
                }
            }
        });







    }

    @SuppressLint("SetTextI18n")
    private void setQuestionView() {
        question.setText(currentQuestion.getQuestion());
        imageview.setImageResource(currentQuestion.getImage());
        questionId.setText(" رقم السؤال /"+" "+currentQuestion.getId());
        radio0.setText(currentQuestion.getOptA());
        radio1.setText(currentQuestion.getOptB());
        quid++;
    }

    @OnClick(R.id.nextbtn)
    public void onViewClicked() {

      if (quid!=1){
          currentQuestion = questionList.get(quid-2);
          getprevQuestionView();


      }

    }

    private void getprevQuestionView() {
        question.setText(currentQuestion.getQuestion());
        imageview.setImageResource(currentQuestion.getImage());
        questionId.setText(" رقم السؤال /"+" "+currentQuestion.getId());
        radio0.setText(currentQuestion.getOptA());
        radio1.setText(currentQuestion.getOptB());
        quid--;

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
        ab.setTitle("coro105");
        ab.setMessage("هل تريد الخروج من التطبيق ؟");
        ab.setPositiveButton("نعم", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //if you want to kill app . from other then your main avtivity.(Launcher)
                Process.killProcess(Process.myPid());
                System.exit(1);

                //if you want to finish just current activity

                finish();
            }
        });
        ab.setNegativeButton("لا", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ab.show();
    }
}
