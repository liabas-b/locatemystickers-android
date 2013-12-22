package com.locatemystickers.Menu;

public class ItemMenu {
    private Integer _imgMenu;
    private String _txtMenu;

    public ItemMenu(Integer imgMenu, String txtMenu)
    {
        _imgMenu = imgMenu;
        _txtMenu = txtMenu;
    }

    public String get_txtMenu() {
        return _txtMenu;
    }

    public void set_txtMenu(String _txtMenu) {
        this._txtMenu = _txtMenu;
    }

    public Integer get_imgMenu() {
        return _imgMenu;
    }

    public void set_imgMenu(Integer _imgMenu) {
        this._imgMenu = _imgMenu;
    }
}
