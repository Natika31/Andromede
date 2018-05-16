package com.example.paulineb.andromede;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by PaulineB on 17/04/18.
 */

public class User {

    private String company;
    private String title;
    private String firstname;
    private String lastname;
    private String address;
    private Integer cp;
    private String city;
    private String email;
    private Boolean organisor;

    public User (String company, String title, String firstname, String lastname, Integer cp, String city, String address, String email, Boolean organisor ) {
        this.company=company;
        this.title=title;
        this.firstname=firstname;
        this.lastname=lastname;
        this.cp=cp;
        this.city=city;
        this.address=address;
        this.email=email;
        this.organisor=organisor;
    }

    public User (JSONObject user) throws JSONException {
        this.company=user.getString("company");
        this.title=user.getString("title");
        this.firstname=user.getString("firstname");
        this.lastname=user.getString("lastname");
        this.cp=user.getInt("cp");
        this.city=user.getString("city");
        this.address=user.getString("address");
        this.email=user.getString("email");
        this.organisor=user.getBoolean("organisor");
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getOrganisor() {
        return organisor;
    }

    public void setOrganisor(Boolean organisor) {
        this.organisor = organisor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public JSONObject getAllData() throws JSONException {
        JSONObject data = new JSONObject();
        data.put("lastname", this.getLastname());
        data.put("firstname", this.getFirstname());
        data.put("company", this.getCompany());
        data.put("title", this.getTitle());
        data.put("cp", this.getCp());
        data.put("city", this.getCity());
        data.put("address", this.getAddress());
        data.put("email", this.getEmail());
        data.put("organisor", this.getOrganisor());

        return data;
    }
}
