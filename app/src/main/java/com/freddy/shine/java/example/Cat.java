package com.freddy.shine.java.example;

import com.google.gson.annotations.SerializedName;

/**
 * @author: FreddyChen
 * @date : 2022/01/15 03:59
 * @email : freddychencsc@gmail.com
 */
public class Cat {
    @SerializedName("status")
    private StatusDTO status;
    @SerializedName("_id")
    private String id;
    @SerializedName("type")
    private String type;
    @SerializedName("deleted")
    private Boolean deleted;
    @SerializedName("user")
    private String user;
    @SerializedName("text")
    private String text;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("__v")
    private Integer v;

    public StatusDTO getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public String getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public Integer getV() {
        return v;
    }

    public static class StatusDTO {
        @SerializedName("verified")
        private Object verified;
        @SerializedName("sentCount")
        private Integer sentCount;

        @Override
        public String toString() {
            return "StatusDTO{" +
                    "verified=" + verified +
                    ", sentCount=" + sentCount +
                    '}';
        }
    }
}
