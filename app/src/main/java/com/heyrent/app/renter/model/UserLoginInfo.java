package com.heyrent.app.renter.model;

import java.util.List;

public class UserLoginInfo {

    /**
     * result : 0
     * data : {"expiresIn":1799,"additionalInformation":{"jti":"24cfc843-0ece-43d7-b57f-13de4e833e68"},"expired":false,"scope":["all"],"expiration":"2018-09-23T01:35:28.047+0000","tokenType":"bearer","value":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzc2NjY1MjgsInVzZXJfbmFtZSI6IjE3NjgiLCJqdGkiOiIyNGNmYzg0My0wZWNlLTQzZDctYjU3Zi0xM2RlNGU4MzNlNjgiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhbGwiXX0.ewqzeElqe6H-xWDQIt4WnGDqfU7YN1nsdfKBaKpCKsw","refreshToken":{"expiration":"2018-09-23T02:05:28.047+0000","value":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzc2NjgzMjgsInVzZXJfbmFtZSI6IjE3NjgiLCJqdGkiOiJiYjE1M2FlZi1kNTE4LTQ4MjgtOGJjNC1jNDhhZmFjZjQ3YWYiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiMjRjZmM4NDMtMGVjZS00M2Q3LWI1N2YtMTNkZTRlODMzZTY4In0.C59ktgYpYJyXMRZRIegncDqb07X49cA7Jvb4tNpA8x8"}}
     * message : success
     */

    private int result;
    private DataBean data;
    private String message;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return result == 0;
    }

    public static class DataBean {
        /**
         * expiresIn : 1799
         * additionalInformation : {"jti":"24cfc843-0ece-43d7-b57f-13de4e833e68"}
         * expired : false
         * scope : ["all"]
         * expiration : 2018-09-23T01:35:28.047+0000
         * tokenType : bearer
         * value : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzc2NjY1MjgsInVzZXJfbmFtZSI6IjE3NjgiLCJqdGkiOiIyNGNmYzg0My0wZWNlLTQzZDctYjU3Zi0xM2RlNGU4MzNlNjgiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhbGwiXX0.ewqzeElqe6H-xWDQIt4WnGDqfU7YN1nsdfKBaKpCKsw
         * refreshToken : {"expiration":"2018-09-23T02:05:28.047+0000","value":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzc2NjgzMjgsInVzZXJfbmFtZSI6IjE3NjgiLCJqdGkiOiJiYjE1M2FlZi1kNTE4LTQ4MjgtOGJjNC1jNDhhZmFjZjQ3YWYiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiMjRjZmM4NDMtMGVjZS00M2Q3LWI1N2YtMTNkZTRlODMzZTY4In0.C59ktgYpYJyXMRZRIegncDqb07X49cA7Jvb4tNpA8x8"}
         */

        private int expiresIn;
        private AdditionalInformationBean additionalInformation;
        private boolean expired;
        private String expiration;
        private String tokenType;
        private String value;
        private RefreshTokenBean refreshToken;
        private List<String> scope;

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }

        public AdditionalInformationBean getAdditionalInformation() {
            return additionalInformation;
        }

        public void setAdditionalInformation(AdditionalInformationBean additionalInformation) {
            this.additionalInformation = additionalInformation;
        }

        public boolean isExpired() {
            return expired;
        }

        public void setExpired(boolean expired) {
            this.expired = expired;
        }

        public String getExpiration() {
            return expiration;
        }

        public void setExpiration(String expiration) {
            this.expiration = expiration;
        }

        public String getTokenType() {
            return tokenType;
        }

        public void setTokenType(String tokenType) {
            this.tokenType = tokenType;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public RefreshTokenBean getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(RefreshTokenBean refreshToken) {
            this.refreshToken = refreshToken;
        }

        public List<String> getScope() {
            return scope;
        }

        public void setScope(List<String> scope) {
            this.scope = scope;
        }

        public static class AdditionalInformationBean {
            /**
             * jti : 24cfc843-0ece-43d7-b57f-13de4e833e68
             */

            private String jti;

            public String getJti() {
                return jti;
            }

            public void setJti(String jti) {
                this.jti = jti;
            }
        }

        public static class RefreshTokenBean {
            /**
             * expiration : 2018-09-23T02:05:28.047+0000
             * value : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzc2NjgzMjgsInVzZXJfbmFtZSI6IjE3NjgiLCJqdGkiOiJiYjE1M2FlZi1kNTE4LTQ4MjgtOGJjNC1jNDhhZmFjZjQ3YWYiLCJjbGllbnRfaWQiOiJjbGllbnQiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiMjRjZmM4NDMtMGVjZS00M2Q3LWI1N2YtMTNkZTRlODMzZTY4In0.C59ktgYpYJyXMRZRIegncDqb07X49cA7Jvb4tNpA8x8
             */

            private String expiration;
            private String value;

            public String getExpiration() {
                return expiration;
            }

            public void setExpiration(String expiration) {
                this.expiration = expiration;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
