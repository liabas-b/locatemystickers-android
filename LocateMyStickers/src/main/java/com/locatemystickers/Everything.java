package com.locatemystickers;

public class Everything {
    private User _user = null;
    private Sticker _sticker = null;

    public Everything(User user)
    {
        _user = user;
    }

    public Everything(Sticker sticker)
    {
        _sticker = sticker;
    }

    public User get_user() {
        return _user;
    }

    public Sticker get_sticker() {
        return _sticker;
    }
}
