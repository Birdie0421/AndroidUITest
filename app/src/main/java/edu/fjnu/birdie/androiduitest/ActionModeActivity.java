package edu.fjnu.birdie.androiduitest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.os.Build;


public class ActionModeActivity extends AppCompatActivity {

    final int MENU1 = 0x111;
    final int MENU2 = 0x112;
    final int MENU3 = 0x113;


    private String[] names = new String[]{
            "One" ,"Two" ,"Three" ,"Four" ,"Five"
    };
    private int imageId = R.mipmap.ic_launcher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_mode_activity);

        final ListView listView = (ListView) findViewById(R.id.list);
        listAdd(listView);


        //测试点击ListView
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                {
                    Toast.makeText(getApplicationContext(), names[position],
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册上下文菜单
        registerForContextMenu(listView);


        //上下文操作
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB)
        {
            registerForContextMenu(listView);
        }
        else
        {
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

            listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

                @Override
                public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                    Log.d("MultiChoice", "onItemCheckedStateChanged");
                    Log.d("CheckNumber",  listView.getCheckedItemCount()+" ");
                    mode.setTitle(listView.getCheckedItemCount()+"已选择");
                }

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    MenuInflater inflater = mode.getMenuInflater();
                    inflater.inflate(R.menu.menu_action_mode, menu);
                    //mode.setTitle(listView.getCheckedItemCount()+"已选择");
                    //OnCreate 在一次上下文中只执行一次，getCheckedItemCount()为初始值0
                    Log.d("MultiChoice", "onCreateActionMode");
                    Log.d("CheckNumber",  listView.getCheckedItemCount()+" ");
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    Log.d("MultiChoice", "onPrepareActionMode");
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.menu_all:
                            Log.d("all","clicked");
                            for(int i=0;i<listView.getCount();i++)
                                    listView.setItemChecked(i,true );
                            Log.d("isAll","true");
                            Toast.makeText(ActionModeActivity.this,"all",Toast.LENGTH_LONG).show();
                            break;

                        case R.id.menu_none:
                            //listView.clearChoices();
                            for(int i=0;i<listView.getCount();i++)
                                listView.setItemChecked(i,false );
                            Toast.makeText(ActionModeActivity.this,"none",Toast.LENGTH_LONG).show();
                            break;

                        case R.id.menu_delete:
                            Toast.makeText(ActionModeActivity.this,"delete",Toast.LENGTH_LONG).show();
                            break;
                    }
                    return true;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    Log.d("MultiChoice", "onDestroyActionMode");
                }
            });
        }
    }

    //测试上下文菜单
    @Override
    public void onCreateContextMenu(ContextMenu menu ,View source ,ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MENU1, 0, "Menu1");
        menu.add(0, MENU2, 0, "Menu2");
        menu.add(0, MENU3, 0, "Menu3");
        menu.setGroupCheckable(0, true, true);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case MENU1:
                Toast.makeText(ActionModeActivity.this,"1",Toast.LENGTH_LONG).show();
                break;
            case MENU2:
                Toast.makeText(ActionModeActivity.this,"2",Toast.LENGTH_LONG).show();
                break;
            case MENU3:
                Toast.makeText(ActionModeActivity.this,"3",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }


    //使用simpleAdapter 往 Listview中添加数据
    public void listAdd(ListView listView){
        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0 ;i<names.length ; i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("name",names[i]);
            listItem.put("image",imageId);
            listItems.add(listItem);
            Log.d("listItem",i+"");
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this ,listItems ,R.layout.simple_item,
                new String[] {"image","name"},new int[] {R.id.image,R.id.name});
        listView.setAdapter(simpleAdapter);
        Log.d("Adapter","done");
    }

}



