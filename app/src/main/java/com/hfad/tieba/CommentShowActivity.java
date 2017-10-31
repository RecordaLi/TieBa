//package com.hfad.tieba;
//
//import android.content.Context;
//import android.content.Intent;
//import android.support.v4.app.Fragment;
//
//import java.util.UUID;
//
///**
// * Created by lgy on 2017/10/28.
// */
//
//public class CommentShowActivity extends SingleFragmentActivity {
//
//    private static final String EXTRA_TIEZI_ID = "co_tiezi_id";
//
//    @Override
//    protected Fragment createFragment() {
//        UUID tieziId = (UUID) getIntent().getSerializableExtra(EXTRA_TIEZI_ID);
//        return CommentShowFragment.newInstance(tieziId);
//    }
//
//    public static Intent newIntent(Context context, UUID tieziId) {
//        Intent intent = new Intent(context, CommentShowActivity.class);
//        intent.putExtra(EXTRA_TIEZI_ID, tieziId);
//        return intent;
//    }
//}
