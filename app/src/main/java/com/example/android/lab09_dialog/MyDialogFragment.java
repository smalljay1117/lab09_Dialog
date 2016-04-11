package com.example.android.lab09_dialog;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyDialogFragment extends DialogFragment {

    private EditText m_et_username;

    public MyDialogFragment() {
        // Required empty public constructor
    }

    public interface CallBack {//定义一个与Activity通信的接口，使用该DialogFragment的Activity须实现该接口

        void call(CharSequence username, int which);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_my_dialog, null);
        m_et_username = (EditText) view.findViewById(R.id.et_username);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        int count = ((MainActivity)getActivity()).getLoginCount();
//                        count++;
//                        ((MainActivity)getActivity()).setLoginCount(count);
//                        String message = "次數 " + count + " 歡迎光臨 " + m_et_username.getText();

//                        DialogFragmentDataImp imp = (DialogFragmentDataImp) getActivity();
//                        imp.showMessage(message);

//                        ((TextView) getActivity().findViewById(R.id.tv_message)).setText(message);

                        CharSequence username = m_et_username.getText();
                        ((MyDialogFragment.CallBack) getActivity()).call(username, which);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        DialogFragmentDataImp imp = (DialogFragmentDataImp) getActivity();
//                        imp.showMessage("登入取消");

//                        ((TextView) getActivity().findViewById(R.id.tv_message)).setText("登入取消");

                        ((MyDialogFragment.CallBack) getActivity()).call(null, which);
                    }
                });
        return builder.create();
    }
}

