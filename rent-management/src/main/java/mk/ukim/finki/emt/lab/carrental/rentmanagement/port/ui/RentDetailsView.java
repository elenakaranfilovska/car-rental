package mk.ukim.finki.emt.lab.carrental.rentmanagement.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.RentManagement;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Rent;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.RentId;

import java.util.Optional;

@Route("order")
@PageTitle("Show Order")
public class RentDetailsView extends VerticalLayout implements HasUrlParameter<String> {

    private final RentManagement rentManagement;

    public RentDetailsView(RentManagement rentManagement) {
        this.rentManagement = rentManagement;
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Optional<Rent> rent= Optional.ofNullable(parameter).map(RentId::new).flatMap(rentManagement::findById);
        if (rent.isPresent()) {
            showOrder(rent.get());
        } else {
            showNoSuchOrder();
        }
    }

    private void showOrder(Rent rent) {

        var title = new Html("<h3>Rent Details</h3>");
        add(title);
        var rentedOn = new TextField("Rented on");
        rentedOn.setReadOnly(true);
        rentedOn.setWidth("100%");
        rentedOn.setValue(rent.getRentedOn().toString());
        add(rentedOn);
        var state=new TextField("State");
        state.setReadOnly(true);
        state.setWidth("100%");
        state.setValue(rent.state().name());
        add(state);

    }

    private TextField createReadOnlyTextField(String value) {
        var textField = new TextField();
        textField.setReadOnly(true);
        textField.setValue(value);
        return textField;
    }

    private void showNoSuchOrder() {
        add(new Text("The order does not exist."));
    }
}
