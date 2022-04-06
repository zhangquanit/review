package com.review.fragment.retain;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.review.fragment.R;


public class RetainHomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retain_state);
    }
    public void openAsyncTaskAndSaveInstanceState(View view)
    {
        Intent intent = new Intent(this, SavedInstanceStateUsingActivity.class);
        startActivity(intent);
    }

    public void openFragmentRetainDataActivity(View view)
    {
        Intent intent = new Intent(this, FragmentRetainDataActivity.class);
        startActivity(intent);
    }

    public void openConfigChangesTestActivity(View view)
    {
        Intent intent = new Intent(this, ConfigChangesTestActivity.class);
        startActivity(intent);
    }

    public void openFixProblemsActivity(View view)
    {
        Intent intent = new Intent(this, FixProblemsActivity.class);
        startActivity(intent);
    }
}
