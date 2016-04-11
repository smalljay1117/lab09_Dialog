package com.example.android.lab09_dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements DialogInterface.OnClickListener,MyDialogFragment.CallBack {

    private TextView m_tv_message;
    private int loginCount;

//    public int getLoginCount() {
//        return loginCount;
//    }
//
//    public void setLoginCount(int loginCount) {
//        this.loginCount = loginCount;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        m_tv_message = (TextView) findViewById(R.id.tv_message);
    }

    public void clickAlertDialog(View view) {
        new AlertDialog.Builder(this)
                .setMessage("笨阿母")
                .setPositiveButton("正解", this)
                .show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        m_tv_message.setText("皮曦兒 = 笨阿母");
    }

    public void clickAlertDialogYesNo(View view) {
        AlertDialogYesNoListener listener = new AlertDialogYesNoListener();
        new AlertDialog.Builder(this)
                .setMessage("正妹")
                .setPositiveButton("醒醒吧", listener)
                .setNegativeButton("蛤?你說什麼?", listener)
                .show();
    }

    private class AlertDialogYesNoListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    m_tv_message.setText("醒醒吧");
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    m_tv_message.setText("蛤?你說什麼?");
                    break;
            }
        }
    }

    public void clickAlertDialogYesNoCancel(View view) {
        new AlertDialog.Builder(this)
                .setMessage("我是正妹!!")
                .setPositiveButton("醒醒吧!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("醒醒吧!!");
                    }
                })
                .setNegativeButton("你是症妹", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("你是症妹");
                    }
                })
                .setNeutralButton("ㄌㄩㄝ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("ㄌㄩㄝ");
                    }
                })
                .show();
    }

    public void clickAlertDialogItems(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar)
                .setTitle("常說的話")
                .setItems(response, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[which]);
                    }
                })
                .show();
    }

    public void clickAlertDialogMultiChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        final boolean[] selected = new boolean[response.length];
        new AlertDialog.Builder(this)
                .setTitle("常說的話")
                .setMultiChoiceItems(response, selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        selected[which] = isChecked;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringBuilder result = new StringBuilder();
                        for (int i = 0; i < selected.length; i++) {
                            if (selected[i]) {
                                result.append(response[i]).append("\n");
                            }
                        }
                        m_tv_message.setText(result);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    private int mChoice;

    public void clickAlertDialogSingleChoice(View view) {
        final String[] response = getResources().getStringArray(R.array.response);
        new AlertDialog.Builder(this)
                .setTitle("常說的話")
                .setSingleChoiceItems(response, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mChoice = which;
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText(response[mChoice]);
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_tv_message.setText("無語");
                    }
                })
                .show();
    }

    public void clickMyDialogFragment(View view) {
        DialogFragment dialog = new MyDialogFragment();
        dialog.show(getSupportFragmentManager(), "MyDialogFragment");
    }

    @Override
     public void call(CharSequence username, int which) {
        switch (which) {
            case DialogInterface.BUTTON_POSITIVE:
                loginCount++;
                m_tv_message.setText("次數 " + loginCount + " 歡迎光臨 " + username);
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                m_tv_message.setText("登入取消");
                break;
        }
    }
}