package com.dsa.grupo2.CoronavirusGameAndroid;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

public class LoadingDialog {
    private Activity activity;
    private AlertDialog alertDialog;

    LoadingDialog(Activity myActivity){
        activity = myActivity;

    }
    void startLoadingDialog(){
        LayoutInflater inflater = activity.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(false);

        alertDialog = builder.create();
        alertDialog.show();
    }
    void dismissDialog(){
        alertDialog.dismiss();
    }
}
