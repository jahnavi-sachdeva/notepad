import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
class DlgReplace extends JDialog{
	JLabel lblFindWhat,lblReplaceWith;
	JTextField txtFindWhat,txtReplaceWith;
	JCheckBox chkMatchCase;
	JRadioButton rdoUp,rdoDown;
	ButtonGroup bg;
	JPanel panRadio;
	JButton btnFindNext,btnReplace,btnReplaceAll,btnCancel;
	boolean first=false;
	DlgReplace(Notepad frmNotepad){
		super(frmNotepad,"Replace",false);//true for Modal false for Modeless
		setLayout(null);
		lblFindWhat=new JLabel("Find What");
		lblFindWhat.setBounds(5,10,100,50);
		add(lblFindWhat);
		lblReplaceWith=new JLabel("Replace With");
		lblReplaceWith.setBounds(5,70,100,50);
		add(lblReplaceWith);
		txtFindWhat=new JTextField(20);
		txtFindWhat.setBounds(150,10,200,30);
		add(txtFindWhat);
		txtReplaceWith=new JTextField(20);
		txtReplaceWith.setBounds(150,70,200,30);
		add(txtReplaceWith);
		chkMatchCase=new JCheckBox("Match Case");
		chkMatchCase.setBounds(5,120,100,50);
		add(chkMatchCase);
		rdoUp=new JRadioButton("Up");
		rdoDown=new JRadioButton("Down",true);
		bg=new ButtonGroup();
		bg.add(rdoUp);
		bg.add(rdoDown);
		panRadio=new JPanel();
		panRadio.setBorder(BorderFactory.createTitledBorder("Direction"));
		panRadio.add(rdoUp);
		panRadio.add(rdoDown);	
		panRadio.setLayout(new FlowLayout(FlowLayout.LEFT));
		panRadio.setBounds(170, 120, 100, 80);
		add(panRadio);
		btnFindNext=new JButton("Find Next");
		btnFindNext.setBounds(370,10,100,40);
		btnFindNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String findWhat=txtFindWhat.getText();
				boolean mc=chkMatchCase.isSelected();
				String dir;
				if(rdoUp.isSelected())
					dir="up";
				else
					dir="down";
				frmNotepad.find(findWhat, mc, dir);
			}
		});
		add(btnFindNext);
		
		btnReplace=new JButton("Replace");
		btnReplace.setBounds(370,70,100,40);
		btnReplace.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				StringBuffer sb1=new StringBuffer(Notepad.jtaNotepad.getText());
				String s2=txtReplaceWith.getText();
				int start=Notepad.jtaNotepad.getSelectionStart();
				int end=Notepad.jtaNotepad.getSelectionEnd();
				sb1.delete(start, end);
				sb1.insert(start, s2);
				Notepad.jtaNotepad.setText(sb1.toString());
				Notepad.jtaNotepad.setCaretPosition(start+s2.length());
				String findWhat=txtFindWhat.getText();
				boolean mc=chkMatchCase.isSelected();
				String dir;
				if(rdoUp.isSelected())
					dir="up";
				else
					dir="down";
				frmNotepad.find(findWhat, mc, dir);

			}
		});
		add(btnReplace);
		
		btnReplaceAll=new JButton("ReplaceAll");
		btnReplaceAll.setBounds(370,120,100,40);
		btnReplaceAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String s1=Notepad.jtaNotepad.getText();
				String s2=txtFindWhat.getText();
				String s3=txtReplaceWith.getText();
				s1=s1.replaceAll(s2, s3);
				Notepad.jtaNotepad.setText(s1);
			}
		});
		add(btnReplaceAll);
		btnCancel=new JButton("Cancel");
		btnCancel.setBounds(370,170,100,40);
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
			}
		});
		add(btnCancel);
		setSize(500,260);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}