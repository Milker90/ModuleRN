package com.library.modulern;

import android.app.Activity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;

import java.util.Arrays;

public class RRTestNativeActivity extends Activity implements DefaultHardwareBackBtnHandler {
    private ReactRootView reactRootView;
    private ReactInstanceManager mReactInstanceManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);

        FrameLayout rootView = findViewById(R.id.react_test_view);

        // 创建ReactRootView视图
        reactRootView = new ReactRootView(this);
        // 加载JavaScript bundle文件的路径
        String jsBundleFile = "index.android.bundle";
        // 使用JavaScript bundle文件的路径加载React应用程序
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setInitialLifecycleState(LifecycleState.BEFORE_CREATE)
                .setBundleAssetName(jsBundleFile)
                .setJSMainModulePath("index")
                .addPackages(Arrays.<ReactPackage>asList(
                        new MainReactPackage()
                ))
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .build();
        reactRootView.startReactApplication(mReactInstanceManager, "TestComponent", null);

        // 将ReactRootView视图添加到布局中
        rootView.addView(reactRootView);
    }


    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
            reactRootView.unmountReactApplication();
            reactRootView = null; // 释放引用
            mReactInstanceManager = null; // 释放引用
        }
    }
}
