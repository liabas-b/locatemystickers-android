package com.locatemystickers;

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
import com.locatemystickers.json.UsersJSON;
import com.locatemystickers.menu.MenuActivity;
import com.locatemystickers.utils.SegmentedRadioGroup;

public class SearchActivity extends Fragment implements RadioGroup.OnCheckedChangeListener{
    private String _search = "";
    private String URNSTICKER = new String("?direction=asc&sort=id&search="+ _search +"&column=name");
    private String URNUSER = new String("?direction=asc&sort=id&search="+ _search + "&column=name");
    private MenuActivity _context;
    private EditText _editSearch;
    private SegmentedRadioGroup _segmentText;
    private SwipeListView _swipeListView;
    private int _idOnglet = 0;

    public static SearchActivity newInstance(MenuActivity context) {
        return new SearchActivity(context);
    }

    private SearchActivity(MenuActivity context) {
        _context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_activity, container, false);

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
                    return true;
                }
                return true;
            }
        });

        return view;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
    /*
    public void executeOnglet(int id) {
        Thread t = null;
        _search = _editSearch.getText().toString();
        if (!_search.isEmpty()) {
            switch (id) {
                case R.id.btn_everything:
                    _pd = _pd.show(_sv, "Please wait", "Loading list of everything");
                    t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            EverythingJSON e = new EverythingJSON(Singleton.getInstance()._sj.get_user_id());
                            _le = e.readSearchEverything(URNSTICKER, URNUSER);
                            _pd.dismiss();
                        }
                    });
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    _swipeListView.setAdapter(new EverythingAdapter(_sv, _le));
                    break;
                case R.id.btn_stickers:
                    _pd = _pd.show(_sv, "Please wait", "Loading list of stickers");
                    t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            _ls = Singleton.getInstance()._sj.readSearchSticker(URNSTICKER);
                            _pd.dismiss();
                        }
                    });
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    _swipeListView.setAdapter(new StickerAdapter(_context, _ls, _swipeListView));
                    break;
                case R.id.btn_people:
                    _pd = _pd.show(_sv, "Please wait", "Loading list of users...");
                    t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UsersJSON uj = new UsersJSON();
                            _lu = uj.readSearchUser(URNUSER);
                            _pd.dismiss();
                        }
                    });
                    t.start();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    _swipeListView.setAdapter(new UserAdapter(_sv, _lu));
                    break;
            }
       }
    }*/
}