package com.hfad.tieba;

import android.support.v4.app.Fragment;

/**
 * Created by lgy on 2017/10/16.
 */

public class TieZiListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TieZiListFragment();
    }
}
