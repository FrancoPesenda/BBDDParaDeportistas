package Main;

import Frame.GeneralFrame;
import Panels.PanelConsultData;

import java.awt.*;

public class ConsultDataPage {
    public ConsultDataPage(GeneralFrame generalFrame2){
        generalFrame = new GeneralFrame();
        PanelConsultData panelConsultData = new PanelConsultData(generalFrame , generalFrame2);
        generalFrame.setTitle("Base De Datos de Deportistas");
        generalFrame.add(panelConsultData);
        generalFrame.setSize(new Dimension(generalFrame.getWidth() + 1 , generalFrame.getHeight()));
    }

    public void setVisible(boolean b ){
        generalFrame.setVisible(b);
    }
    private GeneralFrame generalFrame ;
}
