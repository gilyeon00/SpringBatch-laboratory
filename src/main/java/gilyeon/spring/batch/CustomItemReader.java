package gilyeon.spring.batch;

import gilyeon.spring.batch.entity.Customer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;


public class CustomItemReader implements ItemReader<Customer> {

    private List<Customer> customerList;
    public CustomItemReader(List<Customer> list){
        this.customerList = new ArrayList<>(list);
    }

    @Override
    public Customer read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!customerList.isEmpty()){
            return customerList.remove(0);
        }
        return null;
    }
}
