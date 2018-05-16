package com.apptimized.tools.randomizer.Randomizer.UIInterface.Controllers;

import com.apptimized.tools.randomizer.JiraEntities.Issue;
import com.apptimized.tools.randomizer.RestClient.RestActions;
import com.apptimized.tools.randomizer.Utils.ExcelWorker;
import com.apptimized.tools.randomizer.Utils.JsonActions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import static com.apptimized.tools.randomizer.Randomizer.UIInterface.Actions.UIActions.showExceptionAlert;
import static com.apptimized.tools.randomizer.Randomizer.UIInterface.Actions.UIActions.showMessage;
import static com.apptimized.tools.randomizer.Utils.JsonActions.parseIssues;

public class MainController implements Initializable {
    private ObservableList<Issue> issuesData = FXCollections.observableArrayList();
    private ArrayList<Issue> issuesList = new ArrayList<>();

    @FXML
    private TextField jqlTextInput;

    @FXML
    private Button loadQueryResultButton;

    @FXML
    private Button exportToXlsx;

    @FXML
    private TableView<Issue> issuesTable;

    @FXML
    private TableColumn<Issue, String> keyColumn;

    @FXML
    private TableColumn<Issue, String> summaryColumn;

    @FXML
    private TableColumn<Issue, String> assigneeColumn;

    @FXML
    private TableColumn<Issue, String> packagerColumn;

    @FXML
    private TableColumn<Issue, String> qaColumn;

    @FXML
    private void initialize(ArrayList<Issue> issuesList) {
        initData(issuesList);

        keyColumn.setCellValueFactory(new PropertyValueFactory<Issue, String>("key"));
        summaryColumn.setCellValueFactory(new PropertyValueFactory<Issue, String>("summary"));
        assigneeColumn.setCellValueFactory(new PropertyValueFactory<Issue, String>("assignee"));
        packagerColumn.setCellValueFactory(new PropertyValueFactory<Issue, String>("packager"));
        qaColumn.setCellValueFactory(new PropertyValueFactory<Issue, String>("qaEngineer"));
        issuesTable.setItems(issuesData);
    }

    private void initData(ArrayList<Issue> issuesList) {
        issuesData.clear();
        issuesData.addAll(issuesList);
    }

    @FXML
    protected void getQueryResultsInTable(ActionEvent event) {
        new Thread(() -> {
            getQueryResultsInTableHandler(jqlTextInput.getText(), false);
        }).start();
    }

    private void getQueryResultsInTableHandler(String query, boolean shuffleButtonStatus) {
        try {
            loadQueryResultButton.setDisable(true);
            exportToXlsx.setDisable(true);

            if(query.equals("")) {
                showMessage(Alert.AlertType.INFORMATION, "Information",
                        "Oooops...",
                        "Query is empty. Please write query for search");
                return;
            }

            String jsonArray = RestActions.getQueryResults(query);

            int maxResults = Integer.parseInt(JsonActions.getNodeFromJson(jsonArray, "maxResults"));
            int total = Integer.parseInt(JsonActions.getNodeFromJson(jsonArray, "total"));
            int startAt = 0;

            ArrayList<String> jsonArrays = new ArrayList<>();

            while(startAt < total) {
                jsonArrays.add(RestActions.getQueryResults(query, startAt));
                startAt += maxResults;
            }

            if (JsonActions.getNodeFromJson(jsonArray, "warningMessages") != null) {
                showMessage(Alert.AlertType.WARNING, "Attention!",
                        "Oooops... Please, fix your query (JQL)",
                        JsonActions.getNodeFromJson(jsonArray, "warningMessages"));
                return;
            }
            if (JsonActions.getNodeFromJson(jsonArray, "errorMessages") != null) {
                showMessage(Alert.AlertType.WARNING, "Attention!",
                        "Oooops... Please, fix your query (JQL)",
                        JsonActions.getNodeFromJson(jsonArray, "errorMessages"));
                return;
            }
            if (jsonArray.contains("\"issues\":[]")) {
                showMessage(Alert.AlertType.INFORMATION, "Information",
                        "Oooops...",
                        "Query hasn't returned any results");
                return;
            }

            for (String s: jsonArrays) {
                issuesList.addAll(parseIssues(s));
            }

            initialize(issuesList);
        } catch(IOException ex) {
            showExceptionAlert(ex);
        } finally {
            loadQueryResultButton.setDisable(false);
            exportToXlsx.setDisable(false);
        }
    }

    @FXML
    protected void writeResultsToExcel() {
        new Thread(() -> {
            try {
                loadQueryResultButton.setDisable(true);
                exportToXlsx.setDisable(true);

                ExcelWorker workWithExcel = new ExcelWorker();
                DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss");
                Date date = new Date();

                workWithExcel.writeXLSXFileWithIssues("JiraReport_" + dateFormat.format(date), dateFormat.format(date), issuesList);
            } catch (IOException e) {
                showMessage(Alert.AlertType.ERROR, "Error",
                        "Oooops...",
                        e.getMessage());
            }
            finally {
                loadQueryResultButton.setDisable(false);
                exportToXlsx.setDisable(false);
            }
        }).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

    }

}
