import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

    public class ArticleOverviewController {

        @FXML
        private TableView<MArticle> articleTable;
        @FXML
        private TableColumn<MArticle, String> descriptionColumn;
        @FXML
        private TableColumn<MArticle, Integer> countColumn;

        @FXML
        private Label descriptionLabel;
        @FXML
        private Label countLabel;

        ArticleManager am;

        // Reference to the main application.
        private App app;

        /**
         * The constructor.
         * The constructor is called before the initialize() method.
         */
        public ArticleOverviewController() {
        }

        /**
         * Initializes the controller class. This method is automatically called
         * after the fxml file has been loaded.
         */
        @FXML
        private void initialize() {
            am = new ArticleManager();
            articleTable.setItems(am.getArticleModels());

            // Initialize the person table with the two columns.
            descriptionColumn.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
            countColumn.setCellValueFactory(cellData -> cellData.getValue().countProperty().asObject());

            //clear the article details
            showArticleDetails(null);

            //listen for selection changes and show the article details when changed
            articleTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showArticleDetails(newValue));
        }

        /**
         * Is called by the main application to give a reference back to itself.
         *
         * @param app
         */
        public void setMainApp(App app) {
            this.app = app;
        }

        /**
                * Fills all text fields to show details about the article.
                * If the specified article is null, all text fields are cleared.
 *
         * @param article the article or null
                */
        private void showArticleDetails(MArticle article) {
            if (article != null) {
                // Fill the labels with info from the article object.
                descriptionLabel.setText(article.getDescription());
                countLabel.setText(Integer.toString(article.getCount()));
            } else {
                // Article is null, remove all the text.
                descriptionLabel.setText("");
                countLabel.setText("");
            }
        }

        /**
         * Called when the user clicks on the delete button.
         */
        @FXML
        private void handleDeleteArticle() {
            int selectedIndex = articleTable.getSelectionModel().getSelectedIndex();
            if(selectedIndex >= 0) {
                am.deleteModel(articleTable.getItems().get(selectedIndex));
                articleTable.getItems().remove(selectedIndex);
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(app.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Article Selected");
                alert.setContentText("Please select an article in the table.");
                alert.showAndWait();
            }
        }
        /**
         * Called when the user clicks the new button. Opens a dialog to edit
         * details for a new person.
         */
        @FXML
        private void handleNewArticle() {
            MArticle tempArticle = new MArticle();
            boolean okClicked = app.showArticleEditDialog(tempArticle);
            if (okClicked) {
                am.addModel(tempArticle);
                articleTable.getItems().add(tempArticle);
            }
        }

        /**
         * Called when the user clicks the edit button. Opens a dialog to edit
         * details for the selected article.
         */
        @FXML
        private void handleEditArticle() {
            MArticle selectedArticle = articleTable.getSelectionModel().getSelectedItem();
            if (selectedArticle != null) {
                boolean okClicked = app.showArticleEditDialog(selectedArticle);
                if (okClicked) {
                    showArticleDetails(selectedArticle);
                    am.updateModel(selectedArticle);
                }

            } else {
                // Nothing selected. TODO ALERTS in library, usability!!!!!!
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(app.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Article Selected");
                alert.setContentText("Please select am article in the table.");

                alert.showAndWait();
            }
        }
}
