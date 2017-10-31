package com.hfad.tieba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by lgy on 2017/10/28.
 */

public class CommentShowFragment extends Fragment {

    private static final String ARG_TIEZI_ID="co_TIEZI_ID";

    private RecyclerView mCommentRecyclerView;
    private CommentAdapter mCommentAdapter;
    private Commentlist mCommentlist;
    private List<Comment> mComments;
    private UUID mUUID;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // UUID tieziId = (UUID)getArguments().getSerializable(ARG_TIEZI_ID);
      //  mUUID = tieziId;
     //   mCommentlist = CommentLab.get(getActivity()).getComments(mUUID);
        mComments = new ArrayList<Comment>();

    }

    public static CommentShowFragment newInstance(UUID mId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIEZI_ID,mId);

       CommentShowFragment fragment = new CommentShowFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiezi_details,container,false);
        mCommentRecyclerView = (RecyclerView)view.findViewById(R.id.tiezi_recycler_view_pinglun);

        mCommentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
//        mCommentlist = CommentLab.get(getActivity()).getComments(mUUID);
//        mComments = mCommentlist.getCommentList();
        mComments = new ArrayList<Comment>();
        for(int i=0;i<5;i++){
            mComments.add(new Comment("haha"));
        }

            mCommentAdapter = new CommentAdapter(mComments);
            mCommentRecyclerView.setAdapter(mCommentAdapter);
    }

    private class CommentHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private Comment mComment;

        public CommentHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_comment,parent,false));
            content = (TextView)itemView.findViewById(R.id.commentTextView);
        }

        public void bind(Comment comment){
            comment = mComment;
            content.setText(comment.getComment());
        }
    }


    private class CommentAdapter extends RecyclerView.Adapter<CommentHolder>{

        private  List<Comment> mComments;

        public CommentAdapter(List<Comment> mComments){
            this.mComments = mComments;
        }


        @Override
        public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CommentHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(CommentHolder holder, int position) {
//            Comment comment = mComments.get(position);
//            holder.bind(comment);
        }

        @Override
        public int getItemCount() {
            return mComments.size();
        }
    }
}
