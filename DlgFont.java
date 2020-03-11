import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
class DlgFont extends JDialog{
	JLabel lblFontName,lblFontStyle,lblFontSize;
	JTextField txtFontName,txtFontStyle,txtFontSize;
	JList lstFontName,lstFontStyle,lstFontSize;
	JScrollPane jspFontName,jspFontStyle,jspFontSize;
	DefaultListModel modelFontName,modelFontStyle,modelFontSize;
	GraphicsEnvironment g=GraphicsEnvironment.getLocalGraphicsEnvironment();
	String arrFontName[]=g.getAvailableFontFamilyNames();
	String arrFontStyle[]={"PLAIN","BOLD","ITALIC"};
	int arrFontSize[]={8,9,10,11,12,14,16,18,20,22,24,26,28,36,48,72};
	JButton btnOk,btnCancel;
	DlgFont(Notepad frmNotepad){
		super(frmNotepad,"Font",true);
		setLayout(null);
		lblFontName=new JLabel("Font Name");
		lblFontName.setBounds(10,10,150,50);
		add(lblFontName);
		lblFontStyle=new JLabel("Font Style");
		lblFontStyle.setBounds(170,10,150,50);
		add(lblFontStyle);
		lblFontSize=new JLabel("Font Size");
		lblFontSize.setBounds(340,10,150,50);
		add(lblFontSize);
		txtFontName=new JTextField(20);
		txtFontName.setBounds(10,60,150,50);
		add(txtFontName);
		txtFontName.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				searchFontName();
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				searchFontName();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				searchFontName();
			}
		});
		txtFontStyle=new JTextField(20);
		txtFontStyle.setBounds(170,60,150,50);
		add(txtFontStyle);
		txtFontSize=new JTextField(20);
		txtFontSize.setBounds(340,60,150,50);
		add(txtFontSize);
		lstFontName=new JList();
		lstFontName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstFontStyle=new JList();
		lstFontStyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstFontSize=new JList();
		lstFontSize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelFontName=new DefaultListModel();
		for(int i=0;i<arrFontName.length;i++){
			modelFontName.addElement(arrFontName[i]);
		}
		modelFontStyle=new DefaultListModel();
		for(int i=0;i<arrFontStyle.length;i++){
			modelFontStyle.addElement(arrFontStyle[i]);
		}
		modelFontSize=new DefaultListModel();
		for(int i=0;i<arrFontSize.length;i++){
			modelFontSize.addElement(arrFontSize[i]);
		}
		lstFontName.setModel(modelFontName);
		lstFontName.clearSelection();
	    for(int i = 0; i < modelFontName.getSize(); i++){
	    	if(frmNotepad.defaultFnt.getName().equals(modelFontName.getElementAt(i))){
	    		lstFontName.setSelectedIndex(i);
	            lstFontName.ensureIndexIsVisible(i);
	            break;
	        }
	    }
	    
		lstFontSize.setModel(modelFontSize);
	    for(int i = 0; i < modelFontSize.getSize(); i++){
	    	if(frmNotepad.defaultFnt.getSize()==(Integer)modelFontSize.getElementAt(i)) {
	    		lstFontSize.setSelectedIndex(i);
	            lstFontSize.ensureIndexIsVisible(i);
	            break;
	        }
	    }
		
	    lstFontStyle.setModel(modelFontStyle);
	    lstFontStyle.setSelectedIndex(frmNotepad.defaultFnt.getStyle());
		jspFontName=new JScrollPane(lstFontName);
		jspFontName.setBounds(10,110,150,250);
		add(jspFontName);
		jspFontStyle=new JScrollPane(lstFontStyle);
		jspFontStyle.setBounds(170,110,150,250);
		add(jspFontStyle);
		jspFontSize=new JScrollPane(lstFontSize);
		jspFontSize.setBounds(340,110,150,250);
		add(jspFontSize);
		
		btnOk=new JButton("Ok");
		btnOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String fntName=(String)lstFontName.getSelectedValue();
				int fntStyle=lstFontStyle.getSelectedIndex();
				int fntSize=(Integer)lstFontSize.getSelectedValue();
				frmNotepad.defaultFnt=new Font(fntName,fntStyle,fntSize);
				frmNotepad.jtaNotepad.setFont(frmNotepad.defaultFnt);
				try{
					Properties p1=new Properties();
					p1.setProperty("fntname",fntName);
					p1.setProperty("fntstyle",fntStyle+"");
					p1.setProperty("fntsize",fntSize+"");
					FileOutputStream fos=new FileOutputStream("documents/notepad.cfg");
					p1.store(fos,"");
					fos.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
				dispose();
			}
		});
		btnOk.setBounds(130,400,100,50);
		add(btnOk);
		btnCancel=new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				dispose();
			}
		});
		btnCancel.setBounds(270,400,100,50);
		add(btnCancel);	
		setSize(550,500);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	void searchFontName() {
		String s1=txtFontName.getText();
		int l=s1.length();
		lstFontName.clearSelection();
		Vector<Integer> v1=new Vector<Integer>();
	    for(int i = 0; i < modelFontName.getSize(); i++){
	    	if(s1.equalsIgnoreCase(((String)modelFontName.getElementAt(i)).substring(0, l))){
	    		v1.add(i);
	        }
	    }
	    Integer arr[]=(Integer[])v1.toArray();
	    int arr2[]=new int[arr.length];
	    for(int i=0;i<arr.length;i++) {
	    	arr2[i]=arr[i];
	    }
	    lstFontName.setSelectedIndices(arr2);

	}
}