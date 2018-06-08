package com.scienceinfo.litepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scienceinfo.litepal.Bean.Curriculum;

public class UpdateActivity extends AppCompatActivity {

    private EditText c_idView;
    private EditText c_nameView;
    private EditText c_infoView;
    private Button update;
    private TextView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initView();
        initData();
    }

    private void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateActivity.this.finish();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c_id = c_idView.getText().toString().trim();
                String c_name = c_nameView.getText().toString().trim();
                String c_info = c_infoView.getText().toString().trim();

                Curriculum curriculum=new Curriculum();
                curriculum.setC_name(c_name);
                curriculum.setC_info(c_info);
                int update = curriculum.update(Long.parseLong(c_id));
                if(update==1){
                    Toast.makeText(UpdateActivity.this,"更新成功",Toast.LENGTH_SHORT).show();
                    UpdateActivity.this.finish();
                }else if(update==0){
                    Toast.makeText(UpdateActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
                    UpdateActivity.this.finish();
                }


            }
        });



    }

    private void initView() {
        c_idView = (EditText) findViewById(R.id.c_id);
        c_nameView = (EditText) findViewById(R.id.c_name);
        c_infoView = (EditText) findViewById(R.id.c_info);
        update = (Button) findViewById(R.id.update);
        back = (TextView) findViewById(R.id.back);

    }
}
