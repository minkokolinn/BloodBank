package controller.admin;

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

public class AdminHomeController implements Initializable{
    @FXML
    private JFXHamburger slide_burger;

    @FXML
    private StackPane anchor;

    @FXML
    private JFXDrawer slide;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new setNode(anchor,"/view/admin/AdminView.fxml");
        try{
            VBox vb= FXMLLoader.load(getClass().getResource("/view/admin/Slider.fxml"));
            slide.setSidePane(vb);
            for(Node  node:vb.getChildren()){
                AnchorPane ap=(AnchorPane)node;
                for(Node node1:ap.getChildren()){
                    if(node1.getAccessibleText()!=null){
                        node1.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                            switch (node1.getAccessibleText()){
                                case "view":
                                    new setNode(anchor,"/view/admin/AdminView.fxml");
                                    break;
                                case "add":
                                    new setNode(anchor,"/view/admin/AdminAdd.fxml");
                                    break;
                                case "exit":
                                    new fxmlL((Stage)((Node)e.getSource()).getScene().getWindow(),"/view/HomePage.fxml");
                                    break;
                            }
                        });
                    }
                }
            }
            HamburgerBackArrowBasicTransition hbat=new HamburgerBackArrowBasicTransition(slide_burger);
            hbat.setRate(-1);
            slide_burger.addEventHandler(MouseEvent.MOUSE_CLICKED,e->{
                hbat.setRate(hbat.getRate()* -1 );
                hbat.play();
                if(slide.isClosed()&&anchor.getPrefWidth()==900){
                    slide.open();
                    anchor.setPrefWidth(700);
                }else {
                    slide.close();
                    anchor.setPrefWidth(900);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
