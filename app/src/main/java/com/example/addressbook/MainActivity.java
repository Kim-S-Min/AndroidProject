package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.lang.reflect.Method;


public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Cursor cursor;
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("select * from ad_db", null);
        startManagingCursor(cursor);
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_2,
                cursor,
                new String[]{ "name" },
                new int[] { android.R.id.text1 });
        ListView list = findViewById(R.id.listView);
        list.setAdapter(adapter);

        registerForContextMenu(list);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        try {
            Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", boolean.class);
            method.setAccessible(true);
            method.invoke(menu, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        searchView = (androidx.appcompat.widget.SearchView)menuItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.app_name));
        searchView.setOnQueryTextListener(queryTextListener);

        return true;
    }
    SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String s) {
            searchView.setQuery("", false);
            searchView.setIconified(true);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String s) {
            return false;
        }
    };


    //  1.
    //  메뉴에서 insert 선택시 add_book.xml로 이동 -> 이름, 전화번호, 주소 등을 입력 후
    //      저장/취소 버튼으로 activity.xml로 돌아오기


    //  2.
    //  메뉴에서 upload 선택후 해당 list내용 클릭시 내용변경을 하는 xml? 또는 대화상자? 로 이동
    //      저장/취소 버튼으로 activity.xml로 돌아오기


    //  3.
    //  메뉴에서 delete 선택시 list내용 우측에 체크박스가 생성 -> 체크된 체크박스들을 가진 list는 삭제시 삭제
    //      삭제/취소 버튼으로 activity.xml로 돌아오기
}