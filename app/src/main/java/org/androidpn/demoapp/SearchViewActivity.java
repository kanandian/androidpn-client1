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
import org.androidpn.entity.SearchHistory;
import org.androidpn.utils.ActivityHolder;
import org.androidpn.utils.UserInfoHolder;
import org.jivesoftware.smack.packet.IQ;
import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

public class SearchViewActivity extends Activity implements SearchView.OnQueryTextListener{
    private SearchView searchView;
    private ListView listView;
    private String[] mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        prepareData();

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
                String query = mStrings[position];
                if ("清除历史搜索记录".equals(query)) {
                    DataSupport.deleteAll(SearchHistory.class);
                    mStrings = new String[]{};
                    listView.setAdapter(new ArrayAdapter<String>(SearchViewActivity.this,android.R.layout.simple_list_item_1,mStrings));
                } else {
                    searchView.setQuery(query,false);
                }
            }
        });
    }

    public void prepareData() {
        List<SearchHistory> searchHistoryList = DataSupport.findAll(SearchHistory.class);
        mStrings = new String[searchHistoryList.size()+1];

        for (int i=0;i<searchHistoryList.size();i++) {
            SearchHistory searchHistory = searchHistoryList.get(i);
            mStrings[i] = searchHistory.getKey();
        }
        mStrings[searchHistoryList.size()] = "清除历史搜索记录";
    }

    /**
     * 单击搜索按钮时激发该方法
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
//        Toast.makeText(SearchViewActivity.this,"您的选择是"+query, Toast.LENGTH_SHORT).show();

        SearchHistory history = DataSupport.where("key = ?", query).findFirst(SearchHistory.class);

        if (history == null) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setKey(query);
            searchHistory.setUserName(UserInfoHolder.getInstance().getUserName());
            searchHistory.setCreateTime(new Date().getTime());
            searchHistory.save();
        }

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
