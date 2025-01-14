package Model;

import java.util.*;

public interface CustomerLoyalty {
     ArrayList<String> customers = new ArrayList<>();
     ArrayList<Integer> loyaltyPoints= new ArrayList<>(); //Code and money

     default int validateCustomerExistance(String customerId) {
          //will return customer
          //If he does not exist will return -1
          if(this.customers.contains(customerId)){
               for (int i=0;i<this.customers.size();i++) {
                    if (customerId.equals(this.customers.get(i)))
                         return i;
               }
          }
          return -1;
     } //Sh
     default int getLoyaltyPoints(int index){
          return this.loyaltyPoints.get(index);
     }

}
