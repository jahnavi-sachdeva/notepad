import java.awt.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

class Notepad extends JFrame implements ActionListener{
	static JTextArea jtaNotepad;
	JMenuBar mbar;
	JMenu mnuFile,mnuEdit,mnuFormat,mnuHelp;
	JScrollPane jsp;
	JMenuItem mitNew,mitOpen,mitSave,mitSaveAs,mitExit;
	JMenuItem mitCut,mitCopy,mitPaste,mitUndo,mitFind,mitDelete,mitFindNext,mitReplace,mitSelectAll,mitTimeDate;
	JCheckBoxMenuItem mitWordWrap;
	JMenuItem mitFont;
	JMenuItem mitViewHelp,mitAboutNotepad;
	
	Font defaultFnt;
	File currentFile;
	boolean saveFlag=true,mc;
	String findWhat,dir;
	Notepad(){
		mbar=new JMenuBar();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
        setFocusable(false);	
		
        try {
        	FileInputStream fis=new FileInputStream("documents/notepad.cfg");
        	Properties p1=new Properties();
        	p1.load(fis);
        	defaultFnt=new Font(p1.getProperty("fntname"),Integer.parseInt(p1.getProperty("fntstyle")),Integer.parseInt(p1.getProperty("fntsize")));
        	fis.close();
        }
        catch(FileNotFoundException e) {
        	defaultFnt=new Font(Font.SERIF,Font.PLAIN,30);
        }
        catch(IOException e) {
        	defaultFnt=new Font(Font.SERIF,Font.PLAIN,30);
        }
        
		jtaNotepad=new JTextArea();
		jtaNotepad.setLineWrap(true);
		jtaNotepad.setFont(defaultFnt);
		jtaNotepad.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				saveFlag=false;
			}
			public void removeUpdate(DocumentEvent e) {
				saveFlag=false;
			}
			public void changedUpdate(DocumentEvent e) {
				saveFlag=false;
			}
		});
		jsp=new JScrollPane(jtaNotepad);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				if(saveFlag==false) {
					int ans=JOptionPane.showOptionDialog(Notepad.this,"Do you want to save changes","Notepad",0,0,null,new String[] {"Save","Don't Save","Cancel"},"Save");
					if(ans==0) 
						save();
					else if(ans==1)
						dispose();
				}
				else
					dispose();
			}
		});
		
		mnuFile=new JMenu("   File");
		mnuEdit=new JMenu("   Edit");
		mnuHelp=new JMenu("   Help");
		mnuFormat=new JMenu("   Format");
		
		mitNew=new  JMenuItem("   New  ");
	   	mitOpen=new JMenuItem("   Open  ");
		mitSave=new JMenuItem("   Save  ");
		mitSaveAs=new JMenuItem("   Save As  ");
		mitExit=new JMenuItem("   Exit  ");
		mitCut=new JMenuItem("    Cut  ");
		mitCopy=new JMenuItem("    Copy  "); 
		mitPaste=new JMenuItem("    Paste  ");
		mitUndo=new JMenuItem("    Undo  ");
		mitFind=new JMenuItem("    Find  ");
	    mitDelete=new JMenuItem("    Delete  ");
		mitFindNext=new JMenuItem("    Find Next  ");
		mitReplace=new JMenuItem("    Replace ");
		mitSelectAll=new JMenuItem("   Select All  ");
		mitTimeDate=new JMenuItem("   Time/date  ");
		mitWordWrap=new JCheckBoxMenuItem("   WordWrap  ",true);
		mitFont=new JMenuItem("   Font  ");
		mitViewHelp=new JMenuItem("   View Help  ");
		mitAboutNotepad=new JMenuItem("   About Notepad  ");
		
		mitNew.addActionListener(this);
	   	mitOpen.addActionListener(this);
		mitSave.addActionListener(this);
		mitSaveAs.addActionListener(this);
		mitExit.addActionListener(this);
		mitCut.addActionListener(this);
		mitCopy.addActionListener(this); 
		mitPaste.addActionListener(this);
		mitUndo.addActionListener(this);
		mitFind.addActionListener(this);
	    mitDelete.addActionListener(this);
		mitFindNext.addActionListener(this);
		mitReplace.addActionListener(this);
		mitSelectAll.addActionListener(this);
		mitTimeDate.addActionListener(this);
		mitWordWrap.addActionListener(this);
		mitFont.addActionListener(this);
		mitViewHelp.addActionListener(this);
		mitAboutNotepad.addActionListener(this);
		
	    mitNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
	    mitOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
 		mitSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
   		mitCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
		mitCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		mitPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
		mitUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
		mitFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,ActionEvent.CTRL_MASK));
		mitReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mitSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
	    mitDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,0)); 
	    mitTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5,0));
	    mitFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
	   
	    mnuFile.setMnemonic(KeyEvent.VK_F);
	    mnuEdit.setMnemonic(KeyEvent.VK_E);	
		mnuFormat.setMnemonic(KeyEvent.VK_F);
		mnuHelp.setMnemonic(KeyEvent.VK_H);	
		
		ImageIcon ii=new ImageIcon("image1.jpg"); 
	    setIconImage(ii.getImage());
		
		mnuFile.add(mitNew);
	    mnuFile.add(mitOpen);
        mnuFile.add(mitSave);
        mnuFile.add(mitSaveAs);
	    mnuFile.addSeparator();
	    mnuFile.add(mitExit);
	      
	    mnuEdit.add(mitUndo);
	    mnuEdit.addSeparator();
	    mnuEdit.add(mitCut);
	    mnuEdit.add(mitCopy);
	    mnuEdit.add(mitPaste);
	    mnuEdit.add(mitDelete);
	    mnuEdit.addSeparator();
	    mnuEdit.add(mitFind);
	    mnuEdit.add(mitFindNext); 
	    mnuEdit.add(mitReplace);
	    mnuEdit.addSeparator();
	    mnuEdit.add(mitSelectAll);
	    mnuEdit.add(mitTimeDate);
	    
	    mnuFormat.add(mitWordWrap);
	    mnuFormat.add(mitFont);
	     
	    mnuHelp.add(mitViewHelp);
	    mnuHelp.addSeparator();
	    mnuHelp.add(mitAboutNotepad); 
	         
	    mbar.add(mnuFile);
	    mbar.add(mnuEdit);
	    mbar.add(mnuFormat);
	    mbar.add(mnuHelp);
	      
	    add(mbar,BorderLayout.NORTH);
	    add(jsp);
	  
	    setTitle(" Notepad");
	    setVisible(true); 
	}
	public static void main(String args[]){
		new Notepad();  	
	}
	public void actionPerformed(ActionEvent ae) {
		String s1=ae.getActionCommand();
		if(s1.trim().equalsIgnoreCase("new")) {
			if(saveFlag==true) {
				jtaNotepad.setText("");
				setTitle("Untitled - Notepad");
				saveFlag=true;
			}
			else {
				int ans=JOptionPane.showOptionDialog(Notepad.this,"Do you want to save changes","Notepad",0,0,null,new String[] {"Save","Don't Save","Cancel"},"Save");
				if(ans==0) { 
					save();
					jtaNotepad.setText("");
					setTitle("Untitled - Notepad");
					saveFlag=true;
				}
				else if(ans==1) {
					jtaNotepad.setText("");
					setTitle("Untitled - Notepad");
					saveFlag=true;
				}
			}				
		}
		else if(s1.trim().equalsIgnoreCase("open")) {
			open();
		}
		else if(s1.trim().equalsIgnoreCase("save")) {
			save();
		}
		else if(s1.trim().equalsIgnoreCase("save as")) {
			saveAs();
		}
		else if(s1.trim().equalsIgnoreCase("exit")) {
			dispose();
		}
		else if(s1.trim().equalsIgnoreCase("undo")) {
			
		}
		else if(s1.trim().equalsIgnoreCase("cut")) {
			jtaNotepad.cut();
		}
		else if(s1.trim().equalsIgnoreCase("copy")) {
			jtaNotepad.copy();
		}
		else if(s1.trim().equalsIgnoreCase("paste")) {
			jtaNotepad.paste();
		}
		else if(s1.trim().equalsIgnoreCase("delete")) {
			StringBuffer sb1=new StringBuffer(jtaNotepad.getText());
			int start=jtaNotepad.getSelectionStart();
			int end=jtaNotepad.getSelectionEnd();
			sb1.delete(start, end);
			jtaNotepad.setText(sb1.toString());
			jtaNotepad.setCaretPosition(start);
		}
		else if(s1.trim().equalsIgnoreCase("find")) {
			new DlgFind(this);
		}
		else if(s1.trim().equalsIgnoreCase("find next")) {
			find(findWhat, mc, dir);
		}
		else if(s1.trim().equalsIgnoreCase("replace")) {
			new DlgReplace(this);
		}
		else if(s1.trim().equalsIgnoreCase("select all")) {
			jtaNotepad.setSelectionStart(0);
			jtaNotepad.setSelectionEnd(jtaNotepad.getText().length());
		}
		else if(s1.trim().equalsIgnoreCase("time/date")) {
			Date d1=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("hh:mm dd:MM:yyyy");
			StringBuffer sb1=new StringBuffer(jtaNotepad.getText());
			int start=jtaNotepad.getCaretPosition();
			sb1.insert(start, sdf.format(d1));
			jtaNotepad.setText(sb1.toString());
			jtaNotepad.setCaretPosition(start);
		}
		else if(s1.trim().equalsIgnoreCase("wordwrap")) {
			jtaNotepad.setLineWrap(mitWordWrap.isSelected());
		}
		else if(s1.trim().equalsIgnoreCase("font")) {
			new DlgFont(this);
		}
		else if(s1.trim().equalsIgnoreCase("view help")) {
			new DlgHelp(this);
		}
		else if(s1.trim().equalsIgnoreCase("about notepad")) {
			new DlgAbout(this);
		}
	}
	void open() {
		JFileChooser jfc=new JFileChooser("c:/javaprog");
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text Documents","txt");
		jfc.addChoosableFileFilter(filter1);
		jfc.setFileFilter(filter1);
		int ans=jfc.showOpenDialog(this);
		if(ans==JFileChooser.APPROVE_OPTION) {
			currentFile=jfc.getSelectedFile();
			try {
				FileInputStream fis=new FileInputStream(currentFile);
				byte b[]=new byte[(int)currentFile.length()];
				fis.read(b);
				jtaNotepad.setText(new String(b));
				fis.close();
				setTitle(currentFile.getName() + " Notepad");
				saveFlag=true;
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else if(ans==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(this,"No File Selected");
		}
	}
	void save() {
		if(currentFile==null) {
			saveAs();
		}
		else {
			try {
				FileOutputStream fos=new FileOutputStream(currentFile);
				fos.write(jtaNotepad.getText().getBytes());
				fos.close();
				setTitle(currentFile.getName() + " Notepad");
				saveFlag=true;
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	void saveAs() {
		JFileChooser jfc=new JFileChooser("c:/javaprog");
		FileNameExtensionFilter filter1=new FileNameExtensionFilter("Text Documents","txt");
		jfc.addChoosableFileFilter(filter1);
		jfc.setFileFilter(filter1);
		int ans=jfc.showSaveDialog(this);
		if(ans==JFileChooser.APPROVE_OPTION) {
			currentFile=jfc.getSelectedFile();
			File f=new File(currentFile.getAbsolutePath());
			if(f.exists()) {
				int ans2=JOptionPane.showOptionDialog(Notepad.this,"File already exists \r\n Do you want to replace it","Notepad",0,0,null,new String[] {"Yes","No"},"No");
				if(ans2==0) {
					try {
						FileOutputStream fos=new FileOutputStream(currentFile);
						fos.write(jtaNotepad.getText().getBytes());
						fos.close();
						saveFlag=true;
						setTitle(currentFile.getName() + " Notepad");
					}
					catch(IOException e) {
						e.printStackTrace();
					}	
				}
			}
		}
		else if(ans==JFileChooser.CANCEL_OPTION) {
			JOptionPane.showMessageDialog(this,"No File Selected");
		}
	}
	void find(String findWhat,boolean mc,String dir) {
		this.findWhat=findWhat;
		this.mc=mc;
		this.dir=dir;
		String findIn=Notepad.jtaNotepad.getText();
		if(mc==false) {
			findIn=findIn.toUpperCase();
			findWhat=findWhat.toUpperCase();
		}
		int startPos=jtaNotepad.getCaretPosition();
		int pos;
		if(dir.equals("down")) {
			pos=findIn.indexOf(findWhat,startPos);
		}
		else {
			if(Notepad.jtaNotepad.getSelectedText()!=null) {
				startPos-=findWhat.length();
				startPos--;
			}
			pos=findIn.lastIndexOf(findWhat,startPos);
		}
		if(pos==-1) {
			JOptionPane.showMessageDialog(null, "Text Not Found");
		}
		else {
			Notepad.jtaNotepad.setSelectionStart(pos);
			Notepad.jtaNotepad.setSelectionEnd(pos+findWhat.length());
		}
	}
}