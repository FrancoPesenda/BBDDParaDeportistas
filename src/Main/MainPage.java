package Main;

import Frame.GeneralFrame;
import Panels.PanelPrincipal;

import java.awt.*;

public class MainPage {

    public MainPage(){

        generalFrame = new GeneralFrame();
        PanelPrincipal panelPrincipal = new PanelPrincipal(generalFrame);

        generalFrame.setTitle("Base De Datos de Deportistas");
        generalFrame.add(panelPrincipal);
        generalFrame.setSize(new Dimension(generalFrame.getWidth() + 1 , generalFrame.getHeight()));

    }

    private GeneralFrame generalFrame ;

}
