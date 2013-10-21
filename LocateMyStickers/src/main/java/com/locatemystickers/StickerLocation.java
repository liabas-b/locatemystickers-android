package com.locatemystickers;

public class StickerLocation {
	private String _created_at;
	private int _id;
	private double _latitude;
	private double _longitude;
	private int _sticker_id;
	private String _updated_at;
	private int _version;
	
	public StickerLocation(String created_at,
					int id,
					double latitute,
					double longitude,
					int sticker_id,
					String updated_at,
					int version) {
		set_created_at(created_at);
		set_id(id);
		set_latitude(latitute);
		set_longitude(longitude);
		set_sticker_id(sticker_id);
		set_updated_at(updated_at);
		set_version(version);
	}
	
	public StickerLocation() {
		
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

	public double get_latitude() {
		return _latitude;
	}

	public void set_latitude(double _latitude) {
		this._latitude = _latitude;
	}

	public double get_longitude() {
		return _longitude;
	}

	public void set_longitude(double _longitude) {
		this._longitude = _longitude;
	}

	public int get_sticker_id() {
		return _sticker_id;
	}

	public void set_sticker_id(int _sticker_id) {
		this._sticker_id = _sticker_id;
	}

	public String get_updated_at() {
		return _updated_at;
	}

	public void set_updated_at(String _updated_at) {
		this._updated_at = _updated_at;
	}

	public int get_version() {
		return _version;
	}

	public void set_version(int _version) {
		this._version = _version;
	}
}
