package com.apptimized.tools.randomizer.Utils;

import com.apptimized.tools.randomizer.JiraEntities.Issue;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

public class JsonActions {

    public static ArrayList<Issue> parseIssues(String jsonArray) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String name;
        JsonNode rootArray = mapper.readTree(jsonArray);
        ArrayList<Issue> issuesList = new ArrayList<Issue>();
        for(JsonNode root : rootArray.path("issues")){
            Issue issue = new Issue();

            issue.setKey(root.path(ConstStorage.KEY_FIELD_ID).asText());
            JsonNode sub_node = root.path("fields").path(ConstStorage.PACKAGER_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setPackager(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.QA_ENGINEER_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setQaEngineer(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.ASSIGNEE_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setAssignee(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.PROJECT_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setProject(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.SUMMARY_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.toString().replace("\"", "");
                issue.setSummary(name);
            }

            sub_node = root.path("fields").path(ConstStorage.ISSUE_TYPE_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setIssueType(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.STATUS_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setStatus(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.REPORTER_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setReporter(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.CREATOR_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setCreator(name.equals("") ? "" : name);
            }

            issue.setCreated(root.path("fields").path(ConstStorage.CREATED_FIELD_ID).asText());
            issue.setResolved(root.path("fields").path(ConstStorage.RESOLVED_FIELD_ID).asText());
            issue.setDueDate(root.path("fields").path(ConstStorage.DUE_DATE_FIELD_ID).asText());
            issue.setApplicationName(root.path("fields").path(ConstStorage.APPLICATION_NAME_FIELD_ID).asText());
            issue.setApplicationVendor(root.path("fields").path(ConstStorage.APPLICATION_VENDOR_FIELD_ID).asText());
            issue.setApplicationVersion(root.path("fields").path(ConstStorage.APPLICATION_VERSION_FIELD_ID).asText());
            issue.setCostCentre(root.path("fields").path(ConstStorage.COST_CENTRE_FIELD_ID).asText());
            issue.setSoftwareId(root.path("fields").path(ConstStorage.SOFTWARE_ID_FIELD_ID).asText());
            issue.setDescription(root.path("fields").path(ConstStorage.DESCRIPTION_FIELD_ID).asText());

            sub_node = root.path("fields").path(ConstStorage.PRIORITY_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("name").toString().replace("\"", "");
                issue.setPriority(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.PACKAGING_TECHNOLOGY_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("value").toString().replace("\"", "");
                issue.setPackagingTechnology(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.SITE_PKG_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("value").toString().replace("\"", "");
                issue.setSitePkg(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.SITE_QA_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("value").toString().replace("\"", "");
                issue.setSiteQa(name.equals("") ? "" : name);
            }

            sub_node = root.path("fields").path(ConstStorage.CATEGORY_FIELD_ID);
            if (!sub_node.isMissingNode()) {
                name = sub_node.path("value").toString().replace("\"", "");
                issue.setCategoryField(name.equals("") ? "" : name);
            }

            issuesList.add(issue);
        }
        return issuesList;
    }

    public static String getNodeFromJson(String jsonArray, String node) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootArray = mapper.readTree(jsonArray);
        if(rootArray.path(node).isMissingNode()){
            return null;
        } else {
            return rootArray.path(node).toString();
        }
    }

}
