package Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import Frame.GeneralFrame ;

public class PanelDataIn extends JPanel {

    public PanelDataIn( GeneralFrame generalFrame , GeneralFrame generalFrame2){
        this.generalFrame = generalFrame ;
        this.generalFrame2 = generalFrame2 ;
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(Box.createRigidArea(new Dimension(200, 100)));
        addDataField("Nombre:           ");
        addDataField("Apellido:           ");
        addDataField("Nacionalidad: ");
        addDataField("Deporte:          ");
        addDataField("Edad:              ");
        add(Box.createRigidArea(new Dimension(200, 100)));
        JButton exit = new JButton("EXIT");
        JButton sendData = new JButton("INSCRIBIR");
        exit.setAlignmentX(Component.LEFT_ALIGNMENT);
        sendData.setAlignmentX(Component.RIGHT_ALIGNMENT);
        exit.setMaximumSize(new Dimension(200,100));
        sendData.setMaximumSize(new Dimension(200,100));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                generalFrame.setVisible(false);
                generalFrame2.setVisible(true);
            }
        });
        sendData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name , lastName , nasionality , sport ,age ;

                name = obtainValue(1);
                System.out.println(name);
                lastName = obtainValue(3);
                System.out.println(lastName);
                nasionality = obtainValue(5);
                System.out.println(nasionality);
                sport = obtainValue(7);
                System.out.println(sport);
                age = obtainValue(9);
                System.out.println(age);

                try {
                    //INSERT INTO deportists (Name,Lastname,Nasionality,Sport,Age) value ('Franco','Pesenda','Argentino','Programador',27)
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deportist","root","Matecocido11");

                    Statement statement = connection.createStatement();

                    statement.executeUpdate("INSERT INTO deportists (Name,Lastname,Nasionality,Sport,Age) value ('"+name+"','"+lastName+"','"+nasionality+"','"+sport+"','"+age+"')");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        Box buttonsControl = Box.createHorizontalBox();
        buttonsControl.add(sendData);
        buttonsControl.add(exit);
        add(buttonsControl);

        setVisible(true);

    }

    public void addDataField(String s ){
        Box box = Box.createHorizontalBox();
        JLabel jLabel = new JLabel(s);
        jLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JTextField jTextField = new JTextField();
        jTextField.setMaximumSize(new Dimension(200,50));
        box.add(jLabel);
        box.add(jTextField);
        add(box);
        add(Box.createRigidArea(new Dimension(200, 10)));
    }

    public String obtainValue(int i ){
        Box b = (Box) getComponent(i);
        JTextField j = (JTextField) b.getComponent(1);
        return j.getText();
    }

    private GeneralFrame generalFrame ;
    private GeneralFrame generalFrame2 ;

}
