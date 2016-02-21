package org.dalol.instagramapidemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * @author Filippo Engidashet <filippo.eng@gmail.com>
 * @version 1.0.0
 * @since 2/20/2016
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        Toolbar toolbar = getToolBarView();
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }

        configViews(getIntent(), savedInstanceState);
    }

    protected void configViews(Intent intent, Bundle savedInstanceState) {

    }

    protected abstract Toolbar getToolBarView();

    protected abstract int getContentView();
}
