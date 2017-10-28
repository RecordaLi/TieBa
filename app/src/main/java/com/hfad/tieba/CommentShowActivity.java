package com.hfad.tieba;

import android.support.v4.app.Fragment;

/**
 * Created by lgy on 2017/10/28.
 */

public class CommentShowActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CommentShowFragment();
    }
}
