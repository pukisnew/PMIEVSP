package i11.michalkevicius.deividas.controller.usermanagement;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import i11.michalkevicius.deividas.controller.Database;
import i11.michalkevicius.deividas.controller.SpreadsheetApp;
import i11.michalkevicius.deividas.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class UserManagementController implements Initializable
{
    private final ObservableList<User> data = FXCollections.observableArrayList();
    public TableView<User> table;
    public JFXTextField lastname;
    public JFXTextField name;
    public JFXTextField email;
    public JFXTextField phone;
    public JFXPasswordField password;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        List<User> users = Collections.emptyList();
        try
        {
            users = Database.getUsers();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        data.addAll(users);
        table.setEditable(false);
        table.setItems(data);
        List<TableColumn<User, ?>> columns = table.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory("id"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory("name"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory("lastname"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory("email"));
        columns.get(4).setCellValueFactory(new PropertyValueFactory("telephone"));
        columns.get(5).setCellValueFactory(new PropertyValueFactory("login"));
        ((TableColumn<User, Boolean>) columns.get(6)).setCellFactory((p) -> new ButtonCell(this::handle));
    }

    public void handle(ActionEvent event, int index)
    {

        try
        {
            User user = data.get(index);
            Database.removeUser(user.getId());
            data.remove(user);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Ištrinti nepavyko!").show();
        }
    }

    public void onCreateClick(ActionEvent e)
    {
        try
        {
            User user = new User();
            user.setLogin(email.getText());
            user.setEmail(email.getText());
            user.setTelephone(phone.getText());
            user.setName(name.getText());
            user.setLastname(lastname.getText());
            Database.createUser(user, password.getText());
            data.add(user);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }

    }

    public void onBackPress(ActionEvent actionEvent) throws IOException
    {
        SpreadsheetApp.launchAdminPanelStage();
    }

    interface OnButtonCellClick
    {
        void handle(ActionEvent e, int index);
    }

    private class ButtonCell extends TableCell<User, Boolean> implements EventHandler<ActionEvent>
    {
        private final Button cellButton = new Button("Ištrinti");
        private final OnButtonCellClick handler;

        ButtonCell(OnButtonCellClick handler)
        {
            this.handler = handler;
            cellButton.setOnAction(this);
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty)
        {
            super.updateItem(t, empty);
            if (empty)
            {
                setGraphic(null);
            }
            else
            {
                setGraphic(cellButton);
            }
        }

        @Override
        public void handle(ActionEvent event)
        {
            handler.handle(event, this.getIndex());
        }
    }
}
