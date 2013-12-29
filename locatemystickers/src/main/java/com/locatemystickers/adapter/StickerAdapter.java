package com.locatemystickers.adapter;

import java.util.List;

import com.ged74.swipelistview.SwipeListView;
import com.locatemystickers.Singleton;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.type.Sticker;
import com.locatemystickers.utils.JDate;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.locatemystickers.R;

public class StickerAdapter extends BaseAdapter {
	private List<Sticker>	_stickers;
	private MenuActivity _context;
	private ViewHolder _holder;
    private SwipeListView _swipeListView;
    private Sticker _sticker;
	
    public StickerAdapter(MenuActivity context, List<Sticker> objects, SwipeListView swipeListView) {
        _stickers = objects;
        _context = context;
        _swipeListView = swipeListView;
    }

	@Override
	public int getCount() {
		return _stickers.size();
	}

	@Override
	public Sticker getItem(int position) {
		return _stickers.get(position);
	}

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void editRow() {
        try  {
            if (!_sticker.get_color().equals("null"))
                _holder.barcolor.setBackgroundColor(Color.parseColor(_sticker.get_color()));
            else
                _holder.barcolor.setBackgroundColor(Color.WHITE);
        } catch (IllegalArgumentException e) {
            Log.e(StickerAdapter.class.getName(), _sticker.get_name() + " - " + e.getMessage());
        }
        _holder.name.setText(_sticker.get_name());
        JDate jdate = new JDate(_sticker.get_updated_at());
        _holder.update_at.setText(jdate.toStringDiffNow());
        if (_sticker.get_is_active())
            _holder.status.setImageResource(android.R.drawable.presence_online);
        else
            _holder.status.setImageResource(android.R.drawable.presence_offline);
    }

    @Override
	public View getView(final int position, View convertView, ViewGroup parent) {
        _sticker = getItem(position);
        _holder = null;
    	
        if (convertView == null)
		{
            LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_stickerswipe, parent, false);
            _holder = new ViewHolder();
            _holder.barcolor = (View)convertView.findViewById(R.id.vbarcolor);
            _holder.name = (TextView)convertView.findViewById(R.id.tvNameSticker);
            _holder.update_at = (TextView)convertView.findViewById(R.id.tvUpdateAtSticker);
            _holder.status = (ImageView)convertView.findViewById(R.id.ivStatus);
            _holder.share = (ImageButton)convertView.findViewById(R.id.btnShare);
            _holder.globe = (ImageButton)convertView.findViewById(R.id.btnGlobe);
            _holder.lms = (ImageButton)convertView.findViewById(R.id.btnIconLogo);
            _holder.editDelete = (ImageButton)convertView.findViewById(R.id.btnEditDelete);
            _holder.mult = (ImageButton)convertView.findViewById(R.id.btnMult);
            convertView.setTag(_holder);
        } else {
            _holder = (ViewHolder)convertView.getTag();
        }
        editRow();
		_holder.share.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                       _sticker = Singleton.getInstance()._sj.readSticker(_sticker.get_id());
                    }
                });
				t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                editRow();
                _swipeListView.closeAnimate(position);
			}
		});
		
		_holder.globe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		
		_holder.lms.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                //TODO _context.fragIntent(StickerDetailActivity.newInstance(_context, _sticker.get_id()));
			}
		});
		
		_holder.editDelete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				        	Singleton.getInstance()._sj.deleteSticker(_sticker.get_id());
                            _stickers.remove(_sticker);
                            _swipeListView.closeAnimate(position);
				        	StickerAdapter.this.notifyDataSetChanged();
				        	break;
				        case DialogInterface.BUTTON_NEGATIVE:
				         	break;
				        }
				    }
				};
				AlertDialog.Builder builder = new AlertDialog.Builder(_context);
				builder.setMessage(_context.getString(R.string.s_Quest01)).setPositiveButton(_context.getString(android.R.string.yes), dialogClickListener)
				    .setNegativeButton(_context.getString(android.R.string.no), dialogClickListener).show();
			}
		});
		
		_holder.mult.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                _swipeListView.closeAnimate(position);
			}
		});
		return convertView;
	}
	
    class ViewHolder {
        View barcolor;
    	TextView name;
    	TextView update_at;
    	ImageView status;
    	ImageButton share;
    	ImageButton globe;
    	ImageButton lms;
    	ImageButton editDelete;
    	ImageButton mult;
    }
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}


}
