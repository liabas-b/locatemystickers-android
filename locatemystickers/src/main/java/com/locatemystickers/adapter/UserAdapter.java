package com.locatemystickers.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.locatemystickers.R;
import com.locatemystickers.type.User;

import java.util.List;

public class UserAdapter extends BaseAdapter {
    private List<User>  _users;
    private Context _context;
    private ViewHolder _holder;
    private User _user;

    public UserAdapter(Context context, List<User> objects) {
        super();
        _users = objects;
        _context = context;
    }

    @Override
    public int getCount() {
        return _users.size();
    }

    @Override
    public User getItem(int i) {
        return _users.get(i);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        _user  = getItem(position);
        _holder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_userswipe, parent, false);
            _holder = new ViewHolder();
            _holder.txtUser = (TextView)convertView.findViewById(R.id.txtUser);
            _holder.txtCounterStickers = (TextView)convertView.findViewById(R.id.txtCounterStickers);
            convertView.setTag(_holder);
        } else {
            _holder = (ViewHolder)convertView.getTag();
        }
        _holder.txtUser.setText(_user.get_name());
        return convertView;
    }

    class ViewHolder {
        TextView txtUser;
        TextView txtCounterStickers;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}
