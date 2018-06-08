package com.scienceinfo.litepal;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scienceinfo.litepal.Bean.Curriculum;

import org.litepal.tablemanager.Connector;

public class CourseControlActivity extends AppCompatActivity {
    private Button addCourse;
    private EditText c_nameView;
    private EditText c_infoView;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        initView();
        initData();

    }

    private void initData() {
        db = Connector.getDatabase();
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c_name = c_nameView.getText().toString().trim();
                String c_info = c_infoView.getText().toString().trim();


                Curriculum curriculum=new Curriculum();
                curriculum.setC_name(c_name);
                curriculum.setC_info(c_info);


                if (curriculum.save()) {
                    Toast.makeText(CourseControlActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                    CourseControlActivity.this.finish();
                } else {
                    Toast.makeText(CourseControlActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
                    CourseControlActivity.this.finish();
                }
            }
        });
    }

    private void initView() {
         addCourse = (Button) findViewById(R.id.add);
         c_nameView = (EditText) findViewById(R.id.c_name);
         c_infoView = (EditText) findViewById(R.id.c_info);
    }
}
