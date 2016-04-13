package com.creditease.common.view;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout.LayoutParams;

public class ListViewLoadingActivity extends ListActivity implements
        OnScrollListener {

    private LinearLayout mLoadLayout;
    private ListView mListView;
    private ListViewAdapter mListViewAdapter = new ListViewAdapter();
    private int mLastItem = 0;
    private int mCount = 21;
    private final Handler mHandler = new Handler();
    private final LayoutParams mProgressBarLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);
    private final LayoutParams mTipContentLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * "加载项"布局，此布局被添加到ListView的Footer中。
         */
        mLoadLayout = new LinearLayout(this);
        mLoadLayout.setMinimumHeight(80);
        mLoadLayout.setGravity(Gravity.CENTER);
        mLoadLayout.setOrientation(LinearLayout.HORIZONTAL);
        /**
         * 向"加载项"布局中添加一个圆型进度条。
         */
        ProgressBar mProgressBar = new ProgressBar(this);
        mProgressBar.setPadding(0, 0, 15, 0);
        mLoadLayout.addView(mProgressBar, mProgressBarLayoutParams);
        /**
         * 向"加载项"布局中添加提示信息。
         */
        TextView mTipContent = new TextView(this);
        mTipContent.setText("加载中...");
        mLoadLayout.addView(mTipContent, mTipContentLayoutParams);
        /**
         * 获取ListView组件，并将"加载项"布局添加到ListView组件的Footer中。
         */
        mListView = getListView();
        mListView.addFooterView(mLoadLayout);
        /**
         * 组ListView组件设置Adapter,并设置滑动监听事件。
         */
        setListAdapter(mListViewAdapter);
        mListView.setOnScrollListener(this);
    }

    public void onScroll(AbsListView view, int mFirstVisibleItem,
                         int mVisibleItemCount, int mTotalItemCount) {
        mLastItem = mFirstVisibleItem + mVisibleItemCount - 1;
        if (mListViewAdapter.count > mCount) {
            mListView.removeFooterView(mLoadLayout);
        }
    }

    public void onScrollStateChanged(AbsListView view, int mScrollState) {

        /**
         * 当ListView滑动到最后一条记录时这时，我们会看到已经被添加到ListView的"加载项"布局， 这时应该加载剩余数据。
         */
        if (mLastItem == mListViewAdapter.count
                && mScrollState == OnScrollListener.SCROLL_STATE_IDLE) {
            if (mListViewAdapter.count <= mCount) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mListViewAdapter.count += 5;
                        mListViewAdapter.notifyDataSetChanged();
                        mListView.setSelection(mLastItem);
                    }
                }, 1000);
            }
        }
    }

    class ListViewAdapter extends BaseAdapter {
        int count = 5;

        public int getCount() {
            return count;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View view, ViewGroup parent) {
            TextView mTextView;
            if (view == null) {
                mTextView = new TextView(ListViewLoadingActivity.this);
            } else {
                mTextView = (TextView) view;
            }
            mTextView.setText("Item " + position);
            mTextView.setTextSize(20f);
            mTextView.setGravity(Gravity.CENTER);
            mTextView.setHeight(100);
            return mTextView;
        }
    }
}