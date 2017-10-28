package com.hfad.tieba;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

/**
 * Created by lgy on 2017/10/21.
 */

public class TieZiDetailFragment extends Fragment {
    private static final String ARG_TIEZI_ID="TIEZI_ID";
    private static final String DIALOG_COMMENT = "DialogComment";

    private RecyclerView mRecyclerView;
    private DetailAdapter mDetailAdapter;
    private TieZi mTieZi;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID tieziId = (UUID)getArguments().getSerializable(ARG_TIEZI_ID);
        mTieZi = TieZiLab.get(getActivity()).getTieZi(tieziId);
    }

    public static TieZiDetailFragment newInstance(UUID mId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_TIEZI_ID,mId);

        TieZiDetailFragment fragment = new TieZiDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tiezi_details,container,false);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.tiezi_recycler_view_xiangqing);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        //TieZiLab lab = TieZiLab.get(getActivity());
        //List<TieZi> list = lab.getTieZis();
        //TieZi tiezi = new TieZi();

        if(mDetailAdapter==null) {
            mDetailAdapter = new DetailAdapter(mTieZi);
            mRecyclerView.setAdapter(mDetailAdapter);
        }else {
            mDetailAdapter.notifyDataSetChanged();
        }
    }

    private class TieZiDetailHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mContent;
        private TextView mCommentView;
        private TieZi mTieZi;
        private CommentLab mCommentLab;

        public TieZiDetailHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_tiezi,parent,false));
            mTitle = (TextView)itemView.findViewById(R.id.tiezi_title1);
            mContent = (TextView)itemView.findViewById(R.id.tiezi_content1);
            mCommentView = (TextView)itemView.findViewById(R.id.commentTextView);
            mCommentLab = CommentLab.get(getActivity());
            mCommentLab.add(mTieZi.getCommentlist());
        }

        public void bind(TieZi tz){
            mTieZi = tz;
            mTitle.setText(mTieZi.getTitle());
            mContent.setText(mTieZi.getContent());
            mCommentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  // Toast.makeText(getContext(),"你正在评论",Toast.LENGTH_SHORT).show();
                    FragmentManager manager = getFragmentManager();
                    HuifuFragment fragment = new HuifuFragment();
                    fragment.show(manager, DIALOG_COMMENT);
                }
            });
        }
    }

    private class DetailAdapter extends RecyclerView.Adapter<TieZiDetailHolder>{
        private TieZi mtiezi;

        public DetailAdapter(TieZi tiezi){
            //list = mlist;
            mtiezi = tiezi;
        }

        @Override
        public TieZiDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new TieZiDetailHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(TieZiDetailHolder holder, int position) {
           // TieZi tiezi = list.get(position);
            holder.bind(mtiezi);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
