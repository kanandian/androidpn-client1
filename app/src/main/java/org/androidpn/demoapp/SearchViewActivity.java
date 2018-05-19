package org.androidpn.demoapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import org.androidpn.IQ.InquiryIQ;
import org.androidpn.utils.ActivityHolder;
import org.jivesoftware.smack.packet.IQ;

public class SearchViewActivity extends Activity implements SearchView.OnQueryTextListener{
    private SearchView searchView;
    private ListView listView;
    private final String[] mStrings = {"aaaa","bbbb","cccc","dddd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);
        searchView = (SearchView) findViewById(R.id.search_view);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mStrings));
        //启用ListView筛选器
        listView.setTextFilterEnabled(true);
        //设置该SearchView默认是否自动缩小为图标
        searchView.setIconifiedByDefault(false);
        //为该SearchView组件设置事件监听器
        searchView.setOnQueryTextListener(this);
        //设置该SearchView显示搜索按钮
        searchView.setSubmitButtonEnabled(true);
        //设置该SearchView内默认显示的提示文本
        searchView.setQueryHint("查找");
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //将选择的列表项内容填到搜索框中
                searchView.setQuery(mStrings[position],false);
            }
        });
    }

    /**
     * 单击搜索按钮时激发该方法
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(SearchViewActivity.this,"您的选择是"+query, Toast.LENGTH_SHORT).show();
        InquiryIQ inquiryIQ = new InquiryIQ();
        inquiryIQ.setType(IQ.Type.GET);

        inquiryIQ.setTarget("search");
        inquiryIQ.setTitle(query);

        Log.d("qzf", "onQueryTextSubmit: "+inquiryIQ.toXML());

        ActivityHolder.getInstance().sendPacket(inquiryIQ);

        SearchViewActivity.this.finish();
        return false;
    }

    /**
     * 用户输入字符时激发该方法
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        if(TextUtils.isEmpty(newText)){
            //清除ListView的过滤
            listView.clearTextFilter();
        }else {
            //使用用户输入的内容，对ListView的列表项进行过滤
            listView.setFilterText(newText);
        }
        return true;
    }
}
