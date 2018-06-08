package com.scienceinfo.litepal;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.scienceinfo.litepal.Bean.Manager;

import org.litepal.tablemanager.Connector;

public class regActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private TextView back;
    private ScrollView scrollView;

    private int screenHeight = 0;//屏幕高度
    private int keyHeight = 0; //软件盘弹起后所占高度
    private float scale = 0.6f; //logo缩放比例
    private View service, content;
    private ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
        initData();


    }

    private void initData() {

        db = Connector.getDatabase();

    }

    private void initView() {
        final EditText userNameView = (EditText) findViewById(R.id.username);

        final EditText passwordView = (EditText) findViewById(R.id.password);
        final EditText againpasswordView = (EditText) findViewById(R.id.againpassword);
        back = (TextView) findViewById(R.id.back);

        Button regView = (Button) findViewById(R.id.btn_reg);
        logo = (ImageView) findViewById(R.id.logo);
        service = findViewById(R.id.service);
        content = findViewById(R.id.content);
        screenHeight = this.getResources().getDisplayMetrics().heightPixels; //获取屏幕高度
        keyHeight = screenHeight / 3;//弹起高度为屏幕高度的1/3

        scrollView = (ScrollView) findViewById(R.id.scrollView);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regActivity.this.finish();
            }
        });

        regView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user = userNameView.getText().toString().trim();

                final String password = passwordView.getText().toString().trim();
                final String againPass = againpasswordView.getText().toString().trim();

                if (!password.equals(againPass)) {
                    Toast.makeText(regActivity.this, "密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    Manager manager = new Manager();
                    manager.setM_name(user);

                    manager.setM_password(password);

                    if (manager.save()) {
                        Toast.makeText(regActivity.this, "存储成功", Toast.LENGTH_SHORT).show();
                        regActivity.this.finish();
                    } else {
                        Toast.makeText(regActivity.this, "存储失败", Toast.LENGTH_SHORT).show();
                        regActivity.this.finish();
                    }
                }

            }
        });


        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        scrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
              /* old是改变前的左上右下坐标点值，没有old的是改变后的左上右下坐标点值
              现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起*/
                if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
                    Log.e("wenzhihao", "up------>" + (oldBottom - bottom));
                    int dist = content.getBottom() - bottom;
                    if (dist > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(content, "translationY", 0.0f, -dist);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        zoomIn(logo, dist);
                    }
                    service.setVisibility(View.INVISIBLE);

                } else if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
                    Log.e("wenzhihao", "down------>" + (bottom - oldBottom));
                    if ((content.getBottom() - oldBottom) > 0) {
                        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(content, "translationY", content.getTranslationY(), 0);
                        mAnimatorTranslateY.setDuration(300);
                        mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
                        mAnimatorTranslateY.start();
                        //键盘收回后，logo恢复原来大小，位置同样回到初始位置
                        zoomOut(logo);
                    }
                    service.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * 缩小
     *
     * @param view
     */
    public void zoomIn(final View view, float dist) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();
        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, scale);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, scale);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", 0.0f, -dist);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }

    /**
     * f放大
     *
     * @param view
     */
    public void zoomOut(final View view) {
        view.setPivotY(view.getHeight());
        view.setPivotX(view.getWidth() / 2);
        AnimatorSet mAnimatorSet = new AnimatorSet();

        ObjectAnimator mAnimatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", scale, 1.0f);
        ObjectAnimator mAnimatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", scale, 1.0f);
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(view, "translationY", view.getTranslationY(), 0);

        mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
        mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
        mAnimatorSet.setDuration(300);
        mAnimatorSet.start();
    }

}

