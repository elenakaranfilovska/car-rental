package mk.ukim.finki.emt.lab.carrental.rentmanagement.port.ui;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.function.ValueProvider;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.RentManagement;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.UserCatalog;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.VehicleCatalog;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.form.RecipientAddressForm;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.application.form.RentForm;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.User;
import mk.ukim.finki.emt.lab.carrental.rentmanagement.domain.model.Vehicle;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.domain.geo.Country;
import mk.ukim.finki.emt.lab.carrental.sharedkernel.port.ui.StringToCityNameConverter;


@Route("create-rent")
@PageTitle("Create Order")
public class CreateRentView extends VerticalLayout {

    private final VehicleCatalog vehicleCatalog;
    private final UserCatalog userCatalog;
    private final RentManagement rentManagement;
    private final Binder<RentForm> binder;
    private ComboBox<Vehicle> vehicle;
    private ComboBox<User> user;
    private TextField itemPrice;
    // private final Grid<OrderItemForm> itemGrid;

    public CreateRentView(VehicleCatalog vehicleCatalog,UserCatalog userCatalog,RentManagement rentManagement) {
        this.userCatalog = userCatalog;
        this.vehicleCatalog = vehicleCatalog;
        this.rentManagement = rentManagement;
        setSizeFull();

        binder = new Binder<>();
        var title = new Html("<h3>Create Rent</h3>");
        add(title);

        setWidth("630px");

        vehicle = new ComboBox<>("Vehicle", vehicleCatalog.findAllAvailable());
        vehicle.setItemLabelGenerator(Vehicle::getModel);
        add(vehicle);

        itemPrice = new TextField("Price");
        itemPrice.setReadOnly(true);
        itemPrice.setWidth("100%");
        add(itemPrice);

        vehicle.addValueChangeListener(evt -> {
            var p = evt.getValue();
            if (p == null) {
                itemPrice.setValue("");
            } else {
                itemPrice.setValue(p.getPrice().toString());
            }
        });

        user = new ComboBox<>("User", userCatalog.findAllAvailable());
        user.setItemLabelGenerator(User::getFirstName);
        add(user);

        binder.setBean(new RentForm());
        binder.forField(vehicle)
                .asRequired()
                .bind(RentForm::getVehicle, RentForm::setVehicle);
        binder.forField(user).asRequired().bind(RentForm::getUser,RentForm::setUser);
        var billingAddress = new AddressLayout();
        billingAddress.bind(binder, RentForm::getBillingAddress);
        add(billingAddress);
        var createRent = new Button("Create Order",evt->createRent());
        createRent.setEnabled(false);
        createRent.getElement().getThemeList().set("primary", true);
        add(createRent);
        binder.addValueChangeListener(evt -> createRent.setEnabled(binder.isValid()));
    }
     private void createRent() {
           try {
                var rentId = rentManagement.createRent(binder.getBean());
                getUI().ifPresent(ui -> ui.navigate(RentDetailsView.class, rentId.getId()));
            } catch (Exception ex) {
                Notification.show(ex.getMessage());
            }
    }

    class AddressLayout extends VerticalLayout {

        private TextField name;
        private TextField address;
        private TextField addressLine2;
        private TextField postalCode;
        private TextField city;
        private ComboBox<Country> country;

        AddressLayout() {
            setPadding(false);
            setWidth("630px");

            name = createTextField("Name");
            address = createTextField("Address line 1");
            city = createTextField("City");
            country = new ComboBox<>("Country", Country.values());
            country.setWidth("100%");

            var line1 = new HorizontalLayout(name, address);
            line1.setWidth("100%");

            var line2 = new HorizontalLayout(city, country);
            line2.setWidth("100%");

            add(line1, line2);
        }

        private TextField createTextField(String caption) {
            var field = new TextField(caption);
            field.setWidth("100%");
            return field;
        }

        private void bind(Binder<RentForm> binder, ValueProvider<RentForm, RecipientAddressForm> parentProvider) {
            binder.forField(name)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getName), setter(parentProvider, RecipientAddressForm::setName));
            binder.forField(address)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getAddress), setter(parentProvider, RecipientAddressForm::setAddress));

            binder.forField(city)
                    .asRequired()
                    .withConverter(new StringToCityNameConverter())
                    .bind(getter(parentProvider, RecipientAddressForm::getCity), setter(parentProvider, RecipientAddressForm::setCity));
            binder.forField(country)
                    .asRequired()
                    .bind(getter(parentProvider, RecipientAddressForm::getCountry), setter(parentProvider, RecipientAddressForm::setCountry));
        }

        private <V> ValueProvider<RentForm, V> getter(ValueProvider<RentForm, RecipientAddressForm> parentProvider, ValueProvider<RecipientAddressForm, V> valueProvider) {
            return orderForm -> valueProvider.apply(parentProvider.apply(orderForm));
        }

        private <V> Setter<RentForm, V> setter(ValueProvider<RentForm, RecipientAddressForm> parentProvider, Setter<RecipientAddressForm, V> setter) {
            return (orderForm, value) -> setter.accept(parentProvider.apply(orderForm), value);
        }
    }
}

