package com.example.today;

import android.content.Intent;
import android.os.Bundle;

public interface ILifeCircle {
    void onCread(Bundle savedInstanceState, Intent intent, Bundle getArguments);
    void onActivityCreated(Bundle savedInstanceState, Intent intent, Bundle getArguments);
    void onStart();
    void onResume();
    void onPause();
    void onStop();
    void onDsitroy();
    void destroyView();
    void onViewDestory();
    void onNewIntent(Intent intent);
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onSaveInstanceState(Bundle bundle);
    void attachView(IMvpView iMvpView);
}
