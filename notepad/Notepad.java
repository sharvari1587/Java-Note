
package notepad;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;


public class Notepad extends JFrame implements ActionListener {
    JTextArea area ;
    String text;
    
    Notepad()
    {
        setTitle("Notepad");
        
        //ICon Of The App
        ImageIcon notepadIcon = new ImageIcon(getClass().getResource("NotepadIcon.jpg"));
        Image icon = notepadIcon.getImage();
        setIconImage(icon);
        
        //Menu Bar Of App 
        JMenuBar menubar = new JMenuBar();   //created object of menu
        menubar.setBackground(Color.WHITE);  //setting bg color 
        
        //FILE MENU
        JMenu file = new JMenu("File");      //creating other object of menu i.e one option 
        JMenuItem newdoc = new JMenuItem("New");  //creating subobject of file option
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        file.add(newdoc);                 //make nest file->new 
        
        JMenuItem open = new JMenuItem("Open");
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        file.add(open);
        
        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        file.add(save);
        
        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        file.add(print);
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, ActionEvent.CTRL_MASK));
        exit.addActionListener(this);
        file.add(exit);
        menubar.add(file);                //making nest menubar->file
        
        //EDIT MENU
        JMenu edit = new JMenu("Edit");
        JMenuItem copy = new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        edit.add(copy);
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        edit.add(paste);
        
        JMenuItem cut = new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        edit.add(cut);
       
        JMenuItem selectall = new JMenuItem("SelectAll");
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);
        edit.add(selectall);
        menubar.add(edit);
        
        //HELP MENU
        //EDIT MENU
        JMenu help = new JMenu("HELP");
        JMenuItem helpi = new JMenuItem("help");
        helpi.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
        help.add(helpi);
        menubar.add(help);
       
        setJMenuBar(menubar);
        
        //TEXT AREA CODE 
        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        add(area);
        
        JScrollPane pane = new JScrollPane(area);
        add(pane);
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("New"))
        {
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open"))
        {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt file", "txt");
            chooser.addChoosableFileFilter(restrict);
            int action = chooser.showOpenDialog(this);
            if(action!= JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            
            File file = chooser.getSelectedFile();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Save"))
        {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            
            int action = saveas.showOpenDialog(this);
            
            if(action != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            File filename = new File(saveas.getSelectedFile()+".txt");
            BufferedWriter outFile = null;
            try{
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
                
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Print"))
        {
            try
            {
                area.print();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Exit"))
        {
            try
            {
                System.exit(0);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Copy"))
        {
                text = area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste"))
        {
                area.insert(text, area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut"))
        {
                text = area.getSelectedText();
                area.replaceRange("",area.getSelectionStart(), area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("SelectAll"))
        {
                area.selectAll();
        }
        
        
        
    }

    public static void main(String[] args) {
        new Notepad();
    }
    
}
