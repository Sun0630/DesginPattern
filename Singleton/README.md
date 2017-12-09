[TOC]
# 单例模式
> **核心原理：** 将构造函数私有化，并且通过静态方法获取一个唯一的实例，在这个过程中必须保证线程安全、防止反序列化导致重新生成实例对象等问题。

* UML
![单例设计模式](http://upload-images.jianshu.io/upload_images/1339948-9859ae584e1e0cc3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## 单例模式实现的几种方式
### 饿汉式
```
/**
 * 饿汉式
 */
public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton(){}

    public static Singleton getInstance() {
        return instance;
    }
}
```
### 懒汉式
```
/**
 * 懒汉式
 */
public class Singleton {

    private static Singleton instance;

    private Singleton(){}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

```
### Double Check Lock（DCL）实现单例模式
```
/**
 * Double Check Lock(DCL) 双重锁校验
 * 优点：资源利用率高，但是由于Java内存模型的问题偶尔会出现DCL失效问题
 */
public class Singleton {

    private static Singleton instance = null;

    private Singleton() {
    }

    public static Singleton getInstance() {
        //避免不必要的同步
        if (instance == null) {
            synchronized (Singleton.class) {
                //在null的情况下创建实例
                if (instance == null) {
                    /**
                     * 1.给Singleton的实例分配内存
                     * 2.调用Singleton()的构造函数
                     * 3.将instance对象指向分配的空间
                     *
                     * 因为JVM编译器对象允许处理器乱序执行，所以这三步顺序不一定
                     */
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
### 静态内部类单例模式
```
/**
 * 静态内部类单例模式(推荐使用)
 * 能够保证线程安全，对象的唯一性，延迟了单例的实例化。
 */
public class Singleton {

    private Singleton(){}

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }

    /**
     * 静态内部类
     */
    private static class SingletonHolder{
        private static final Singleton instance = new Singleton();
    }

}
```
### 枚举单例
```
/**
 * 枚举单例
 *  写法简单，默认枚举实例的创建是线程安全的。
 *  在上述的几种单例模式的实现中，在反序列化的情况下会出现重新创建对象的情况。
 */
public enum SingletonEnum {
    INSTANCE;
    public void doSomething(){
        System.out.println("do sth");
    }
}

/**
 * 防止单例对象被反序列化，重写反序列化的一个钩子函数readResolve()
 */
public final class Singleton implements Serializable{

    public static final Singleton INSTANCE = new Singleton();

    public static Singleton getInstance() {
        return INSTANCE;
    }

    /**
     * 反序列化操作中可以让那个开发人员控制对象的函数
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException{
        return INSTANCE;
    }

}

```
### 容器实现单例模式
```
/**
 * 使用容器实现单例模式
 */
public class SingletonManager {
    private static Map<String,Object> objMap = new HashMap<String,Object>();

    private SingletonManager(){}

    public static void registerService(String key,Object instance){
        if (!objMap.containsKey(key)){
            objMap.put(key,instance);
        }
    }

