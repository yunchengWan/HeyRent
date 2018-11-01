package com.heyrent.app.renter.model;

public class UserInfoBean {

    /**
     * userInfo : {"birthday":"1998-09-02T00:00:00.000+0000","firstName":"wan","lastName":"yuncheng","phone":"13438483753","contactAddr":"","identityId":"us-west-2:ece1c0b3-2914-4898-bfc7-f0c43b5f70df","avatar":"","id":1769,"userId":1768}
     * credentialsNonExpired : false
     * userId : 1768
     * authorities : null
     * enabled : false
     * uid : Heyrent_13438483753@163.com_20180919023824736
     * password : null
     * userState : 1
     * accountNonExpired : false
     * id : 1768
     * userType : 2
     * email : 13438483753@163.com
     * accountNonLocked : false
     * createDate : 2018-09-19T02:38:25.000+0000
     * username : null
     */

    private UserInfo userInfo;
    private boolean credentialsNonExpired;
    private String userId;
    private Object authorities;
    private boolean enabled;
    private String uid;
    private Object password;
    private int userState;
    private boolean accountNonExpired;
    private int id;
    private int userType;
    private String email;
    private boolean accountNonLocked;
    private String createDate;
    private Object username;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Object authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public static class UserInfo {
        /**
         * birthday : 1998-09-02T00:00:00.000+0000
         * firstName : wan
         * lastName : yuncheng
         * phone : 13438483753
         * contactAddr :
         * identityId : us-west-2:ece1c0b3-2914-4898-bfc7-f0c43b5f70df
         * avatar :
         * id : 1769
         * userId : 1768
         */

        private String birthday;
        private String firstName;
        private String lastName;
        private String phone;
        private String contactAddr;
        private String identityId;
        private String avatar;
        private int id;
        private int userId;

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getContactAddr() {
            return contactAddr;
        }

        public void setContactAddr(String contactAddr) {
            this.contactAddr = contactAddr;
        }

        public String getIdentityId() {
            return identityId;
        }

        public void setIdentityId(String identityId) {
            this.identityId = identityId;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
