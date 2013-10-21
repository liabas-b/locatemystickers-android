package com.locatemystickers;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ged74.swipelistview.SwipeListView;
import com.locatemystickers.adapter.EverythingAdapter;
import com.locatemystickers.adapter.StickerAdapter;
import com.locatemystickers.adapter.UserAdapter;
import com.locatemystickers.json.EverythingJSON;
import com.locatemystickers.json.StickersJSON;
import com.locatemystickers.json.UsersJSON;
import com.locatemystickers.segmentedradio.SegmentedRadioGroup;

import org.json.JSONException;

import java.util.List;

public class SearchActivity extends Fragment implements RadioGroup.OnCheckedChangeListener{

    private EditText _editSearch;
    private SegmentedRadioGroup _segmentText;
    private SwipeListView _swipeListView;
    private CharSequence _charseq;
    private List<User>  _lu;
    private List<Sticker> _ls;
    private List<Everything> _le;
    private ProgressDialog _pd;
    private ScreenView _sv;
    private int _idOnglet = 0;
    private String _search = "";
    private String URNSTICKER = new String("?direction=asc&sort=id&search="+ _search +"&column=name");
    private String URNUSER = new String("?direction=asc&sort=id&search="+ _search + "&column=name");

    static SearchActivity newInstance(ScreenView sv) {
        return new SearchActivity(sv);
    }

    public SearchActivity(ScreenView sv) {
        _sv = sv;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = View.inflate(_sv, R.layout.search_activity, container);

        _editSearch = (EditText)v.findViewById(R.id.editSearch);
        _segmentText = (SegmentedRadioGroup)v.findViewById(R.id.segment_text);
        _segmentText.setOnCheckedChangeListener(this);
        _swipeListView = (SwipeListView)v.findViewById(R.id.lvEsp);

        _editSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH ||
                        i == EditorInfo.IME_ACTION_DONE ||
                        keyEvent.getAction() == KeyEvent.ACTION_DOWN &&
                                keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    executeOnglet(_idOnglet);
                    return true;
                }
                return true;
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }

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
                    _swipeListView.setAdapter(new StickerAdapter(_sv, _ls, _swipeListView));
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
    }

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (group == _segmentText) {
            _idOnglet = checkedId;
            executeOnglet(_idOnglet);
        }
    }
}
