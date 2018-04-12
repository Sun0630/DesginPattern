package tech.sx.day11_decoration;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Author sunxin
 * @Date 2018/4/12 0012 上午 11:21
 * @Description 重写RecyclerView，添加增强方法
 */
public class WrapRecyclerView extends RecyclerView {

    private WrapRecyclerAdapter mRecyclerAdapter;

    public WrapRecyclerView(Context context) {
        super(context);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mRecyclerAdapter = new WrapRecyclerAdapter(adapter);
        super.setAdapter(mRecyclerAdapter);
    }

    /**
     * 添加头布局
     *
     * @param view
     */
    public void addHeaderView(View view) {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.addHeaderView(view);
        }
    }

    /**
     * 添加底部局
     *
     * @param view
     */
    public void addFooterView(View view) {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.addFooterView(view);
        }
    }

    /**
     * 移除
     *
     * @param view
     */
    public void removeHeaderView(View view) {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.removeHeaderView(view);
        }
    }

    /**
     * 移除
     *
     * @param view
     */
    public void removeFooterView(View view) {
        if (mRecyclerAdapter != null) {
            mRecyclerAdapter.removeFooterView(view);
        }
    }
}
