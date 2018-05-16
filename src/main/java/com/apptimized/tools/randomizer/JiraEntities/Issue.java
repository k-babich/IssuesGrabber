package com.apptimized.tools.randomizer.JiraEntities;

public class Issue {

    private String key = null;
    private String summary = null;
    private String issueType = null;
    private String status = null;
    private String priority = null;
    private String description = null;

    private String assignee = null;
    private String packager = null;
    private String qaEngineer = null;
    private String reporter = null;
    private String creator = null;

    private String created = null;
    private String resolved = null;
    private String dueDate = null;

    private String applicationName = null;
    private String applicationVendor = null;
    private String applicationVersion = null;

    private String costCentre = null;
    private String packagingTechnology = null;
    private String sitePkg = null;
    private String siteQa = null;
    private String softwareId = null;
    private String categoryField = null;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    private String project = null;

    public String getKey() {
        return (key == null || key.equals("null")) ? "" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSummary() {
        return (summary == null || summary.equals("null"))  ? "" : summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIssueType() {
        return (issueType == null || issueType.equals("null")) ? "" : issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getStatus() {
        return (status == null || status.equals("null")) ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPriority() {
        return (priority == null || priority.equals("null")) ? "" : priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return (description == null || description.equals("null")) ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssignee() {
        return (assignee == null || assignee.equals("null")) ? "" : assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getPackager() {
        return (packager == null || packager.equals("null")) ? "" : packager;
    }

    public void setPackager(String packager) {
        this.packager = packager;
    }

    public String getQaEngineer() {
        return (qaEngineer == null || qaEngineer.equals("null")) ? "" : qaEngineer;
    }

    public void setQaEngineer(String qaEngineer) {
        this.qaEngineer = qaEngineer;
    }

    public String getReporter() {
        return (reporter == null || reporter.equals("null")) ? "" : reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getCreator() {
        return (creator == null || creator.equals("null")) ? "" : creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreated() {
        return (created == null || created.equals("null")) ? "" : created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getResolved() {
        return (resolved == null || resolved.equals("null")) ? "" : resolved;
    }

    public void setResolved(String resolved) {
        this.resolved = resolved;
    }

    public String getDueDate() {
        return (dueDate == null || dueDate.equals("null")) ? "" : dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getApplicationName() {
        return (applicationName == null || applicationName.equals("null")) ? "" : applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getApplicationVendor() {
        return (applicationVendor == null || applicationVendor.equals("null")) ? "" : applicationVendor;
    }

    public void setApplicationVendor(String applicationVendor) {
        this.applicationVendor = applicationVendor;
    }

    public String getApplicationVersion() {
        return (applicationVersion == null || applicationVersion.equals("null")) ? "" : applicationVersion;
    }

    public void setApplicationVersion(String applicationVersion) {
        this.applicationVersion = applicationVersion;
    }

    public String getCostCentre() {
        return (costCentre == null || costCentre.equals("null")) ? "" : costCentre;
    }

    public void setCostCentre(String costCentre) {
        this.costCentre = costCentre;
    }

    public String getPackagingTechnology() {
        return (packagingTechnology == null || packagingTechnology.equals("null")) ? "" : packagingTechnology;
    }

    public void setPackagingTechnology(String packagingTechnology) {
        this.packagingTechnology = packagingTechnology;
    }

    public String getSitePkg() {
        return (sitePkg == null || sitePkg.equals("null")) ? "" : sitePkg;
    }

    public void setSitePkg(String sitePkg) {
        this.sitePkg = sitePkg;
    }

    public String getSiteQa() {
        return (siteQa == null || siteQa.equals("null")) ? "" : siteQa;
    }

    public void setSiteQa(String siteQa) {
        this.siteQa = siteQa;
    }

    public String getSoftwareId() {
        return (softwareId == null || softwareId.equals("null")) ? "" : softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    public String getCategoryField() {
        return (categoryField == null || categoryField.equals("null")) ? "" : categoryField;
    }

    public void setCategoryField(String categoryField) {
        this.categoryField = categoryField;
    }

    public String[] getParamsAsArray() {
        return new String[]{getProject(), getKey(), getSummary(),
                getIssueType(), getStatus(), getPriority(), getSoftwareId(),
                getApplicationName(), getApplicationVendor(), getApplicationVersion(),
                getDescription(), getCategoryField(), getAssignee(),
                getReporter(), getPackager(), getQaEngineer(), getPackagingTechnology(),
                getSitePkg(), getSiteQa(), getCostCentre(), getCreated(), getDueDate(), getResolved()};
    }

}
