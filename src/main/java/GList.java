import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GList {
    private JButton backToMenuButton;
    private JTable table1;

    public JPanel getPanelList() {
        return jPanelList;
    }

    private JPanel jPanelList;
    private final JFrame jFrame;
    private final DefaultTableModel tableModel;
    private final User user;
    private final String[] columnNames ={
            "Nr"," ","wynik","czas","data","poziom"
    };
    private List<Result> results;

    public GList(JFrame jFrame,User user ) {
        this.user = user;
        this.results=user.getResults();
        this.jFrame=jFrame;
        this.tableModel=new DefaultTableModel(columnNames,0);
        initTable();
        this.table1.setModel(tableModel);
        backToMenuButton.addActionListener(e -> {
            jFrame.getContentPane().removeAll();
            FirstMenu firstMenu = new FirstMenu(this.jFrame,this.user);
        });
    }
    private void initTable(){

        for(int i=0;i<results.size();i++){
            String position =Integer.toString( i+1);
            String name = results.get(i).getName();
            String scoreStr = results.get(i).getScore() +"/"+results.get(i).getMax();
            String time = results.get(i).getTime() +" sec";
            String date = results.get(i).getDate();
            String level=results.get(i).getLevel().toString();
            Object[] data={position,name,scoreStr,time,date,level};
            this.tableModel.addRow(data);
        }

    }
}
