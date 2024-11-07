package controller.donation;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.fxmlL;
import utility.setNode;

import java.net.URL;
import java.util.ResourceBundle;

public class DonationHomePageController implements Initializable{
    @FXML
    private JFXHamburger slide_burger_donation;

    @FXML
    private StackPane anchorDonation;

    @FXML
    private JFXDrawer SlideDonation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new setNode(anchorDonation,"/view/donation/DonationHome.fxml");
        try{
            VBox vb= FXMLLoader.load(getClass().getResource("/view/donation/DonationSlider.fxml"));
            SlideDonation.setSidePane(vb);
            for(Node node:vb.getChildren()){
                AnchorPane ap=(AnchorPane)node;
                for (Node node1:ap.getChildren()){
                    if(node1.getAccessibleText()!=null){
                        node1.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                            switch (node1.getAccessibleText()){
                                case "home":
                                    new setNode(anchorDonation,"/view/donation/DonationHome.fxml");
                                    break;
                                 case "view":
                                    new setNode(anchorDonation, "/view/donation/ViewListDonation.fxml");
                                    break;
                                 case "add_donator":
                                    new setNode(anchorDonation,"/view/donation/AddDonater.fxml");
                                    break;
                                 case "donate":
                                    new setNode(anchorDonation,"/view/donation/Donate.fxml");
                                    break;
                                 case "exit_donate":
                                     new fxmlL((Stage)((Node)e.getSource()).getScene().getWindow(),"/view/HomePage.fxml");
                                    break;

                            }
                        });
                    }
                }
            }
            HamburgerBackArrowBasicTransition hbat2=new HamburgerBackArrowBasicTransition(slide_burger_donation);
            hbat2.setRate(-1);
            slide_burger_donation.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                hbat2.setRate(hbat2.getRate()* -1 );
                hbat2.play();
                if(SlideDonation.isClosed()&&anchorDonation.getPrefWidth()==900){
                    SlideDonation.open();
                    anchorDonation.setPrefWidth(700);
                }else {
                    SlideDonation.close();
                    anchorDonation.setPrefWidth(900);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
