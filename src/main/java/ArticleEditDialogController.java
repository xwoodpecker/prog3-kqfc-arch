import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ArticleEditDialogController{
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField countField;


    private Stage dialogStage;
    private MArticle article;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the article to be edited in the dialog.
     *
     * @param article
     */
    public void setArticle(MArticle article) {
        this.article = article;

        descriptionField.setText(article.getDescription());
        countField.setText(Integer.toString(article.getCount()));
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            article.setDescription(descriptionField.getText());
            article.setCount(Integer.parseInt(countField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (descriptionField.getText() == null || descriptionField.getText().length() == 0) {
            errorMessage += "No valid description!\n";
        }

        if (countField.getText() == null || countField.getText().length() == 0) {
            errorMessage += "No valid count!\n";
        } else {
            // try to parse the postal code into an int.
            try {
                if(Integer.parseInt(countField.getText()) < 0)
                    errorMessage += "Count has to be a positive number!";
            } catch (NumberFormatException e) {
                errorMessage += "No valid count (must be an integer)!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}