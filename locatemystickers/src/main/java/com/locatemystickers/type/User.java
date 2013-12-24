package com.locatemystickers.type;

public class User {

    private boolean _is_admin;
    private String _adress;
    private String _city;
    private int _compteur;
    private String _country;
    private String _created_at;
    private String _email;
    private String _first_name;
    private int _id;
    private String _last_name;
    private String _name;
    private String _password_digest;
    private String _phone;
    private String _remember_token;
    private String _updated_at;
    private String _zip_code;

    public User() {}

    public User(String adress,
                String city,
                int compteur,
                String country,
                String email,
                String name) {
        _adress = adress;
        _city = city;
        _compteur = compteur;
        _country = country;
        _email = email;
        _name = name;
    }

    public User(boolean _is_admin,
                String _adress,
                String _city,
                int _compteur,
                String _country,
                String _created_at,
                String _email,
                String _first_name,
                int _id,
                String _last_name,
                String _name,
                String _password_digest,
                String _phone,
                String _remember_token,
                String _updated_at,
                String _zip_code) {
        this._is_admin = _is_admin;
        this._adress = _adress;
        this._city = _city;
        this._compteur = _compteur;
        this._country = _country;
        this._created_at = _created_at;
        this._email = _email;
        this._first_name = _first_name;
        this._id = _id;
        this._last_name = _last_name;
        this._name = _name;
        this._password_digest = _password_digest;
        this._phone = _phone;
        this._remember_token = _remember_token;
        this._updated_at = _updated_at;
        this._zip_code = _zip_code;
    }

    public boolean is_admin() {
        return _is_admin;
    }

    public void set_admin(boolean _is_admin) {
        this._is_admin = _is_admin;
    }

    public String get_adress() {
        return _adress;
    }

    public void set_adress(String _adress) {
        this._adress = _adress;
    }

    public String get_city() {
        return _city;
    }

    public void set_city(String _city) {
        this._city = _city;
    }

    public int get_compteur() {
        return _compteur;
    }

    public void set_compteur(int _compteur) {
        this._compteur = _compteur;
    }

    public String get_country() {
        return _country;
    }

    public void set_country(String _country) {
        this._country = _country;
    }

    public String get_created_at() {
        return _created_at;
    }

    public void set_created_at(String _created_at) {
        this._created_at = _created_at;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_first_name() {
        return _first_name;
    }

    public void set_first_name(String _first_name) {
        this._first_name = _first_name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_last_name() {
        return _last_name;
    }

    public void set_last_name(String _last_name) {
        this._last_name = _last_name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password_digest() {
        return _password_digest;
    }

    public void set_password_digest(String _password_digest) {
        this._password_digest = _password_digest;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_remember_token() {
        return _remember_token;
    }

    public void set_remember_token(String _remember_token) {
        this._remember_token = _remember_token;
    }

    public String get_updated_at() {
        return _updated_at;
    }

    public void set_updated_at(String _updated_at) {
        this._updated_at = _updated_at;
    }

    public String get_zip_code() {
        return _zip_code;
    }

    public void set_zip_code(String _zip_code) {
        this._zip_code = _zip_code;
    }
}
