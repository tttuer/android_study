package com.example.pca.mylocationlogger;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pca on 2016-10-27.
 */
public class DBManger extends SQLiteOpenHelper {

    //DBHelper 생성자로 관리할 DB이름과 버전 정보를 입력받음
    public DBManger(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        //새로운 테이블 생성
        /*이름은 MyGPS이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
          latitude 더블형 컬럼, longitude 더블형 컬럼으로 구성된 테이블 생성 */
        db.execSQL("CREATE TABLE MyGPS (_id INTEGER PRIMARY KEY AUTOINCREMENT, latitude DOUBLE, longitude DOUBLE");
    }

    //DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(double latitude, double longitude){
        //읽고 쓰기가 가능하게 DB열기
        SQLiteDatabase db = getWritableDatabase();
        //DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO MONEYBOOK VALUES(null, '" + latitude + "', " + longitude + "');");
        db.close();
    }

    public String getResult(){
        //읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        //DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM MyGPS",null);
        while(cursor.moveToNext()){
            result += cursor.getString(0)
                    + ". latitude : "
                    + cursor.getDouble(1)
                    + ", longitude : "
                    + cursor.getDouble(2)
                    + "\n";
        }

        return result;
    }
}
