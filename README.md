# SmartRecyclerView
SmartRecyclerView下拉刷新，上拉加载更多库

![署名-相同方式共享](https://licensebuttons.net/l/by-sa/3.0/88x31.png)  
  
遵循：[BY-SA](https://creativecommons.org/licenses/by-nc-sa/4.0/)  

[署名-相同方式共享  4.0协议](https://creativecommons.org/licenses/by-sa/4.0/)  


Committed and Share By Tandong（谭东）.
https://github.com/jaychou2012/SmartRecyclerView

APKDemo下载体验：[下载APK](https://github.com/zuichu/SmartRecyclerView/blob/master/app-debug.apk) 

##Gradle Dependency
Add this in your root ```build.gradle``` file (not your module ```build.gradle``` file):

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Then, add the library to your module ```build.gradle```
```
dependencies {
	        compile 'com.github.jaychou2012:SmartRecyclerView:1.01'
	}
```

##效果截图：

<center>
<img src="https://github.com/zuichutech/Resource/blob/master/Screenshot_20170306-194034.png" width="40%" height="40%"/>
功能首页
</center>


<center>
<img src="https://github.com/zuichutech/Resource/blob/master/device-2017-03-06-194209.png" width="40%" height="40%"/>
下拉刷新
</center>


<center>
<img src="https://github.com/zuichutech/Resource/blob/master/device-2017-03-06-194244.png" width="40%" height="40%"/>
正在加载
</center>

<center>
<img src="https://github.com/zuichutech/Resource/blob/master/Screenshot_20170306-194112.png" width="40%" height="40%"/>
没有更多数据
</center>

<center>
<img src="https://github.com/zuichutech/Resource/blob/master/device-2017-03-06-194319.png" width="40%" height="40%"/>
有Header
</center>


<center>
<img src="https://github.com/zuichutech/Resource/blob/master/device-2017-03-06-194331.png" width="40%" height="40%"/>
空数据
</center>

<center>
<img src="https://github.com/zuichutech/Resource/blob/master/device-2017-03-06-194350.png" width="40%" height="40%"/>
多种布局
</center>



[视频演示](https://github.com/zuichutech/Resource/blob/master/device-2017-03-06-194506.mp4)  

##基本用法：

布局文件
```

 <com.whatjay.recyclerview.view.SmartRecyclerview
        android:id="@+id/recyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

```
```
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setLoadingListener(this);
        list = new ArrayList<String>();
        mainAdapter = new MainAdapter(this, list);
        recyclerview.setAdapter(mainAdapter);
        mainAdapter.setOnItemClickListener(this);
        mainAdapter.setOnItemLongClickListener(this);
```
如果想自动刷新的话
```
         recyclerview.refresh();
 ```
监听刷新，可以实现接口

```
        implements SmartRecyclerview.LoadingListener
 ```
 然后实现重写2个方法
 ```
    @Override
    public void onRefresh() {
      
    }

    @Override
    public void onLoadMore() {
       
    }
 ```
 刷新完成，要使用
  ```
    recyclerview.refreshComplete();
 ```
  加载更多完成，要使用
  ```
    recyclerview.loadMoreComplete();
 ```
  没有更多数据了，要使用
  ```
     recyclerview.setNoMore(true);
 ```
 可以自己设置更改刷新和加载更多的样式
 ```
      recyclerview.setRefreshProgressStyle(ProgressStyle.BallBeat);
      recyclerview.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);
 ```
 
  可以自己设置更改刷新的箭头，不设置的话为默认
  ```
      recyclerview.setArrowImageView(R.mipmap.ic_pulltorefresh_arrow);
  
```
 Item点击和长按事件使用
 ```
      implements BaseSmartAdapter.OnRecyclerViewItemClickListener, BaseSmartAdapter.OnRecyclerViewItemLongClickListener
      
      ...
      
    @Override
    public void onItemClick(View view, int position) {
        
    }

    @Override
    public void onItemLongClick(View view, int position) {
    
    }
      
 ```
 
 当然，RecyclerView的Adapter写起来很麻烦，并且默认没有点击和长按事件，所以，SmartRecyclerView写好了BaseSmartAdapter和多种布局的BaseMultiSmartAdapter。只需要继承实现就可以了。例如
 
 ```
   public class MainAdapter extends BaseSmartAdapter<String> {

    public MainAdapter(Context context, List<String> lists) {
        super(context, me.zuichu.smartrecyclerviewdemo.R.layout.item_main, lists);
    }

    @Override
    public void bindData(SmarViewHolder holder, String s) {
        holder.setText(me.zuichu.smartrecyclerviewdemo.R.id.tv_text, s);
    }
  }
 ```
 多种布局的话，用BaseMultiSmartAdapter
 
 ```
  public class MultiAdapter extends BaseMultiSmartAdapter<MultiItem> {

    public MultiAdapter(Context context, List<MultiItem> data) {
        super(context, data);
        addItemType(0, R.layout.item_multi1);
        addItemType(1, R.layout.item_multi2);
    }

    @Override
    protected void bindData(SmarViewHolder smarViewHolder, MultiItem item) {
        switch (item.getItemType()) {
            case 0:
                smarViewHolder.setText(R.id.tv_text1, "布局一："+item.getName());
                break;
            case 1:
                smarViewHolder.setText(R.id.tv_text2, "布局二："+item.getName());
                break;
        }
    }
}
 ```
 多种布局的Item实体类要继承SmartMultiEntity
```
 public class MultiItem extends SmartMultiEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
 ``` 
 如果你用了GridLayoutManager,又要实现不规则的布局Adapter的话，可以在Adapter里这样写，重写Adapter的onAttachedToRecyclerView方法，然后按照需求判断就可以了
 ```
 @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int position2 = position;
                    if (position2 >= getLists().size()) {
                        position2 = getLists().size() - 1;
                    }
                    if (getLists().size() > 0) {
                        int type = getLists().get(position2).getItemType();
                        return (type == 0) ?
                                gridManager.getSpanCount() : 1;
                    }
                    return gridManager.getSpanCount();
                }
            });
        }
    }
 ``` 
SmarRecyclerView轻松帮你实现了下拉刷新，上拉加载更多的逻辑。并且帮你做了很多Adapter里做的事情，简化你的操作。只需继承BaseSmartAdapter或BaseMultiSmartAdapter，实现里面的bindData方法即可，无需自己还要复杂的绑定Item布局，直接帮你自动绑定和加载。赋值的话可以这样用
 ```
@Override
    public void bindData(SmarViewHolder holder, String s) {
        holder.setText(me.zuichu.smartrecyclerviewdemo.R.id.tv_text, s);
    }
 ``` 
##License

This work is licensed under a Creative Commons Attribution 4.0 International License. Feel free to contribute via Pull Requests, or discuss ideas in Issues. Also feel free to use these ideas in making the Next Big Thing. 


## 《Android开发进阶实战：拓展与提升》已出版


### 新书涵盖Android最新的技术和内容，包括：新布局方式ConstraintLayout 、AndroidX、Jetpack、TV开发等，值得购买阅读。


![Android开发进阶实战：拓展与提升](http://img13.360buyimg.com/n1/jfs/t1/113550/10/7905/112523/5ec79791E6bf5d507/7169944c4d0d6669.jpg "Android开发进阶实战：拓展与提升")


### 纸质书购买：

[京东](https://item.jd.com/69496918930.html "京东")         [天猫](https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.6.7103434dRkHC8S&id=618745314823&user_id=3446196188&cat_id=2&is_b=1&rn=45bd1618b102199a8f9794a7b8431df4 "天猫")  [当当](http://product.dangdang.com/28552590.html "当当")
