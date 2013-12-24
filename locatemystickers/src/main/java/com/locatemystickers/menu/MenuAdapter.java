package com.locatemystickers.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.locatemystickers.menu.ItemMenu;
import com.locatemystickers.R;

import java.util.List;

public class MenuAdapter extends BaseAdapter {

    private Context _context;
    private List<ItemMenu> _listItem;

    public MenuAdapter(Context context, List<ItemMenu> listItem) {
        _context = context;
        _listItem = listItem;
    }

    @Override
    public int getCount() { return _listItem.size(); }
    @Override
    public ItemMenu getItem(int position) { return _listItem.get(position); }
    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemMenu item = getItem(position);
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.rowmenu, parent, false);
            holder._textView = (TextView)convertView.findViewById(R.id.textMenu);
            holder._imageView = (ImageView)convertView.findViewById(R.id.imgMenu);
            convertView.setTag(holder);
        } else { holder = (ViewHolder)convertView.getTag(); }
        holder._imageView.setImageResource(item.get_imgMenu());
        holder._textView.setText(item.get_txtMenu());
        return convertView;
    }

    private class ViewHolder {
        private ImageView _imageView;
        private TextView _textView;
    }
}
