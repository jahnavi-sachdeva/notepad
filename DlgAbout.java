import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class DlgAbout extends JDialog implements ActionListener{
	ImageIcon ii;
	JLabel l1,l2,l3,l4;
	Font f;
	JButton b1;
	DlgAbout(Notepad frmNotepad){
		super(frmNotepad,"About",true);
		setLayout(null);
		ii=new ImageIcon("images/winlogo3.PNG");
		l1=new JLabel(ii,SwingConstants.CENTER);
		f=new Font(Font.SERIF,Font.BOLD,33);
		l2=new JLabel("______________________________________________________________");
		l3 = new JLabel("<html>Notepad is a word processing program"
		 		+ "<br>which allows changing of text in a computer file."
		 		+ "<br>Notepad was created by the Microsoft corporation"
		 		+ "<br>It is a very simple word processor."
		 		+ "<br>It has been a part of Microsoft Windows since 1985"
		 		+ "<br>The program has options such as changing the font"
		 		+ "<br>the font size, and the font style."
		 		+ "<br>The most common use for Notepad is to view"
		 		+ "<br>or change (edit) text (.txt) files, though .dat"
		 		+ "<br>and .ini files can be changed in Notpad as well."
		 		+ "<br>Many users find Notepad a simple program for creating"
		 		+ "<br>webpages."
		 		+ "</html>");
		l3.setFont(new Font(Font.SANS_SERIF,Font.ITALIC,15));
		l4=new JLabel("Created By _____________");
		l4.setFont(f);
		l4.setForeground(Color.RED);
		b1=new JButton("Ok");
		b1.addActionListener(this);
		 
		l1.setBounds(0, 0, 500, 100);
		l2.setBounds(20,60,500,50);
		l3.setBounds(60,80,500,300);
		l4.setBounds(20,340,500,50);
		b1.setBounds(200,400,50,40);
		
		add(l1);
		add(l2);
		add(l3);
		add(l4);
		add(b1);
		setSize(500,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)  {
		dispose();
	}
}
