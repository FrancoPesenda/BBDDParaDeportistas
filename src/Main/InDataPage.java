package Main;

import Frame.GeneralFrame;
import Panels.PanelDataIn;
import Panels.PanelPrincipal;

import java.awt.*;

public class InDataPage {
    public InDataPage(GeneralFrame generalFrame2){
        generalFrame = new GeneralFrame();
        PanelDataIn panelDataIn = new PanelDataIn(generalFrame , generalFrame2);

        generalFrame.setTitle("Base De Datos de Deportistas");
        generalFrame.add(panelDataIn);
        generalFrame.setSize(new Dimension(generalFrame.getWidth() + 1 , generalFrame.getHeight()));
    }

    public void setVisible(boolean b ){
        generalFrame.setVisible(b);
    }
    private GeneralFrame generalFrame ;
}
