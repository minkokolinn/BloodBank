package controller.transfer;

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

public class TransferHomeController implements Initializable{
    @FXML
    private JFXHamburger slide_burger_transfer;

    @FXML
    private StackPane anchorTransfer;

    @FXML
    private JFXDrawer SlideTransfer;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new setNode(anchorTransfer,"/view/transfer/TransferRegister.fxml");
        try {
            VBox vb= FXMLLoader.load(getClass().getResource("/view/transfer/TransferSlider.fxml"));
            SlideTransfer.setSidePane(vb);
            for (Node node:vb.getChildren()){
                AnchorPane ap=(AnchorPane)node;
                for(Node node1:ap.getChildren()){
                    if(node1.getAccessibleText()!=null){
                        node1.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                            switch (node1.getAccessibleText()){
                                case "register_transfer":
                                    new setNode(anchorTransfer,"/view/transfer/TransferRegister.fxml");
                                    break;
                                case "hospital_transfer":
                                    new setNode(anchorTransfer,"/view/transfer/TransferHospital.fxml");
                                    break;
                                case "patient_transfer":
                                    new setNode(anchorTransfer,"/view/transfer/TransferPatient.fxml");
                                    break;
                                case "exit_transfer":
                                    new fxmlL((Stage)((Node)e.getSource()).getScene().getWindow(),"/view/HomePage.fxml");
                                    break;
                            }
                        });
                    }
                }
            }
            HamburgerBackArrowBasicTransition hbat3=new HamburgerBackArrowBasicTransition(slide_burger_transfer);
            hbat3.setRate(-1);
            slide_burger_transfer.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                hbat3.setRate(hbat3.getRate()*-1);
                hbat3.play();
                if(SlideTransfer.isClosed()&&anchorTransfer.getPrefWidth()==900){
                    SlideTransfer.open();
                    anchorTransfer.setPrefWidth(700);
                }else {
                    SlideTransfer.close();
                    anchorTransfer.setPrefWidth(900);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
