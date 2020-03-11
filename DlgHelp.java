import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
public class DlgHelp extends JDialog{
	JTextArea t;
	String txt="";
	DlgHelp(Notepad frmNotepad){
		super(frmNotepad,"Help",true);
		t=new JTextArea();
		t.setText("For New File -> Ctr+n\nFor File Open -> Ctr+n\nFor File Save -> Ctr+n\nFor Print the File -> Ctr+p\nFor Undo -> Ctr+z\nFor Cut-> Ctr+x\nFor Copy -> Ctr+c\nFor Paste -> Ctr+v\nFor  -> Ctr+n\nFor Delete -> del\nFor Find -> Ctr+F\nFor Find Next -> f3\nFor Replace -> Ctr+H\nFor Go To -> Ctr+G\nFor Select All -> Ctr+A\nFor Date and Time-> F5");
		add(t);
		t.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,18));
		t.setForeground(Color.blue);
		t.setEditable(false);
		setSize(240,450);
		setTitle("Help");
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
