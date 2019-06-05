package com.glqdlt.ex;

public enum CustomOauth2ProviderEnum {

    MY_OAUTH_PROVIDER {
        @Override
        public String getProvider(String id) {
            return null;
        }
    };

    CustomOauth2ProviderEnum() {
    }

    public abstract String getProvider(String id);

}
