

# 装饰设计模式
> 又称包装设计模式，用来动态的扩展对象的功能，也是继承关系的一种替代方案之一。
怎么写：一般都是把类对象作为构造函数传递。

使用场景：需要透明且动态的扩展类的功能时。

## UML
![](/img/decoration.png)
## 角色介绍
* Component：抽象组件，可以是一个接口或抽象类，充当的就是被装饰的原始对象。
* ConcreteComponent：组件具体实现类，是我们装饰的具体对象
* Decorator：抽象装饰者，职责就是为了装饰我们的组件对象，内部一定要有一个窒息那个组建对象的引用。
* ConcreteDecoratorA：装饰者的具体实现，对抽象装饰者的具体实现。




**抽象组件**
```java
/**
 * @Author sunxin
 * @Date 2018/4/1 11:43
 * @Description 抽象组件，可以是一个接口或抽象类，充当的就是被装饰的原始对象
 */

public abstract class Component {
    public abstract void operate();
}
```

**组件具体实现类**
```java
/**
 * @Author sunxin
 * @Date 2018/4/1 11:45
 * @Description 组件具体实现类，是我们装饰的具体对象
 */

public class ConcreteComponent extends Component {
    @Override
    public void operate() {

    }
}
```

**抽象装饰者**
```java
/**
 * @Author sunxin
 * @Date 2018/4/1 11:51
 * @Description 抽象装饰者
 */

public abstract class Decorator extends Component {

    /**
     * 持有一个Component对象的引用
     */
    private Component mComponent;

    /**
     * 构造一个持有该对象的构造函数
     * @param component
     */
    public Decorator(Component component) {
        mComponent = component;
    }

    @Override
    public void operate() {
        mComponent.operate();
    }
}
```

**装饰者具体实现类**
```java
/**
 * @Author sunxin
 * @Date 2018/4/1 11:55
 * @Description 装饰者具体实现类
 */

public class ConcreteDecoratorA extends Decorator {
    /**
     * 构造一个持有该对象的构造函数
     *
     * @param component
     */
    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        //装饰方法可以在父类方法之前调用也可在之后调用
        operateA();
        super.operate();
        operateB();
    }

    /**
     * 自定义装饰A方法
     */
    public void operateA() {

    }

    /**
     * 自定义装饰A方法
     */
    public void operateB() {

    }
}
```

**最后调用**
```java
/**
 * @Author sunxin
 * @Date 2018/4/1 11:56
 * @Description 最后调用
 */

public class Client {
    public void main(String args[]){
        //构造被装饰的组件对象
        Component component = new ConcreteComponent();
        //根据组件对象构造装饰者对象A并调用，此时相当于给组件对象增加装饰者A的功能方法
        Decorator decorator = new ConcreteDecoratorA(component);
        decorator.operate();
    }
}
```

## 实例：使用装饰设计模式为RecyclerView添加头布局和脚布局
> 模仿ListView，可以直接调用RecyclerView.addHeaderView 方法。主要是装饰RecyclerView.Adapter

1.自定义WrapRecyclerViewAdapter 继承自RecyclerView.Adapter

```java
/**
 * @Author Administrator
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
```

2.重写RecyclerView，为其增加添加头布局和脚布局的方法
```java
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
     // 在这里装饰原来的 Adapter，为其增加添加头脚的布局
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
```

3.使用

```java
public class MainActivity extends AppCompatActivity {

    private WrapRecyclerView mRecyclerView;
    private List<Integer> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
     //构造假数据
        mItems = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mItems.add(i);
        }
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter mRealAdapter = new RecyclerAdapter();
        mRecyclerView.setAdapter(mRealAdapter);
        View headerView = LayoutInflater.from(this).inflate(R.layout.item_header, mRecyclerView, false);
        View footerView = LayoutInflater.from(this).inflate(R.layout.item_footer, mRecyclerView, false);
     // 添加头布局和脚布局
        mRecyclerView.addHeaderView(headerView);
        mRecyclerView.addFooterView(footerView);
    }

   // 定义Adapter
    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_main, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.mTextView.setText("position = " + mItems.get(position));
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItems.remove(position);
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            public ViewHolder(View itemView) {
                super(itemView);
                mTextView = itemView.findViewById(R.id.text_view);
            }
        }
    }
}
```
4.效果

![](/img/show.png)











