package edu.fjnu.birdie.androiduitest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class MyAdapter extends BaseAdapter {

    private List<String> mList;

    private Context mContext;
    /**
     * 用来记录item的状态，1表示选中，0表示未选中
     */
    private int[] mItemState;
    private boolean isCachedBackground = false;
    private Drawable mBackground;
    private boolean mActionModeStarted = false;

    public MyAdapter(Context context, List<String> list) {
        mList = list;
        mContext = context;
        mItemState = new int[mList.size()];
        for (int i = 0; i < mItemState.length; i++) {
            mItemState[i] = 0;
        }

    }
//        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
//        for(int i=0 ;i<names.length ; i++){
//            Map<String,Object> listItem = new HashMap<String,Object>();
//            listItem.put("name",names[i]);
//            listItem.put("image",imageId);
//            listItems.add(listItem);
//        SimpleAdapter simpleAdapter = new SimpleAdapter(this ,listItems ,R.layout.simple_item,
//                new String[] {"image","name"},new int[] {R.id.image,R.id.name});
//        listView.setAdapter(simpleAdapter);





    public int[] getItemState() {
        return mItemState;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.simple_item, parent, false);
            holder.mTv = (TextView) convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //首先缓存原来的background
        if (!isCachedBackground) {
            isCachedBackground = true;
            mBackground = convertView.getBackground();
        }
        updateBackground(position, convertView);
        holder.mTv.setText(getItem(position));
        return convertView;
    }

    public int getCheckedItemCount() {
        int count = 0;
        for (int i : mItemState) {
            if (i == 1) count++;
        }
        return count;
    }

    private void updateBackground(int position,View convertView) {
        if (getItemState()[position] == 1) {
            convertView.setBackgroundColor(0xFFDFDFDF);
        } else if (getItemState()[position] == 0){
            convertView.setBackgroundDrawable(mBackground);
        }
    }

    public void checkAll() {
        for (int i = 0; i < mItemState.length; i++) {
            mItemState[i] = 1;
        }
    }

    public void unCheckAll() {
        for (int i = 0; i < mItemState.length; i++) {
            mItemState[i] = 0;
        }
    }

    public boolean isAllChecked() {
        for (int i : mItemState) {
            if (i == 0) {
                return false;
            }
        }
        return true;
    }

    public void setActionModeState(boolean flag) {
        mActionModeStarted = flag;
    }

    public boolean isActionModeStarted() {
        return mActionModeStarted;
    }

    public void deleteSelectedItems() {
        for (int i = mItemState.length - 1; i >= 0; i--) {
            if (mItemState[i] == 1) {
                mList.remove(i);
            }
        }
        notifyDataSetChanged();
        mItemState = new int[mList.size()];
        for (int i : mItemState) {
            i = 0;
        }
    }

    public boolean isActionModeStart() {
        return mActionModeStarted;
    }

    static class ViewHolder{

        TextView mTv;

    }
}