package com.locatemystickers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.locatemystickers.Everything;
import com.locatemystickers.R;
import com.locatemystickers.Sticker;
import com.locatemystickers.User;

import java.util.List;

public class EverythingAdapter extends BaseAdapter {
    private Context _context;
    private List<Everything> _levrything;

    public EverythingAdapter(Context context, List<Everything> levrything) {
        _context = context;
        _levrything = levrything;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).get_sticker() != null)
            return 0;
        return 1;
    }

    @Override
    public int getCount() {
        return _levrything.size();
    }

    @Override
    public Everything getItem(int i) {
        return _levrything.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Everything everything = getItem(i);
        ViewHolder holder = null;
        int type = getItemViewType(i);

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new ViewHolder();
            if (type == 0) {
                view = inflater.inflate(R.layout.row_stickerswipe, viewGroup, true);
                holder.tvNameSticker = (TextView)view.findViewById(R.id.tvNameSticker);

            } else {
                view = inflater.inflate(R.layout.row_userswipe, viewGroup, true);
                holder.v1 = (TextView)view.findViewById(R.id.txtUser);
            }
            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }
        if (type == 0) {
            holder.tvNameSticker.setText(everything.get_sticker().get_name());
        } else {
            holder.v1.setText(everything.get_user().get_name());
        }
        return view;
    }

    class ViewHolder {
        TextView v1;
        TextView tvNameSticker;
    }
}
