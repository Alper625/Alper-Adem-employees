package com.company;



import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static ArrayList<Employe> ReadCSV()
    {

        String path="D:\\CSV2.txt";
        String line;
        ArrayList<Employe> employes=new ArrayList<Employe>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while((line = br.readLine()) != null)
            {
                String[] values = line.split(", ");
                Employe employe=new Employe(Integer.parseInt(values[0]),Integer.parseInt(values[1]),values[2],values[3]);
                employes.add(employe);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return employes;
    }
public static Date BigDate(Date date1,Date date2)
{
    if(date1.compareTo(date2) > 0)
    {
        return date1;
    }
    return date2;

}
    public static Date SmalDate(Date date1,Date date2)
    {
        if(date1.compareTo(date2) > 0)
        {
            return date2;
        }
        return date1;

    }
    public static void main(String[] args) {
        ArrayList<Employe> values=ReadCSV();
        ProcessingData(values);
    }

    private static void ProcessingData(ArrayList<Employe> values) {
        long differenceInDays=0;
        ArrayList<TogetherWorkEmploye> togetherWorkEmployes = new ArrayList<TogetherWorkEmploye>();
        for(int i = 0 ; i < values.size();i++)
        {
            Employe employe1 = values.get(i);
            for(int j = i + 1 ; j < values.size();j++)
            {
                Employe employe2 = values.get(j);
                if(employe1.getProjectId() == employe2.getProjectId())
                {
                    if(employe1.getFrom().compareTo(employe2.getEnd()) < 0 &&
                            employe2.getFrom().compareTo(employe1.getEnd()) < 0)
                    {
                        Date bigDate = BigDate(employe1.getFrom(),employe2.getFrom());
                        Date smallDate = SmalDate(employe1.getEnd(),employe2.getEnd());
                        long date1InMs = bigDate.getTime();
                        long date2InMs = smallDate.getTime();
                        long diff;
                        if(date1InMs > date2InMs) {
                            diff = date1InMs - date2InMs;
                        } else {
                            diff = date2InMs - date1InMs;
                        }

                        int daysDiff = (int) (diff / (1000 * 60 * 60* 24));

                        TogetherWorkEmploye workEmploye=new TogetherWorkEmploye(employe1.getId(),employe2.getId(),
                                employe2.getProjectId(),
                                daysDiff);
                        togetherWorkEmployes.add(workEmploye);

                    }
                }
            }
        }

        Object[][] strings= new Object[togetherWorkEmployes.size()][4];
        long longPeriod = 0;
        int emp1=0,emp2=0;
        boolean flag=false;
        for(int i = 0 ; i < togetherWorkEmployes.size();i++)
        {
            strings[i][0]=togetherWorkEmployes.get(i).getEmploye1ID();
            strings[i][1]=togetherWorkEmployes.get(i).getEmploye2ID();
            strings[i][2]=togetherWorkEmployes.get(i).getProjectId();
            strings[i][3]=togetherWorkEmployes.get(i).getDays();
            if(togetherWorkEmployes.get(i).getDays() > longPeriod)
            {
                flag=true;
                longPeriod=togetherWorkEmployes.get(i).getDays();
                emp1=togetherWorkEmployes.get(i).getEmploye1ID();
                emp2=togetherWorkEmployes.get(i).getEmploye2ID();
            }
        }
        boolean mark=false;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for(int i = 0 ; i < togetherWorkEmployes.size();i++) {
            int projectId1=togetherWorkEmployes.get(i).getProjectId();
            long mostWorkDay=0;
            mark=false;
            int m=0;
            if(!numbers.contains(projectId1))
            {
                for(int j = 0; j < togetherWorkEmployes.size();j++)
                {
                    int projectId2=togetherWorkEmployes.get(j).getProjectId();
                    if(projectId1==projectId2)
                    {
                        if(togetherWorkEmployes.get(i).getDays() > mostWorkDay)
                        {
                            mark=true;
                            mostWorkDay=togetherWorkEmployes.get(i).getDays();
                            m=j;
                        }

                    }
                }
                numbers.add(projectId1);
                if(mark)
                {
                    System.out.println("In the project: " + projectId1 + " have worked the most: " +
                     togetherWorkEmployes.get(m).getEmploye1ID() + " " +togetherWorkEmployes.get(m).getEmploye2ID());

                }
            }
        }
        if(!flag)
        {
            System.out.println("There are no employees who have worked together");
        }
        else
        {
            System.out.println("The two employees who have worked for the longest period of time: " + emp1 + " , "+ emp2 +
                    " The days they worked together: " + longPeriod);
        }

        GUI gui=new GUI(strings);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setVisible(true);
        gui.setTitle("GUI");
        gui.setSize(600,200);


    }

}
