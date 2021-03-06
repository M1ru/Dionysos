package RoomScreen.Layout;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.text.Style;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StyledDocument;

import RoomScreen.Connection.Client;
import RoomScreen.Connection.Server;
import WAVMaker.WAVMaker;

import org.bitlet.weupnp.GatewayDevice;

import Recorder.Recorder;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author DevSim
 */
public class RoomPanel extends JPanel {

	//private javax.swing.JTextArea msgArea;
	private JTextPane msgArea;

	public CardLayout instHolder;
	private Server server;
	private Client client;
	private JPanel focusDest;
	private StyledDocument document;

	public boolean isRecording;
	public Recorder recorder;
	private JFileChooser fileChooser;

	public static RoomPanel instance;
	/*
	 * Creates new form Main2
	 */
	public RoomPanel() {
		instance = this;
		
		msgArea = new JTextPane();
		document = (StyledDocument) msgArea.getDocument();

		Style init = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		Style aStyle = document.addStyle("BLUE", init);
		StyleConstants.setForeground(aStyle, Color.blue);

		look_feel();
		initComponents();
		addPanel();

		isRecording = false;
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("wav", "wav"));
		fileChooser.setMultiSelectionEnabled(false);

		jpInstru.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (focusDest != null) focusDest.requestFocus();
			}
		});
	}
	public void setFocusDest(JPanel p) {
		focusDest = p;
	}

	/*
	 * While removing and creating new instrument panel, the chat text append to
	 * bottom. To prevent this, rather than scroll chat JTextPane, keep only 13
	 * rows in JTextPane. Maybe better implementation about chat area or change
	 * instrument panels to CardLayout?
	 */
	private int rows = 0;
	public void appendStr(String str, String type) throws BadLocationException {
		if (rows > 13) {
			document.remove(0, document.getText(0, document.getLength()).indexOf("\n") + 1);
		}
		else {
			rows++;
		}
		document = (StyledDocument) msgArea.getDocument();
		if (type != null) document.insertString(document.getLength(), str, document.getStyle("BLUE"));
		else document.insertString(document.getLength(), str, null);
	}

	/*
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {
		jpLogo = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		recordBtn = new javax.swing.JButton();
		location = new javax.swing.JLabel();
		historyBtn = new javax.swing.JButton();
		replayBtn = new javax.swing.JButton();
		time = new javax.swing.JLabel();
		exitBtn = new javax.swing.JButton();
		jpInstru = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtField = new javax.swing.JTextField();
		sendBtn = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		userList = new javax.swing.JList<>();
		jpChoice = new javax.swing.JPanel();

		setSize(new Dimension(920, 770));

		jpLogo.setBackground(new java.awt.Color(255, 255, 204));

		javax.swing.GroupLayout jpLogoLayout = new javax.swing.GroupLayout(jpLogo);
		jpLogo.setLayout(jpLogoLayout);
		jpLogoLayout.setHorizontalGroup(jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 214, Short.MAX_VALUE));
		jpLogoLayout.setVerticalGroup(jpLogoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 190, Short.MAX_VALUE));

		jPanel2.setBackground(new java.awt.Color(204, 255, 204));
		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Function"));
		jPanel2.setName(""); // NOI18N

		recordBtn.setText("Record");
		recordBtn.setName(""); // NOI18N

		recordBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				recordBtnActionPerformed(evt);
			}
		});

		location.setText("Location : C:\\Dionysos\\record\\");

		historyBtn.setText("History");

		replayBtn.setText("To Wav");
		replayBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				wavBtnActionPerformed(evt);
			}
		});

		time.setText("Time : 00 : 05");

		exitBtn.setText("Exit");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(recordBtn)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(replayBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(historyBtn)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(location).addComponent(time))
						.addContainerGap(71, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE).addGroup(jPanel2Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(recordBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
										.addComponent(replayBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(historyBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(location).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(time).addContainerGap()));

		jpInstru.setBackground(new java.awt.Color(255, 204, 255));

		javax.swing.GroupLayout jpInstruLayout = new javax.swing.GroupLayout(jpInstru);
		jpInstru.setLayout(jpInstruLayout);

		jpInstruLayout.setHorizontalGroup(jpInstruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
		jpInstruLayout.setVerticalGroup(jpInstruLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));

		jPanel4.setBackground(new java.awt.Color(204, 204, 255));
		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chatting"));

		msgArea.setEditable(false);

		DefaultCaret caret = (DefaultCaret) msgArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		jScrollPane2.setViewportView(msgArea);

		sendBtn.setText("Send");
		jScrollPane1.setViewportView(userList);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane2)
								.addGroup(jPanel4Layout.createSequentialGroup()
										.addComponent(txtField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE))
								.addComponent(jScrollPane1))
						.addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane2)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(txtField,
								javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(sendBtn))
						.addContainerGap()));

		jpChoice.setBackground(new java.awt.Color(255, 153, 102));
		jpChoice.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Instrument"));

		javax.swing.GroupLayout jpChoiceLayout = new javax.swing.GroupLayout(jpChoice);
		jpChoice.setLayout(jpChoiceLayout);
		jpChoiceLayout.setHorizontalGroup(jpChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
		jpChoiceLayout.setVerticalGroup(jpChoiceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 130, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
										.addComponent(jpLogo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(jpChoice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel4,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(jpInstru, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout
								.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jpLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jpChoice,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jpInstru, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
	}

	private void addPanel() {
		//Logo
		Logo logoPanel = new Logo(jpLogo);
		jpLogo.setLayout(new BorderLayout());
		jpLogo.add(logoPanel);

		//Chocie
		Choice choicePanel = new Choice(jpChoice);
		jpChoice.setLayout(new BorderLayout());
		jpChoice.add(choicePanel);
	}

	private void recordBtnActionPerformed(java.awt.event.ActionEvent evt) {
		if (isRecording) {
			// Finish recording
			isRecording = false;
			recordBtn.setText("Record");
			recorder.stop();

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss", Locale.KOREA);
			Date date = new Date();
			recorder.saveAs("History/" + formatter.format(date) + ".txt");

			try {
				WAVMaker maker = new WAVMaker(new FileInputStream("History/" + formatter.format(date) + ".txt"));
				maker.createWAV();
				if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
					maker.save(fileChooser.getSelectedFile().toString() + ".wav");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			recorder = null;
		}
		else {
			// Start recording
			isRecording = true;
			recordBtn.setText("[STOP]");

			recorder = new Recorder();
		}
	}

	private void wavBtnActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private void look_feel() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(RoomPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(RoomPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(RoomPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(RoomPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
	}

	public JTextPane getMsgArea() {
		return msgArea;
	}
	public JTextField gettxtField() {
		return txtField;
	}
	public JList<String> getUserList() {
		return userList;
	}
	public JPanel getJpChoice() {
		return jpChoice;
	}
	public JPanel getJpInstru() {
		return jpInstru;
	}
	public Client getClient() {
		return client;
	}
	public void changeInst(String inst) {
		instHolder.show(jpInstru, inst);
	}

	// Variables declaration - do not modify                     
	private javax.swing.JButton exitBtn;
	private javax.swing.JButton historyBtn;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;

	private javax.swing.JPanel jpChoice;
	private javax.swing.JPanel jpInstru;
	private javax.swing.JPanel jpLogo;
	private javax.swing.JLabel location;

	private javax.swing.JButton recordBtn;
	private javax.swing.JButton replayBtn;
	private javax.swing.JButton sendBtn;
	private javax.swing.JLabel time;
	private javax.swing.JTextField txtField;
	private javax.swing.JList<String> userList;
}
