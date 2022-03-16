package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Frame.GeneralFrame;
import Main.ConsultDataPage;
import Main.InDataPage;

public class PanelPrincipal extends JPanel {

    public PanelPrincipal( GeneralFrame generalFrame ){
        this.generalFrame = generalFrame ;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(200, 100)));
        addButton("INGRESAR DATOS",new ActionListenerInData());
        addButton("CONSULTAR DATOS",new ActionListenerConsultData());
        addButton("SALIR",new ActionListenerExit());
        setVisible(true);

    }

    public void addButton( String s , ActionListener actionListener){
        JButton jButton = new JButton(s);
        jButton.setAlignmentX((float)0.5);
        jButton.setMaximumSize(new Dimension(200, 50));
        jButton.addActionListener(actionListener);
        add(jButton);
        add(Box.createRigidArea(new Dimension(200, 10)));
    }

    private class ActionListenerExit implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            System.exit(0);
        }
    }

    private class ActionListenerConsultData implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (consultDataPage == null){
                consultDataPage = new ConsultDataPage(generalFrame);
            }else {
                consultDataPage.setVisible(true);
            }

            generalFrame.setVisible(false);
        }
    }

    private class ActionListenerInData implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (inDataPage == null){
                inDataPage = new InDataPage(generalFrame);
            }else {
                inDataPage.setVisible(true);
            }

            generalFrame.setVisible(false);

        }
    }

    private GeneralFrame generalFrame ;
    private InDataPage inDataPage ;
    private ConsultDataPage consultDataPage;

}
