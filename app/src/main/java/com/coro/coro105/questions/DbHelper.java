package com.coro.coro105.questions;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JoseThomas on 3/31/2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    //DB version, table and database name
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "corodb";
    private static final String DB_TABLE = "corotable";

    //table column names
    private static final String KEY_ID = "id";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer";
    private static final String KEY_OPTA = "optA";
    private static final String KEY_OPTB = "optB";
    private static final String KEY_Score = "score";


    private SQLiteDatabase dbase;
    private int rowCount = 0;

    public DbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT, %s TEXT, %s TEXT,%s INTEGER )", DB_TABLE, KEY_ID, KEY_QUES, KEY_ANSWER, KEY_OPTA, KEY_OPTB,KEY_Score);
        Log.d("TaskDBHelper", "Query to form table" + sqlQuery);
        db.execSQL(sqlQuery);
        addQuestions();
    }

    private void addQuestions() {
        Question q1 = new Question("هل أنت أحد العاملين بالقطاع أو العزل الصحي ؟ ", "نعم", "لا", "نعم",2);
        this.addQuestionToDB(q1);
        Question q2 = new Question("هل تعاني من ارتفاع درجة الحرارة أكثر من 38 ؟", "نعم", "لا", "نعم",2);
        this.addQuestionToDB(q2);
        Question q3 = new Question("هل تعاني من سعال شديد أو متزايد ؟ ", "نعم", "لا","نعم",2);
        this.addQuestionToDB(q3);
        Question q4 = new Question("هل تعاني من احتقان شديد بالحلق ؟  ", "نعم", "لا", "نعم",1);
        this.addQuestionToDB(q4);
        Question q5 = new Question("هل تعاني من القيئ أو اسهال ؟", "نعم", "لا", "نعم",0);
        this.addQuestionToDB(q5);
        Question q6 = new Question("هل تعاني من مرض مزمن  :ضغط  /سكر  /قلب  /ربو أو أمراض مناعية ؟", "نعم", "لا", "نعم",1);
        this.addQuestionToDB(q6);
        Question q7 = new Question("هل قمت بزيارة أماكن سياحية أو دينية مثال:( قادم من العمرة / ايران / أوروبا / شرم الشيخ...  )؟  ", "نعم", "لا", "نعم",5);
        this.addQuestionToDB(q7);
        Question q8 = new Question("هل قمت بزيارة مركز صحي ثبت فيه وجود كورونا مثال: (مستشفى  /عيادة  /معمل تحاليل أو وحدة صحية)؟ ", "نعم", "لا", "نعم",3);
        this.addQuestionToDB(q8);
        Question q9 = new Question("هل قمت بمخالطه لحالة إلتهاب تنفسي حاد ؟ ", "نعم", "لا", "نعم",4);
        this.addQuestionToDB(q9);

    }

    public void addQuestionToDB(Question q){
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, q.getQuestion());
        values.put(KEY_ANSWER,q.getAnswer());
        values.put(KEY_OPTA,q.getOptA());
        values.put(KEY_OPTB,q.getOptB());
        values.put(KEY_Score,q.getScore());

        dbase.insert(DB_TABLE, null, values);
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<Question>();

        dbase = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM "+DB_TABLE;
        Cursor cursor = dbase.rawQuery(selectQuery,null);
        rowCount = cursor.getCount();

        if(cursor.moveToFirst()){
            do{
                Question q = new Question();
                q.setId(cursor.getInt(0));
                q.setQuestion(cursor.getString(1));
                q.setAnswer(cursor.getString(2));
                q.setOptA(cursor.getString(3));
                q.setOptB(cursor.getString(4));
                q.setScore(cursor.getInt(5));

                questionList.add(q);

            }while (cursor.moveToNext());
        }
        return questionList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
        onCreate(db);
    }

    public int getRowCount(){
        return rowCount;
    }
}
