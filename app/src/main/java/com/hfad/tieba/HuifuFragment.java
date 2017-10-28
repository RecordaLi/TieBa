package com.hfad.tieba;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by lgy on 2017/10/24.
 */

public class HuifuFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.huifu_comment,null);
        final EditText editText = (EditText)view.findViewById(R.id.commentEditText);
        builder.setView(view)
                .setPositiveButton("回复", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String input = editText.getText().toString();

                        if(input.equals("")){
                            Toast.makeText(getActivity(),"内容不能为空", Toast.LENGTH_SHORT).show();
                        }
                        else {

                        }
                    }
                });

        builder.create();

        return builder.create();

    }


//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//        View mView = LayoutInflater.from(getActivity())
//                .inflate(R.layout.huifu_comment, container);
//        return mView;
//    }
}