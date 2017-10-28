package com.hfad.tieba;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class TieZiActivity extends SingleFragmentActivity {
    private static final String EXTRA_TIEZI_ID="tiezi_id";

    @Override
    protected Fragment createFragment() {
        UUID mId = (UUID)getIntent().getSerializableExtra(EXTRA_TIEZI_ID);
        return TieZiFragment.newInstance(mId);
    }

    public static Intent newIntent(Context context, UUID mId){
        Intent intent = new Intent(context,TieZiActivity.class);
        intent.putExtra(EXTRA_TIEZI_ID,mId);
        return intent;
    }
}
