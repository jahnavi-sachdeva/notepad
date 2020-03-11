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

class DlgFind extends JDialog{
	JLabel lblFindWhat;
	JTextField txtFindWhat;
	JCheckBox chkMatchCase;
	JRadioButton rdoUp,rdoDown;
	ButtonGroup bg;
	JPanel panRadio;
	JButton btnFindNext,btnCancel;
	boolean first=false;
	DlgFind(Notepad frmNotepad){
		super(frmNotepad,"Find",false);//true for Modal false for Modeless
		setLayout(null);
		lblFindWhat=new JLabel("Find What");
		lblFindWhat.setBounds(5,10,100,50);
		add(lblFindWhat);
		txtFindWhat=new JTextField(20);
		txtFindWhat.setBounds(150,10,200,30);
		add(txtFindWhat);
		chkMatchCase=new JCheckBox("Match Case");
		chkMatchCase.setBounds(5,70,100,50);
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
		panRadio.setBounds(170, 70, 100, 80);
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
		
		btnCancel=new JButton("Cancel");
		btnCancel.setBounds(370,70,100,40);
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