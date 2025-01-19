package Control;


import Database.Database;
import Model.*;
import View.PerformanceReportView.AdminManagerAllBillView;


public class AdminManagerViewBillController{
    private User admin;
    private AdminManagerAllBillView view=new AdminManagerAllBillView();

    public AdminManagerViewBillController() {
        setSearchBoxAction();
    }

    public AdminManagerViewBillController(User admin){
        this.admin=admin;
        setSearchBoxAction();
    }
    public void setSearchBoxAction() {
        this.view.getSearch().getSearchButton().setOnAction(event -> {
            String cashierId = this.view.getSearch().getSearchField().getText();

            if (cashierId.isEmpty()) {
                showError("Search field cannot be empty.");
                return;
            }

            Cashier cashier = getCashierById(cashierId);

            if (cashier == null) {
                showError("No cashier found with ID: " + cashierId);
            } else {
                this.view.getBillView().setCashier(cashier);
                this.view.getDisplayPane().getChildren().add(this.view.getBillView().getView());
            }
        });
    }

    public Cashier getCashierById(String id) {
        for(Cashier cashier: Database.getDatabase().getCashiers()){
            if(cashier.getId()==Integer.parseInt(id)){
                return cashier;
            }
        }
        return null;
    }

    // Utility to display error messages (example implementation)
    private void showError(String message) {
        // Replace with your preferred error message handling (e.g., pop-up, label update)
        System.err.println("Error: " + message);
    }


    public User getAdmin() {
        return admin;
    }

    public AdminManagerAllBillView getView() {
        return view;
    }
}
