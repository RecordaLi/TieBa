package com.hfad.tieba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by lgy on 2017/10/28.
 */

public class CommentShowFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiezi_details,container,false);
        mRecyclerView = (RecyclerView)view.findViewById(R.id.tiezi_recycler_view_pinglun);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    private class CommentHolder extends RecyclerView.ViewHolder {
        public CommentHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_comment,parent,false));
        }
    }


    private class CommentAdapter extends RecyclerView.Adapter<CommentHolder>{

        private List<Commentlist> mCommentlists;

        public CommentAdapter(List<Commentlist> mCommentlists){
            this.mCommentlists = mCommentlists;
        }


        @Override
        public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new CommentHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(CommentHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mCommentlists.size();
        }
    }
}