    public static Object getInstance(String key){
        return objMap.get(key);
    }

}
```

## 单例模式在Android源码中的应用**（LayoutInflater）**
1. 通过`LayoutInflater.from(context)`来获取LayoutInflater服务
```
/**
 * Obtains the LayoutInflater from the given context.
 */
public static LayoutInflater from(Context context) {
    LayoutInflater LayoutInflater =
            (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    if (LayoutInflater == null) {
        throw new AssertionError("LayoutInflater not found.");
    }
    return LayoutInflater;
}
```
2. 来看看`context.getSystemService`是怎么工作的,context的实现类是`ContextImpl`类，点进去看一下
```
@Override
public Object getSystemService(String name) {
    return SystemServiceRegistry.getSystemService(this, name);
}
```
3. 进入到`SystemServiceRegistry`类中
```
/**
 * Gets a system service from a given context.
 */
public static Object getSystemService(ContextImpl ctx, String name) {
    ServiceFetcher<?> fetcher = SYSTEM_SERVICE_FETCHERS.get(name);
    return fetcher != null ? fetcher.getService(ctx) : null;
}
```
看到这里感觉好像是我们上面用到的第五种单例模式，使用容器实现。看一下果然是
```
private static final HashMap<String, ServiceFetcher<?>> SYSTEM_SERVICE_FETCHERS =
        new HashMap<String, ServiceFetcher<?>>();
```
使用`map`通过键值对的方式保存系统服务。在调用`registerService`的时候注入。
```
/**
 * Statically registers a system service with the context.
 * This method must be called during static initialization only.
 */
private static <T> void registerService(String serviceName, Class<T> serviceClass,
        ServiceFetcher<T> serviceFetcher) {
    SYSTEM_SERVICE_NAMES.put(serviceClass, serviceName);
    SYSTEM_SERVICE_FETCHERS.put(serviceName, serviceFetcher);
}
```
4. 我们可以再看看这些系统服务都是在什么时候注册的
```
static {
  
registerService(Context.LAYOUT_INFLATER_SERVICE, LayoutInflater.class,
        new CachedServiceFetcher<LayoutInflater>() {
    @Override
    public LayoutInflater createService(ContextImpl ctx) {
        return new PhoneLayoutInflater(ctx.getOuterContext());
    }});
}
```
是在一个静态的代码块中进行注册服务，第一次加载该类的时候执行，并且只执行一次，保证实例的唯一性。

> 从这个过程中可以看出，系统将服务以键值对的形式存储在HashMap中，用户使用时只需要获取具体的服务对象，第一次获取时，调用`getSystemService`来获取具体的对象，在第一次调用时，会调用`registerService`通过map将对象缓存在一个列表中，下次再用时直接从容器中取出来就可以。避免重复创建对象，从而达到单例的效果。减少了资源消耗。

4. 接下来，我们继续深入研究一下`LayoutInflater`的源码实现，我们知道`LayoutInflater`是一个抽象类，具体的实现肯定都在它的子类，在注册服务的时候可以之后它的子类就是`PhoneLayoutInflater`.
```
/**
 * @hide
 */
public class PhoneLayoutInflater extends LayoutInflater {
   //内置View类型的前缀，拼接出完整路径 andorid.widget.TextView
    private static final String[] sClassPrefixList = {
        "android.widget.",
        "android.webkit.",
        "android.app."
    };

    /**
     * Instead of instantiating directly, you should retrieve an instance
     * through {@link Context#getSystemService}
     *
     * @param context The Context in which in which to find resources and other
     *                application-specific things.
     *
     * @see Context#getSystemService
     */
    public PhoneLayoutInflater(Context context) {
        super(context);
    }

    protected PhoneLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    /** Override onCreateView to instantiate names that correspond to the
        widgets known to the Widget factory. If we don't find a match,
        call through to our super class.
    */
    @Override protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        for (String prefix : sClassPrefixList) {
            try {
                View view = createView(name, prefix, attrs);
                if (view != null) {
                    return view;
                }
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }
     //核心语句，根据我完整View的的路径名来构造出View对象
        return super.onCreateView(name, attrs);
    }

    public LayoutInflater cloneInContext(Context newContext) {
        return new PhoneLayoutInflater(this, newContext);
    }
}
```
> 从上述代码中我们可以看出 为什么当我们自定义View的时候需要把全类名写上。

5. 看一下一个View的构建流程，以`Activity`的`setContentView为`例
```
/**
 * Set the activity content from a layout resource.  The resource will be
 * inflated, adding all top-level views to the activity.
 *
 * @param layoutResID Resource ID to be inflated.
 *
 * @see #setContentView(android.view.View)
 * @see #setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
 */
public void setContentView(@LayoutRes int layoutResID) {
    getWindow().setContentView(layoutResID);
    initWindowDecorActionBar();
}
```
实际上是调用`Window`的`setContentView`，window是一个抽象类，子类是`PhoneWindow`,具体来看下
```
@Override
public void setContentView(int layoutResID) {
    // Note: FEATURE_CONTENT_TRANSITIONS may be set in the process of installing the window
    // decor, when theme attributes and the like are crystalized. Do not check the feature
    // before this happens.
//如果为空，安装DecorView，并将DecorView添加到mContentParent中
    if (mContentParent == null) {
        installDecor();
    } else if (!hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
        mContentParent.removeAllViews();
    }

    if (hasFeature(FEATURE_CONTENT_TRANSITIONS)) {
        final Scene newScene = Scene.getSceneForLayout(mContentParent, layoutResID,
                getContext());
        transitionTo(newScene);
    } else {
    //通过布局id和mContentParent渲染布局，解析xml文件
        mLayoutInflater.inflate(layoutResID, mContentParent);
    }
    mContentParent.requestApplyInsets();
    final Callback cb = getCallback();
    if (cb != null && !isDestroyed()) {
        cb.onContentChanged();
    }
    mContentParentExplicitlySet = true;
}
```
看一下infate方法，主要就是解析xml文件的标签
```
public View inflate(XmlPullParser parser, @Nullable ViewGroup root, boolean attachToRoot) {
    synchronized (mConstructorArgs) {
        Trace.traceBegin(Trace.TRACE_TAG_VIEW, "inflate");

        final Context inflaterContext = mContext;
        final AttributeSet attrs = Xml.asAttributeSet(parser);
        Context lastContext = (Context) mConstructorArgs[0];
        mConstructorArgs[0] = inflaterContext;
        View result = root;

        try {
            // Look for the root node.
            int type;
            while ((type = parser.next()) != XmlPullParser.START_TAG &&
                    type != XmlPullParser.END_DOCUMENT) {
                // Empty
            }

            if (type != XmlPullParser.START_TAG) {
                throw new InflateException(parser.getPositionDescription()
                        + ": No start tag found!");
            }

            final String name = parser.getName();
            
            if (DEBUG) {
                System.out.println("**************************");
                System.out.println("Creating root view: "
                        + name);
                System.out.println("**************************");
            }

            if (TAG_MERGE.equals(name)) {
                if (root == null || !attachToRoot) {
                    throw new InflateException("<merge /> can be used only with a valid "
                            + "ViewGroup root and attachToRoot=true");
                }

                rInflate(parser, root, inflaterContext, attrs, false);
            } else {
                // Temp is the root view that was found in the xml
                final View temp = createViewFromTag(root, name, inflaterContext, attrs);

                ViewGroup.LayoutParams params = null;

                if (root != null) {
                    if (DEBUG) {
                        System.out.println("Creating params from root: " +
                                root);
                    }
                    // Create layout params that match root, if supplied
                    params = root.generateLayoutParams(attrs);
                    if (!attachToRoot) {
                        // Set the layout params for temp if we are not
                        // attaching. (If we are, we use addView, below)
                        temp.setLayoutParams(params);
                    }
                }

                if (DEBUG) {
                    System.out.println("-----> start inflating children");
                }

                // Inflate all children under temp against its context.
                rInflateChildren(parser, temp, attrs, true);

                if (DEBUG) {
                    System.out.println("-----> done inflating children");
                }

                // We are supposed to attach all the views we found (int temp)
                // to root. Do that now.
                if (root != null && attachToRoot) {
                    root.addView(temp, params);
                }

                // Decide whether to return the root that was passed in or the
                // top view found in xml.
                if (root == null || !attachToRoot) {
                    result = temp;
                }
            }

        ....//省略代码

        return result;
    }
}
```
`rInflate`通过深度优先遍历来构造视图树，每解析一个View元素就会递归调用`rInflte`，会将每个View元素添加到parent中，最后最后，通过`setContentView`设置的内容就会出现在屏幕上。
```
void rInflate(XmlPullParser parser, View parent, Context context,
        AttributeSet attrs, boolean finishInflate) throws XmlPullParserException, IOException {

    final int depth = parser.getDepth();
    int type;

    while (((type = parser.next()) != XmlPullParser.END_TAG ||
            parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {

        if (type != XmlPullParser.START_TAG) {
            continue;
        }

        final String name = parser.getName();
        
        if (TAG_REQUEST_FOCUS.equals(name)) {
            parseRequestFocus(parser, parent);
        } else if (TAG_TAG.equals(name)) {
            parseViewTag(parser, parent, attrs);
        } else if (TAG_INCLUDE.equals(name)) {
            if (parser.getDepth() == 0) {
                throw new InflateException("<include /> cannot be the root element");
            }
            parseInclude(parser, context, parent, attrs);
        } else if (TAG_MERGE.equals(name)) {
            throw new InflateException("<merge /> must be the root element");
        } else {
            final View view = createViewFromTag(parent, name, context, attrs);
            final ViewGroup viewGroup = (ViewGroup) parent;
            final ViewGroup.LayoutParams params = viewGroup.generateLayoutParams(attrs);
            rInflateChildren(parser, view, attrs, true);
            viewGroup.addView(view, params);
        }
    }

    if (finishInflate) {
        parent.onFinishInflate();
    }
}
```
> 小结：单例模式是运用最多的设计模式之一，在客户端没有高并发的情况下，选择那种实现方式并没有太大的影响，但出于效率考虑，推荐使用DCL和静态内部类的实现方式。






