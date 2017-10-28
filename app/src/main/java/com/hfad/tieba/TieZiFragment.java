package com.hfad.tieba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by lgy on 2017/10/16.
 */

public class TieZiFragment extends Fragment {
    private static final String ARG_TIEZI_ID="TIEZI_ID";

    private TieZi mTieZi;
    private EditText mTitle;
    private EditText mContent;
    private Button mDateButton;



    public static TieZiFragment newInstance(UUID mId){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TIEZI_ID,mId);

        TieZiFragment fragment = new TieZiFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID TieZiId = (UUID)getArguments().getSerializable(ARG_TIEZI_ID);
        mTieZi = TieZiLab.get(getActivity()).getTieZi(TieZiId);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiezi,container,false);
        mTitle = (EditText)view.findViewById(R.id.tiezi_title);
        mTitle.setText(mTieZi.getTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTieZi.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mContent = (EditText)view.findViewById(R.id.tiezi_content);
        mContent.setText(mTieZi.getContent());
        mContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mTieZi.setContent(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDateButton = (Button)view.findViewById(R.id.tiezi_date);
        mDateButton.setText(mTieZi.getDate().toString());
        mDateButton.setEnabled(false);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        TieZiLab.get(getActivity()).updateTiezi(mTieZi);
    }
}
