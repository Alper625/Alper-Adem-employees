package com.company;

import com.company.TogetherWorkEmploye;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class GUI extends JFrame {
    public void addelem(TogetherWorkEmploye employe)
    {

    }
    public GUI(Object[][] data)
    {

        String[] columnNames = {"Employee ID #1",
                "Employee ID #2 ",
                "Project ID",
                " Days worked",
        };


        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500,50));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);


    }
}
