package edu.fjnu.birdie.androiduitest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class XMLMenuActivity extends ActionBarActivity {
/* 配合手动添加菜单项使用
    final int FONT_10 = 0x111;
    final int FONT_16 = 0x112;
    final int FONT_20 = 0x113;

    final int PLAIN_ITEM = 0x11b;

    final int FONT_RED = 0x114;
    final int FONT_BLACK =0x115; */

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_menu);
        tv = (TextView)findViewById(R.id.menutest);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem mi){
        switch (mi.getItemId()){
            case R.id.font_10:
                tv.setTextSize(10 * 2);
                break;
            case R.id.font_16:
                tv.setTextSize(16 * 2);
                break;
            case R.id.font_20:
                tv.setTextSize(20 * 2);
                break;
            case R.id.color_red:
                tv.setTextColor(Color.RED);
                break;
            case R.id.color_black:
                tv.setTextColor(Color.BLACK);
                break;
            case R.id.normal:
                Toast.makeText(XMLMenuActivity.this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

//手动添加菜单项

//    @Override
//    public boolean onCreateOptionMenu(Menu menu){
//
//        SubMenu fontMenu = menu.addSubMenu("字体大小");
//            fontMenu.setHeaderTitle("选择字体大小");
//                fontMenu.add(0,FONT_10,0,"10号字体");
//                fontMenu.add(0,FONT_16,0,"16号字体");
//                fontMenu.add(0,FONT_20,0,"20号字体");
//
//        menu.add(0 ,PLAIN_ITEM ,0 ,"普通菜单项");
//
//        SubMenu colorMenu = menu.addSubMenu("字体颜色");
//            colorMenu.setHeaderTitle("选择文字颜色");
//                colorMenu.add(0,FONT_10,0,"红色");
//                colorMenu.add(0,FONT_16,0,"黑色");
//        return super.onCreateOptionsMenu(menu);
//    }

}

