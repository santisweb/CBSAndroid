package com.cristalbusinessservices.Model.Appointments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contact {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("organizationId")
    @Expose
    private Integer organizationId;
    @SerializedName("contactType")
    @Expose
    private Integer contactType;
    @SerializedName("title")
    @Expose
    private Object title;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("middleName")
    @Expose
    private Object middleName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("dateofBirth")
    @Expose
    private Object dateofBirth;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("homePhone")
    @Expose
    private Object homePhone;
    @SerializedName("mobilePhone")
    @Expose
    private String mobilePhone;
    @SerializedName("workPhone")
    @Expose
    private Object workPhone;
    @SerializedName("fileName")
    @Expose
    private Object fileName;
    @SerializedName("cdnPartialPath")
    @Expose
    private Object cdnPartialPath;
    @SerializedName("businessId")
    @Expose
    private Integer businessId;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("createdByUserId")
    @Expose
    private Integer createdByUserId;
    @SerializedName("createdDateUtc")
    @Expose
    private String createdDateUtc;
    @SerializedName("changedDateUtc")
    @Expose
    private Object changedDateUtc;
    @SerializedName("changedByUserId")
    @Expose
    private Object changedByUserId;
    @SerializedName("changedByUserIp")
    @Expose
    private Object changedByUserIp;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("company")
    @Expose
    private Object company;
    @SerializedName("business")
    @Expose
    private Object business;
    @SerializedName("contactUser")
    @Expose
    private Object contactUser;
    @SerializedName("assignedToContact")
    @Expose
    private Object assignedToContact;
    @SerializedName("recepientId")
    @Expose
    private Integer recepientId;
    @SerializedName("isSyncWithExternal")
    @Expose
    private Boolean isSyncWithExternal;
    @SerializedName("syncDate")
    @Expose
    private Object syncDate;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
    @SerializedName("contactStatus")
    @Expose
    private Integer contactStatus;
    @SerializedName("contactTypeName")
    @Expose
    private Integer contactTypeName;
    @SerializedName("referredByAffiliateId")
    @Expose
    private Object referredByAffiliateId;
    @SerializedName("contactSourceId")
    @Expose
    private Integer contactSourceId;
    @SerializedName("shortDescription")
    @Expose
    private Object shortDescription;
    @SerializedName("contactLeadStatus")
    @Expose
    private Object contactLeadStatus;
    @SerializedName("assignedToContactId")
    @Expose
    private Object assignedToContactId;
    @SerializedName("companyId")
    @Expose
    private Integer companyId;
    @SerializedName("companyName")
    @Expose
    private Object companyName;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("profilePhotoUrl")
    @Expose
    private Object profilePhotoUrl;
    @SerializedName("address")
    @Expose
    private Object address;
    @SerializedName("organization")
    @Expose
    private Object organization;
    @SerializedName("contactSource")
    @Expose
    private Object contactSource;
    @SerializedName("socialSecurityNumber")
    @Expose
    private Object socialSecurityNumber;
    @SerializedName("aptNumber")
    @Expose
    private Object aptNumber;
    @SerializedName("occupation")
    @Expose
    private Object occupation;
    @SerializedName("leadStatus")
    @Expose
    private Integer leadStatus;
    @SerializedName("contactSourceName")
    @Expose
    private Object contactSourceName;
    @SerializedName("contactServiceNames")
    @Expose
    private Object contactServiceNames;
    @SerializedName("contactServiceSubscriptions")
    @Expose
    private List<Object> contactServiceSubscriptions = null;
    @SerializedName("selectedContactServiceSubscriptionIds")
    @Expose
    private Object selectedContactServiceSubscriptionIds;
    @SerializedName("ssnLastFourDigit")
    @Expose
    private String ssnLastFourDigit;
    @SerializedName("ssnNumber")
    @Expose
    private String ssnNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getContactType() {
        return contactType;
    }

    public void setContactType(Integer contactType) {
        this.contactType = contactType;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getMiddleName() {
        return middleName;
    }

    public void setMiddleName(Object middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Object getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(Object dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(Object homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Object getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(Object workPhone) {
        this.workPhone = workPhone;
    }

    public Object getFileName() {
        return fileName;
    }

    public void setFileName(Object fileName) {
        this.fileName = fileName;
    }

    public Object getCdnPartialPath() {
        return cdnPartialPath;
    }

    public void setCdnPartialPath(Object cdnPartialPath) {
        this.cdnPartialPath = cdnPartialPath;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Integer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getCreatedDateUtc() {
        return createdDateUtc;
    }

    public void setCreatedDateUtc(String createdDateUtc) {
        this.createdDateUtc = createdDateUtc;
    }

    public Object getChangedDateUtc() {
        return changedDateUtc;
    }

    public void setChangedDateUtc(Object changedDateUtc) {
        this.changedDateUtc = changedDateUtc;
    }

    public Object getChangedByUserId() {
        return changedByUserId;
    }

    public void setChangedByUserId(Object changedByUserId) {
        this.changedByUserId = changedByUserId;
    }

    public Object getChangedByUserIp() {
        return changedByUserIp;
    }

    public void setChangedByUserIp(Object changedByUserIp) {
        this.changedByUserIp = changedByUserIp;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Object getCompany() {
        return company;
    }

    public void setCompany(Object company) {
        this.company = company;
    }

    public Object getBusiness() {
        return business;
    }

    public void setBusiness(Object business) {
        this.business = business;
    }

    public Object getContactUser() {
        return contactUser;
    }

    public void setContactUser(Object contactUser) {
        this.contactUser = contactUser;
    }

    public Object getAssignedToContact() {
        return assignedToContact;
    }

    public void setAssignedToContact(Object assignedToContact) {
        this.assignedToContact = assignedToContact;
    }

    public Integer getRecepientId() {
        return recepientId;
    }

    public void setRecepientId(Integer recepientId) {
        this.recepientId = recepientId;
    }

    public Boolean getIsSyncWithExternal() {
        return isSyncWithExternal;
    }

    public void setIsSyncWithExternal(Boolean isSyncWithExternal) {
        this.isSyncWithExternal = isSyncWithExternal;
    }

    public Object getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Object syncDate) {
        this.syncDate = syncDate;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }

    public Integer getContactTypeName() {
        return contactTypeName;
    }

    public void setContactTypeName(Integer contactTypeName) {
        this.contactTypeName = contactTypeName;
    }

    public Object getReferredByAffiliateId() {
        return referredByAffiliateId;
    }

    public void setReferredByAffiliateId(Object referredByAffiliateId) {
        this.referredByAffiliateId = referredByAffiliateId;
    }

    public Integer getContactSourceId() {
        return contactSourceId;
    }

    public void setContactSourceId(Integer contactSourceId) {
        this.contactSourceId = contactSourceId;
    }

    public Object getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(Object shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Object getContactLeadStatus() {
        return contactLeadStatus;
    }

    public void setContactLeadStatus(Object contactLeadStatus) {
        this.contactLeadStatus = contactLeadStatus;
    }

    public Object getAssignedToContactId() {
        return assignedToContactId;
    }

    public void setAssignedToContactId(Object assignedToContactId) {
        this.assignedToContactId = assignedToContactId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Object getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Object companyName) {
        this.companyName = companyName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Object getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(Object profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getOrganization() {
        return organization;
    }

    public void setOrganization(Object organization) {
        this.organization = organization;
    }

    public Object getContactSource() {
        return contactSource;
    }

    public void setContactSource(Object contactSource) {
        this.contactSource = contactSource;
    }

    public Object getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(Object socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Object getAptNumber() {
        return aptNumber;
    }

    public void setAptNumber(Object aptNumber) {
        this.aptNumber = aptNumber;
    }

    public Object getOccupation() {
        return occupation;
    }

    public void setOccupation(Object occupation) {
        this.occupation = occupation;
    }

    public Integer getLeadStatus() {
        return leadStatus;
    }

    public void setLeadStatus(Integer leadStatus) {
        this.leadStatus = leadStatus;
    }

    public Object getContactSourceName() {
        return contactSourceName;
    }

    public void setContactSourceName(Object contactSourceName) {
        this.contactSourceName = contactSourceName;
    }

    public Object getContactServiceNames() {
        return contactServiceNames;
    }

    public void setContactServiceNames(Object contactServiceNames) {
        this.contactServiceNames = contactServiceNames;
    }

    public List<Object> getContactServiceSubscriptions() {
        return contactServiceSubscriptions;
    }

    public void setContactServiceSubscriptions(List<Object> contactServiceSubscriptions) {
        this.contactServiceSubscriptions = contactServiceSubscriptions;
    }

    public Object getSelectedContactServiceSubscriptionIds() {
        return selectedContactServiceSubscriptionIds;
    }

    public void setSelectedContactServiceSubscriptionIds(Object selectedContactServiceSubscriptionIds) {
        this.selectedContactServiceSubscriptionIds = selectedContactServiceSubscriptionIds;
    }

    public String getSsnLastFourDigit() {
        return ssnLastFourDigit;
    }

    public void setSsnLastFourDigit(String ssnLastFourDigit) {
        this.ssnLastFourDigit = ssnLastFourDigit;
    }

    public String getSsnNumber() {
        return ssnNumber;
    }

    public void setSsnNumber(String ssnNumber) {
        this.ssnNumber = ssnNumber;
    }

}
