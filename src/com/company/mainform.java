/*
 * Created by JFormDesigner on Sat Aug 08 09:01:20 PDT 2020
 */

package com.company;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.Result;

import net.miginfocom.swing.*;

/**
 * @author Prem
 */
public class mainform extends JFrame {
    Connection con1;
    PreparedStatement insert;
    DefaultTableModel model, model1;
    public mainform() {
        this.model1 = new DefaultTableModel();
        this.model = new DefaultTableModel();
        model1.addColumn("Number");
        model1.addColumn("Name");
        model1.addColumn("Deposit");
        model1.addColumn("Years");
        model1.addColumn("Type of Savings");

        model.addColumn("Year");
        model.addColumn("Starting");
        model.addColumn("Interest");
        model.addColumn("Ending Value");
        initComponents();
    }

    private void button1ActionPerformed(ActionEvent e) throws ClassNotFoundException, SQLException {
        if(customer_number.getText().isEmpty()||customer_name.getText().isEmpty()||initial_deposit.getText().isEmpty()|| number_of_years.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please fill all the required fields");
        }
        System.out.println("button clicked");
        String customerNumber = customer_number.getText().toString();
        String customerName = customer_name.getText().toString();

        String typeOfSavings = comboBox1.getSelectedItem().toString();
        double initialDeposit = Double.parseDouble(initial_deposit.getText().toString());
        int numberOfYears = Integer.parseInt(number_of_years.getText().toString());

        Savings savings = new Savings(customerNumber, customerName, typeOfSavings, initialDeposit, numberOfYears);

        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/savings", "root", "");
        Statement stmt = con1.createStatement();
        stmt.executeUpdate("insert into savingstable(custno,custname,cdep,nyears,savtype ) " +
                "values('" + savings.customerNumber + "','" + savings.customerName + "','" +
                savings.initialDeposit + "','" + savings.numberOfYears + "','"+savings.typeOfSavings+"')");

        JOptionPane.showMessageDialog(null,"Record Added");

        table1.setModel(model1);

        ResultSet rs = stmt.executeQuery("SELECT * FROM savingstable");

        while (rs.next()) {
            String customernumber = rs.getString("custno");
            String customername = rs.getString("custname");
            Double deposit = rs.getDouble("cdep");
            Integer numberofyears = rs.getInt("nyears");
            String savingtype = rs.getString("savtype");
            Object[] row = {customernumber, customername, deposit.toString(), numberofyears.toString(), savingtype};

            System.out.println(customernumber + customername +deposit.toString() + numberofyears.toString() + savingtype);

            model1.addRow(row);

        }


    }



    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Prem
        label2 = new JLabel();
        customer_number = new JTextField();
        label1 = new JLabel();
        customer_name = new JTextField();
        label3 = new JLabel();
        initial_deposit = new JTextField();
        label4 = new JLabel();
        number_of_years = new JTextField();
        label5 = new JLabel();
        comboBox1 = new JComboBox();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label2 ----
        label2.setText("Enter The Customer Number");
        contentPane.add(label2, "cell 0 1");
        contentPane.add(customer_number, "cell 5 1 9 1");

        //---- label1 ----
        label1.setText("Enter The Customer Name");
        contentPane.add(label1, "cell 0 2");
        contentPane.add(customer_name, "cell 5 2 9 1");

        //---- label3 ----
        label3.setText("Enter the inital deposit");
        contentPane.add(label3, "cell 0 3");
        contentPane.add(initial_deposit, "cell 5 3 9 1");

        //---- label4 ----
        label4.setText("Enter the number of years");
        contentPane.add(label4, "cell 0 4");
        contentPane.add(number_of_years, "cell 5 4 9 1");

        //---- label5 ----
        label5.setText("Choose The type of savings");
        contentPane.add(label5, "cell 0 6");
        contentPane.add(comboBox1, "cell 5 6 9 1");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 8");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 7 8");

        //---- button1 ----
        button1.setText("Add");
        button1.addActionListener(e -> {
            try {
                button1ActionPerformed(e);
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });


        contentPane.add(button1, "cell 0 9");

        //---- button2 ----
        button2.setText("Edit");
        contentPane.add(button2, "cell 0 9");

        //---- button3 ----
        button3.setText("Delete");
        contentPane.add(button3, "cell 0 9");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents

        comboBox1.addItem("Savings Deluxe");
        comboBox1.addItem("Savings Regular");

        table2.setModel(model);
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                String savingsType = table1.getValueAt(table1.getSelectedRow(), 4).toString();

                if (savingsType.equals("Savings Deluxe")) {
                    int years = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 3).toString());

//                    for (int i = 1; i <= years; i++) {
//                        Deluxe deluxe = new Deluxe("", "", "", );
//                    }
                }

            }
        });

    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Prem
    private JLabel label2;
    private JTextField customer_number;
    private JLabel label1;
    private JTextField customer_name;
    private JLabel label3;
    private JTextField initial_deposit;
    private JLabel label4;
    private JTextField number_of_years;
    private JLabel label5;
    private JComboBox comboBox1;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
