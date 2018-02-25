# TestModules
### 仿携程双日期选择功能的DatePicker控件

![image](https://github.com/telyo/TestModules/blob/master/gif/datepicker.gif?raw=true)



简单调用：

```java
PickerFactory.createFactory().createDatePickerDialog(this, new DoubleDatePicker.OnDatesSelectListener() {
    @Override
    public void onSelect(ChooseDates chooseDates) {
        date.setText("开始 " + DateFormatUtils.formatDate(chooseDates.getInDate(), "yyyy-MM-dd") + "\n"
                + "结束 " + DateFormatUtils.formatDate(chooseDates.getOutDate(), "yyyy-MM-dd") + "\n"
                +"共 " + chooseDates.getDaysCount() + " 晚");
    }
});
```

还不完善改进中...



### 灵活的下拉菜单控件

![image](https://github.com/telyo/TestModules/blob/master/gif/pmenu.gif?raw=true)

简单调用：

布局

```xml
 <com.telyo.scrollmenu.scrollmenu.ScrollMenuLayout
    android:id="@+id/scrollMenuLayout"
    android:layout_width="match_parent"
    android:layout_height="0dp"
    android:layout_weight="1"
    app:childDefaultLayoutId="@layout/scroll_menu">
     
    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
       	<ImageView
              android:id="@+id/iv"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:background="@color/colorAccent"
              android:clickable="true"
              android:src="@mipmap/ic_launcher_round"/>
    </LinearLayout>
    
</com.telyo.scrollmenu.scrollmenu.ScrollMenuLayout>
```
增加下拉菜单

```java
    scrollMenuLayout = findViewById(R.id.scrollMenuLayout);
  	scrollMenuLayout.addMenuChild(R.layout.menu2);
    scrollMenuLayout.addMenuChild(R.layout.menu3);
    scrollMenuLayout.addMenuChild(R.layout.menu4);
    scrollMenuLayout.addMenuChild(R.layout.menu5);
```
弹出与收回

```java
if (!scrollMenuLayout.isMenuShowing()) {
    scrollMenuLayout.showMenu(1);//按照添加菜单布局的顺序，排序
} else {
    scrollMenuLayout.dismissMenu(1);
}
```



