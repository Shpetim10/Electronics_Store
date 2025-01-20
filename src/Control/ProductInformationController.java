package Control;

import View.ProductInformationView;
import javafx.scene.Node;

public class ProductInformationController {
    private ProductInformationView view=new ProductInformationView();

    public ProductInformationController(){
        setUpPaneSwitcher();
    }

    public void setUpPaneSwitcher(){
        view.getSearchBox().getSearchField().setOnMouseClicked(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getInventoryTable().getTable());
                }
        );
        view.getSearchBox().getSearchButton().setOnAction(
                e->{
                    this.view.getDisplayPane().getChildren().clear();
                    this.view.getDisplayPane().getChildren().add(this.view.getInfoPane());

                }
        );
    }

    public ProductInformationView getView() {
        return view;
    }
}
