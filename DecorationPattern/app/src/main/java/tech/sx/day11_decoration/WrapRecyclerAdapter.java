package tech.sx.day11_decoration;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * @Author sunxin
 * @Date 2018/4/12 0012 上午 9:32
 * @Description 使用装饰者模式构造adapter，为RecyclerView添加头部和底部
 */

public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * 原adapter，不支持头部和底部的添加
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mRealAdapter;

    /**
     * 可能会有多个头部和底部，所以需要用一个集合保存起来
     */
    private ArrayList<View> mHeaders;
    private ArrayList<View> mFooters;


    public WrapRecyclerAdapter(RecyclerView.Adapter realAdapter) {
        mRealAdapter = realAdapter;

        //注册一个监听，让我们的WrapRecyclerAdapter也能听得到列表的改变
        realAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                notifyDataSetChanged();
            }
        });

        mHeaders = new ArrayList<>();
        mFooters = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        //现在有三种布局，头布局，真实的数据布局，脚布局，需要通过position来分辨
        int numHeaders = getHeadersCount();

        //1. 根据位置判断头布局对应的holder
        if (position < numHeaders) {
            return createHeaderFooterViewHolder(mHeaders.get(position));
        }


        // 2. 真实数据对应的Holder
        // Adapter  需要减掉头部的数量
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mRealAdapter.onCreateViewHolder(parent, mRealAdapter.getItemViewType(adjPosition));
            }
        }

        // 3. 脚布局对应的Holder

        return createHeaderFooterViewHolder(mFooters.get(adjPosition - adapterCount));
    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {

        return new RecyclerView.ViewHolder(view) {

        };
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 也得分辨是头部还是底部还是真实数据，我们在这里只管真实数据就可以
        int numHeaders = getHeadersCount();
        if (position<numHeaders){
            return;
        }

        // 2. 真实数据对应的Holder
        // Adapter  需要减掉头部的数量
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mRealAdapter.onBindViewHolder(holder,adjPosition);
            }
        }

    }

    @Override
    public int getItemCount() {
        // 条目数  =  头部数量 + real数量  + 底部数量
        return mHeaders.size() + mRealAdapter.getItemCount() + mFooters.size();
    }


    /**
     * 获取头布局的数量
     *
     * @return
     */
    public int getHeadersCount() {
        return mHeaders.size();
    }

    /**
     * 获取脚布局的数量
     *
     * @return
     */
    public int getFootersCount() {
        return mFooters.size();
    }


    /**
     * 添加头布局
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (!mHeaders.contains(view)) {
            mHeaders.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加底部局
     *
     * @param view
     */
    public void addFooterView(View view) {
        if (!mFooters.contains(view)) {
            mFooters.add(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除
     *
     * @param view
     */
    public void removeHeaderView(View view) {
        if (mHeaders.contains(view)) {
            mHeaders.remove(view);
            notifyDataSetChanged();
        }
    }

    /**
     * 移除
     *
     * @param view
     */
    public void removeFooterView(View view) {
        if (mFooters.contains(view)) {
            mFooters.remove(view);
            notifyDataSetChanged();
        }
    }


}
