package me.chunyu.spike.wcl_activity_launchmode_demo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 测试B
 * Created by wangchenlong on 16/2/17.
 */
public class TestBActivity extends AppCompatActivity {
    private static final String TAG = "DEBUG-WCL: " + TestBActivity.class.getSimpleName();

    public static final String EXTRA_DATA = "extra_data";
    private static final int REQUEST_CODE = 0;

    @BindView(R.id.main_tv_text) TextView mTvText;
    @BindView(R.id.main_b_jump) Button mBJump;
    @BindView(R.id.main_b_jump_2) Button mBJump2;
    @BindView(R.id.main_b_jump_3) Button mBJump3;
    @BindView(R.id.main_b_jump_4) Button mBJump4;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        mTvText.setText(String.valueOf("Activity B"));
        mBJump.setText(String.valueOf("创建[Activity C]"));
        mBJump .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestBActivity.this, TestCActivity.class));
            }
        });

        mBJump2.setVisibility(View.VISIBLE);
        mBJump2.setText(String.valueOf("创建自己[startActivity]"));
        mBJump2 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestBActivity.this, TestBActivity.class));
            }
        });

        mBJump3.setVisibility(View.VISIBLE);
        mBJump3.setText(String.valueOf("创建自己[startActivityForResult]"));
        mBJump3 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(TestBActivity.this, TestBActivity.class), REQUEST_CODE);
            }
        });

        mBJump4.setVisibility(View.VISIBLE);
        mBJump4.setText(String.valueOf("关闭自己"));
        mBJump4  .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_DATA, "我是B!");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Log.e(TAG, "onCreate "+getTaskId());
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult ");
        if (requestCode == 0 && resultCode == RESULT_OK) {
            String extra = data.getStringExtra(EXTRA_DATA);
            Toast.makeText(getApplicationContext(), extra, Toast.LENGTH_SHORT).show();
        }
    }


    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Log.e(TAG, "onNewIntent");
    }

    @Override protected void onStart() {
        super.onStart();

        Log.e(TAG, "onStart");
    }

    @Override protected void onResume() {
        super.onResume();

        Log.e(TAG, "onResume");
    }

    @Override protected void onPause() {
        super.onPause();

        Log.e(TAG, "onPause");
    }

    @Override protected void onStop() {
        super.onStop();

        Log.e(TAG, "onStop");
    }

    @Override protected void onDestroy() {
        super.onDestroy();

        Log.e(TAG, "onDestroy");
    }
}
