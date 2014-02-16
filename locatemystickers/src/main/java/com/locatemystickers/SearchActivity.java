package com.locatemystickers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ged74.swipelistview.SwipeListView;
import com.locatemystickers.adapter.StickerAdapter;
import com.locatemystickers.adapter.UserAdapter;
import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.json.UsersJSON;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.type.Sticker;
import com.locatemystickers.type.User;
import com.locatemystickers.utils.SegmentedRadioGroup;

import java.util.List;

public class SearchActivity extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private String _search = "";
    private String URNSTICKER = new String("?direction=asc&sort=id&search="+ _search +"&column=name");
    private String URNUSER = new String("?direction=asc&sort=id&search="+ _search + "&column=name");
    private MenuActivity _context;
    private EditText _editSearch;
    private SegmentedRadioGroup _segmentText;
    private SwipeListView _swipeListView;
    private int _idOnglet = R.id.btn_stickers;
    private List<Sticker> _lSticker;
    private List<User> _lUser;

    public static SearchActivity newInstance(MenuActivity context) {
        return new SearchActivity(context);
    }

    private SearchActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_activity, container, false);
        final ProgressDialog pd = new ProgressDialog(_context);
        _editSearch = (EditText)view.findViewById(R.id.editSearch);
        _segmentText = (SegmentedRadioGroup)view.findViewById(R.id.segment_text);
        _segmentText.setOnCheckedChangeListener(this);
        _swipeListView = (SwipeListView)view.findViewById(R.id.lvEsp);
        _editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    searchSegment();
                    return true;
                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        _idOnglet = checkedId;
        searchSegment();
    }

    private void searchSegment() {
        _search = _editSearch.getText().toString();
        switch (_idOnglet){
            case R.id.btn_stickers:
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            _lSticker = new StickersJSON(Singleton.getInstance()._id).readSearchSticker(URNSTICKER);
                        }
                    });
                    t.start(); t.join();
                    if (!_lSticker.isEmpty())
                        _swipeListView.setAdapter(new StickerAdapter(_context, _lSticker, _swipeListView));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_people:
                try {
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            _lUser = new UsersJSON().readSearchUser(URNUSER);
                        }
                    });
                    t.start(); t.join();
                    if (!_lUser.isEmpty())
                        _swipeListView.setAdapter(new UserAdapter(_context, _lUser));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
