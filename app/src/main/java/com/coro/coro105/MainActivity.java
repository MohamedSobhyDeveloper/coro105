package com.coro.coro105;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.coro.coro105.questions.DbHelper;
import com.coro.coro105.questions.Question;

import java.util.Collections;
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


    }

    private void setQuestionView(){
        question.setText(currentQuestion.getQuestion());
        radio0.setText(currentQuestion.getOptA());
        radio1.setText(currentQuestion.getOptB());
        quid++;
    }

    @OnClick(R.id.nextbtn)
    public void onViewClicked() {
        RadioButton answer = (RadioButton)findViewById(radioGroup1.getCheckedRadioButtonId());

        if (answer==null){
            Toast.makeText(this,"من فضلك اختر اجابة",Toast.LENGTH_SHORT).show();
        }else {

            if(currentQuestion.getAnswer().equals(answer.getText())){
                score=score+currentQuestion.getScore();
                Log.d("Score", "Your score: "+score);
            }

            if(quid<9){
                currentQuestion = questionList.get(quid);
                setQuestionView();
            }else{
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                Bundle b = new Bundle();
                b.putInt("score",score);
                intent.putExtras(b);
                startActivity(intent);
                finish();
            }
            radio1.setChecked(false);
            radio0.setChecked(false);
            radioGroup1.clearCheck();
        }



    }
}
