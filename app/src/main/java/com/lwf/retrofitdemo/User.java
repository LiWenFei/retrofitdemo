package com.lwf.retrofitdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwenfei on 2016/11/17.
 */

public class User implements Parcelable {

    private SessionBean session;
    private String name;
    private String real_name;
    private String phone;
    private int is_staff;
    private int position;
    private int id;
    private List<LogisListBean> logis_list;

    public SessionBean getSession() {
        return session;
    }

    public void setSession(SessionBean session) {
        this.session = session;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIs_staff() {
        return is_staff;
    }

    public void setIs_staff(int is_staff) {
        this.is_staff = is_staff;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LogisListBean> getLogis_list() {
        return logis_list;
    }

    public void setLogis_list(List<LogisListBean> logis_list) {
        this.logis_list = logis_list;
    }

    public static class SessionBean implements Parcelable {
        private String sid;

        protected SessionBean(Parcel in) {
            sid = in.readString();
        }

        public static final Creator<SessionBean> CREATOR = new Creator<SessionBean>() {
            @Override
            public SessionBean createFromParcel(Parcel in) {
                return new SessionBean(in);
            }

            @Override
            public SessionBean[] newArray(int size) {
                return new SessionBean[size];
            }
        };

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(sid);
        }

    }

    public static class LogisListBean {
        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.session, flags);
        dest.writeString(this.name);
        dest.writeString(this.real_name);
        dest.writeString(this.phone);
        dest.writeInt(this.is_staff);
        dest.writeInt(this.position);
        dest.writeInt(this.id);
        dest.writeList(this.logis_list);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.session = in.readParcelable(SessionBean.class.getClassLoader());
        this.name = in.readString();
        this.real_name = in.readString();
        this.phone = in.readString();
        this.is_staff = in.readInt();
        this.position = in.readInt();
        this.id = in.readInt();
        this.logis_list = new ArrayList<LogisListBean>();
        in.readList(this.logis_list, LogisListBean.class.getClassLoader());
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
