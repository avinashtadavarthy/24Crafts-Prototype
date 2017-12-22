package com.twenty.four.crafts.auditions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by srikanth on 10/11/17.
 */

public class AuditionHelper {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("shootingLocation")
        @Expose
        private String shootingLocation;
        @SerializedName("auditionLocation")
        @Expose
        private String auditionLocation;
        @SerializedName("contactNo")
        @Expose
        private String contactNo;
        @SerializedName("__v")
        @Expose
        private Integer v;
        @SerializedName("coCreatorRequests")
        @Expose
        private List<Object> coCreatorRequests = null;
        @SerializedName("professions")
        @Expose
        private List<Object> professions = null;
        @SerializedName("applicantsId")
        @Expose
        private List<Object> applicantsId = null;
        @SerializedName("createdById")
        @Expose
        private List<String> createdById = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getShootingLocation() {
            return shootingLocation;
        }

        public void setShootingLocation(String shootingLocation) {
            this.shootingLocation = shootingLocation;
        }

        public String getAuditionLocation() {
            return auditionLocation;
        }

        public void setAuditionLocation(String auditionLocation) {
            this.auditionLocation = auditionLocation;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public Integer getV() {
            return v;
        }

        public void setV(Integer v) {
            this.v = v;
        }

        public List<Object> getCoCreatorRequests() {
            return coCreatorRequests;
        }

        public void setCoCreatorRequests(List<Object> coCreatorRequests) {
            this.coCreatorRequests = coCreatorRequests;
        }

        public List<Object> getProfessions() {
            return professions;
        }

        public void setProfessions(List<Object> professions) {
            this.professions = professions;
        }

        public List<Object> getApplicantsId() {
            return applicantsId;
        }

        public void setApplicantsId(List<Object> applicantsId) {
            this.applicantsId = applicantsId;
        }

        public List<String> getCreatedById() {
            return createdById;
        }

        public void setCreatedById(List<String> createdById) {
            this.createdById = createdById;
        }

}
