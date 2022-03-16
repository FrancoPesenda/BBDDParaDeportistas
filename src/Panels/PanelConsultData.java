package Panels;

import Frame.GeneralFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Locale;

public class PanelConsultData extends JPanel {
    public PanelConsultData( GeneralFrame myFrame , GeneralFrame mainFrame ){

        this.myFrame = myFrame ;
        this.mainFrame = mainFrame ;
        nasionality = new JComboBox<>();
        sport = new JComboBox<>();
        jTextArea = new JTextArea();
        jTextArea.append("  ID   NOMBRE   APELLIDO   PAIS   DEPORTE   EDAD");
        serch = new JButton("Buscar");
        clean = new JButton("Limpiar lista");

        serch.addActionListener(new ActionListenerSerchData());
        clean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jTextArea.setText("");
                jTextArea.append("  ID   NOMBRE   APELLIDO   PAIS   DEPORTE   EDAD");
            }
        });
        jTextArea.setEditable(false);
        String[] options1 = { "Sin Filtro" , "Argentina" , "Brazil" , "Peru" , "Uruguay" , "Mexico" , "EstadosUnidos" , "Colombia"} ;
        addOptions(nasionality , options1 );
        String[] options2 = { "Sin Filtro" ,"Futbol" , "Basket" , "Voley" , "Esport" } ;
        addOptions(sport , options2);
        setLayout(new BorderLayout());

        Box topBox = Box.createHorizontalBox();
        topBox.add(nasionality);
        topBox.add(sport);
        Box buttonsOptions = Box.createHorizontalBox() ;
        buttonsOptions.add(clean);
        buttonsOptions.add(Box.createRigidArea(new Dimension(285,20)));
        buttonsOptions.add(serch);
        Box box = Box.createVerticalBox();
        box.add(topBox);
        box.add(buttonsOptions);

        add(box , BorderLayout.NORTH);
        add(jTextArea , BorderLayout.CENTER);
    }

    public void addOptions( JComboBox<String> jComboBox , String[] options ){

        for (int i = 0; i < options.length; i++) {
            jComboBox.addItem(options[i]);
        }
    }

    private class ActionListenerSerchData implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {

            String sportSelection = (String) sport.getSelectedItem() ;
            sportSelection = sportSelection.toLowerCase();
            String nasionalitySelection = (String) nasionality.getSelectedItem() ;
            nasionalitySelection = nasionalitySelection.toLowerCase() ;

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deportist","root","Matecocido11");

                Statement statement = connection.createStatement();

                PreparedStatement sportFilter = connection.prepareStatement("SELECT * FROM deportists WHERE Sport = ?");

                PreparedStatement nasionalityFilter = connection.prepareStatement("SELECT * FROM deportists WHERE Nasionality = ?");

                PreparedStatement sportAndNasionalityFilter = connection.prepareStatement("SELECT * FROM deportists WHERE Nasionality = ? and Sport = ?") ;

                ResultSet resultSet = null ;
                if (sportSelection.equals("sin filtro") && nasionalitySelection.equals("sin filtro")){
                    resultSet = statement.executeQuery("SELECT * FROM deportists") ;
                }else if (sportSelection.equals("sin filtro") && !nasionalitySelection.equals("sin filtro")){
                    nasionalityFilter.setString(1,nasionalitySelection);
                    resultSet = nasionalityFilter.executeQuery();
                }else if (!sportSelection.equals("sin filtro") && nasionalitySelection.equals("sin filtro")){
                    sportFilter.setString(1,sportSelection);
                    resultSet = sportFilter.executeQuery();
                }else if (!sportSelection.equals("sin filtro") && !nasionalitySelection.equals("sin filtro")){
                    sportAndNasionalityFilter.setString(1,nasionalitySelection);
                    sportAndNasionalityFilter.setString(2,sportSelection);
                    resultSet = sportAndNasionalityFilter.executeQuery();
                }

                while (resultSet.next()){
                    jTextArea.append("\n  " + resultSet.getString(1) +"   "+ resultSet.getString(2) +"   "+resultSet.getString(3) +"   "+resultSet.getString(4) +"   "+resultSet.getString(5) +"   "+resultSet.getString(6));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private GeneralFrame myFrame ;
    private GeneralFrame mainFrame ;
    private JTextArea jTextArea ;
    private JComboBox<String> nasionality ;
    private JComboBox<String> sport ;
    private JButton serch ;
    private JButton clean ;

}
