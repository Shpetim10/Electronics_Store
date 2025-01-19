package Model;

import java.util.*;

public interface CustomerLoyalty {
     default int validateCustomerExistance(String customerId) {
          //will return customer
          //If he does not exist will return -1

          if(Database.getDatabase().getCustomers().contains(customerId)){
               for (int i=0;i< Database.getDatabase().getCustomers().size();i++) {
                    if (customerId.equals(Database.getDatabase().getCustomers().get(i)))
                         return i;
               }
          }
          return -1;
     } //Sh
     default int getLoyaltyPoints(int index){
          return Database.getDatabase().getLoyaltyPoints().get(index);
     }

}
