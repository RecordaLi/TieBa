package com.hfad.tieba;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lgy on 2017/10/16.
 */

public class TieZiListFragment extends Fragment {
    private static final String SAVE_SUBTITLE = "save_subtitle";
    private RecyclerView mRecyclerView;
    private TieZiAdapter mTieZiAdapter;
    private Boolean mSubtitle = false;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVE_SUBTITLE,mSubtitle);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiezi_list, container, false);
        if(savedInstanceState!=null){
            mSubtitle = savedInstanceState.getBoolean(SAVE_SUBTITLE);
        }
        mRecyclerView = (RecyclerView) view.findViewById(R.id.tiezi_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_tiezi_list,menu);

        MenuItem sub = menu.findItem(R.id.show_subtitle);
        if(mSubtitle){
            sub.setTitle(R.string.hide_subtitle);
        }else{
            sub.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.new_tiezi:
                TieZi mTieZi = new TieZi();
                TieZiLab.get(getActivity()).addTieZi(mTieZi);
                Intent intent = TieZiActivity.newIntent(getActivity(),mTieZi.getId());
                startActivity(intent);
                return true;

            case R.id.show_subtitle:
                mSubtitle = !mSubtitle;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        TieZiLab ziLab = TieZiLab.get(getActivity());
        int count = ziLab.getTieZis().size();

        String subtitle = getString(R.string.subtitle_format,count);

        if(!mSubtitle){
            subtitle = null;
        }
        AppCompatActivity appCompatActivity = (AppCompatActivity)getActivity();
        appCompatActivity.getSupportActionBar().setSubtitle(subtitle);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        TieZiLab lab = TieZiLab.get(getActivity());
        List<TieZi> TieZis = lab.getTieZis();
        if(mTieZiAdapter==null) {
            mTieZiAdapter = new TieZiAdapter(TieZis);
            mRecyclerView.setAdapter(mTieZiAdapter);
        }else {
            mTieZiAdapter.setTieZis(TieZis);
            mTieZiAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }

    private class TieZiHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleView;
        private TextView mContentView;
        private TextView mCommentView;
        private TieZi tiezi;


        public TieZiHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.list_item_tiezi, viewGroup, false));
            itemView.setOnClickListener(this);
            mTitleView = (TextView)itemView.findViewById(R.id.tiezi_title1);
            mContentView = (TextView)itemView.findViewById(R.id.tiezi_content1);
            mCommentView = (TextView)itemView.findViewById(R.id.commentTextView);
        }

        public void bind(TieZi tie){
            tiezi = tie;
            mTitleView.setText(tiezi.getTitle());
            mContentView.setText(tiezi.getContent());
            mCommentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"你正在评论",Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(),tiezi.getTitle()+"Click",Toast.LENGTH_SHORT).show();
            //Intent intent = new Intent(getActivity(),TieZiDetailActivity.class);
            Intent intent = TieZiDetailActivity.newIntent(getActivity(),tiezi.getId());
            startActivity(intent);
        }
    }

    private class TieZiAdapter extends RecyclerView.Adapter<TieZiHolder> {
        private List<TieZi> mTieZis;

        public TieZiAdapter(List<TieZi> list) {
            mTieZis = list;
        }

        @Override
        public TieZiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());

            return new TieZiHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(TieZiHolder holder, int position) {
            TieZi mTieZi = mTieZis.get(position);
            holder.bind(mTieZi);
        }

        @Override
        public int getItemCount() {
            return mTieZis.size();
        }

        public void setTieZis(List<TieZi> tieZis){
            mTieZis = tieZis;
        }
    }


}
