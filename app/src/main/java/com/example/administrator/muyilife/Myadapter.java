package com.example.administrator.muyilife;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */

public class Myadapter extends BaseAdapter{

    private LayoutInflater layoutInflater;
    private List<ItemBean> mList;                                                     //数据源与适配器进行关联

    public Myadapter(Context context, List<ItemBean> list, ListView listView) {
        mList = list;
        layoutInflater = LayoutInflater.from(context);                                //context要使用当前adapter的界面对象layoutinflater（布局装载器对象）
    }



    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //返回每一项的显示内容
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //无任何优化处理，效率低下
        /*
        View view = layoutInflater.inflate(R.layout.item,null);//将xml文件装载成一个view对象
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_image);
        TextView title = (TextView)view.findViewById(R.id.tv_title);
        TextView content = (TextView)view.findViewById(R.id.tv_content);
        //取出数据并赋值
        ItemBean bean = mList.get(position);
        imageView.setImageResource(bean.ImageId);
        title.setText(bean.Title);
        content.setText(bean.Content);
        return view;
        */
        //view为被实例化，缓存池中无缓存
        /*   if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.item,null);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        TextView title = (TextView)convertView.findViewById(R.id.tv_title);
        TextView content = (TextView)convertView.findViewById(R.id.tv_content);
        //取出数据并赋值
        ItemBean bean = mList.get(position);
        imageView.setImageResource(bean.ImageId);
        title.setText(bean.Title);
        content.setText(bean.Content);
        return convertView;*/
        //比较好的适配方法
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        new ImageLoader().showImageByThread(viewHolder.imageView,mList.get(position).newsIconUrl);
        viewHolder.name.setText((CharSequence) mList.get(position).getNewsName());
        return convertView;
    }
    class ViewHolder {
        public ImageView imageView,imageView1;
        public TextView name,name1;
    }


}