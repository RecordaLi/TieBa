package com.hfad.tieba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

/**
 * Created by lgy on 2017/10/21.
 */

public class TieZiDetailActivity extends FragmentActivity {
   private static final String EXTRA_TIEZI_ID="tiezi_id";

    protected Fragment createFragment() {
        UUID tieziId = (UUID)getIntent().getSerializableExtra(EXTRA_TIEZI_ID);
        return TieZiDetailFragment.newInstance(tieziId);
    }

    public static Intent newIntent(Context context, UUID tieziId){
        Intent intent = new Intent(context,TieZiDetailActivity.class);
        intent.putExtra(EXTRA_TIEZI_ID,tieziId);
        return intent;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.commentlayout);
        Fragment fragment2 = fm.findFragmentById(R.id.detail_layout);


        if(fragment==null| fragment2==null){
            fragment = createFragment();
            fragment2 = createFragment2();
            fm.beginTransaction().add(R.id.fragment_container,fragment).commit();
            fm.beginTransaction().add(R.id.fragment_container,fragment2).commit();
        }


    }

    protected Fragment createFragment2() {
        UUID tieziId = (UUID) getIntent().getSerializableExtra(EXTRA_TIEZI_ID);
        return CommentShowFragment.newInstance(tieziId);
    }
}
