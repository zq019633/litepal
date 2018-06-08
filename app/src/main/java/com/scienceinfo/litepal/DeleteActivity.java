package com.scienceinfo.litepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scienceinfo.litepal.Bean.Curriculum;

import org.litepal.crud.DataSupport;

public class DeleteActivity extends AppCompatActivity {

    private EditText c_idView;
    private Button delete;
    private TextView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        initView();
        initData();
    }

    private void initData() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteActivity.this.finish();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String c_id = c_idView.getText().toString().trim();
                int deleteCount = DataSupport.delete(Curriculum.class, Long.parseLong(c_id));
                if(deleteCount==1){
                    Toast.makeText(DeleteActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                    DeleteActivity.this.finish();
                }else{
                    Toast.makeText(DeleteActivity.this,"删除失败",Toast.LENGTH_SHORT).show();
                    DeleteActivity.this.finish();
                }

            }
        });

    }

    private void initView() {
        c_idView = (EditText) findViewById(R.id.c_id);
        delete = (Button) findViewById(R.id.delete);
        back = (TextView) findViewById(R.id.back);

    }
}
