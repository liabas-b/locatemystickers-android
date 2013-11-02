package com.locatemystickers;

public class Sticker {
	private String _code = null;
    private String _color = null;
	private String _created_at;
	private int _id;
	private boolean _is_active;
    private String _last_latitude = null;
    private String _last_location = null;
    private String _last_longitude = null;
	private String _name;
	private int _sticker_type_id;
	private String _text;
	private String _updated_at;
	private int _user_id;
	private int _version;
	
	public Sticker(String code,
                   String color,
				   String created_at,
				   int id,
				   boolean status,
                   String last_latitude,
                   String last_location,
                   String last_longitude,
				   String name,
				   int sticker_type_id,
				   String text,
				   String update_at,
				   int user_id,
				   int version)
	{
		set_code(code);
        set_color(color);
		set_created_at(created_at);
		set_id(id);
		set_is_active(status);
        set_last_latitude(last_latitude);
        set_last_location(last_location);
        set_last_longitude(last_longitude);
		set_name(name);
		set_sticker_type_id(sticker_type_id);
		set_text(text);
		set_updated_at(update_at);
		set_user_id(user_id);
		set_version(version);
	}

    public Sticker()
	{
		
	}


    public String get_color() {
        return _color;
    }

    public void set_color(String _color) {
        this._color = _color;
    }

    public String get_last_latitude() {
        return _last_latitude;
    }

    public void set_last_latitude(String _last_latitude) {
        this._last_latitude = _last_latitude;
    }

    public String get_last_location() {
        return _last_location;
    }

    public void set_last_location(String _last_location) {
        this._last_location = _last_location;
    }

    public String get_last_longitude() {
        return _last_longitude;
    }

    public void set_last_longitude(String _last_longitude) {
        this._last_longitude = _last_longitude;
    }
	public String get_code() {
		return _code;
	}

	public void set_code(String _code) {
		this._code = _code;
	}

	public String get_created_at() {
		return _created_at;
	}

	public void set_created_at(String _created_at) {
		this._created_at = _created_at;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public boolean get_is_active() {
		return _is_active;
	}

	public void set_is_active(boolean _status) {
		this._is_active = _status;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public int get_sticker_type_id() {
		return _sticker_type_id;
	}

	public void set_sticker_type_id(int _sticker_type_id) {
		this._sticker_type_id = _sticker_type_id;
	}

	public String get_text() {
		return _text;
	}

	public void set_text(String _text) {
		this._text = _text;
	}

	public String get_updated_at() {
		return _updated_at;
	}

	public void set_updated_at(String _updated_at) {
		this._updated_at = _updated_at;
	}

	public int get_user_id() {
		return _user_id;
	}

	public void set_user_id(int _user_id) {
		this._user_id = _user_id;
	}

	public int get_version() {
		return _version;
	}

	public void set_version(int _version) {
		this._version = _version;
	}


}
