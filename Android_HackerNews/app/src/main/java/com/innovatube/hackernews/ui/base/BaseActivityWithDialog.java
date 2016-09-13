package com.innovatube.hackernews.ui.base;

import android.os.Bundle;

import com.afollestad.materialdialogs.MaterialDialog;
import com.innovatube.hackernews.R;
import com.innovatube.hackernews.utils.MaterialDialogUtils;

public abstract class BaseActivityWithDialog extends BaseActivity implements BaseViewInterface {

    protected MaterialDialog progressDialog, alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createProgressDialog();
        createAlertDialog();
        //setupDialogTitle();
    }

    @Override
    public void createProgressDialog() {
        progressDialog = MaterialDialogUtils.createProgress(this, getString(R.string.title_dialog));
    }

    @Override
    public void createAlertDialog() {
        alertDialog = MaterialDialogUtils.createAlertDialog(this, getString(R.string.title_dialog));
    }

    @Override
    public void showProgressDialog(boolean value) {
        if (value) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showAlertDialog(String errorMessage) {
        alertDialog.setContent(errorMessage);
        alertDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            alertDialog.dismiss();
        }

        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    //protected abstract void setupDialogTitle();

    @Override
    protected void onDestroy() {
        dismissDialog();
        super.onDestroy();
    }
}
