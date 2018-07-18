package com.hcx.framework.cxmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hcx.framework.cxmvplibrary.utils.ActivitySwitcher;

public class MainAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);

        ActivitySwitcher.startFragment(MainAct.this,DemoFrg.class);
    }
}
