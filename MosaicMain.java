import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.Timer;



@SuppressWarnings("serial")
public class MosaicMain{
	JFrame frm = new JFrame(); JPanel picPnl = new Panel(); //Panel
	JPanel pnl = new JPanel(); JPanel compPnl = new JPanel(new GridBagLayout());
	JButton btnGo = new JButton("Go!");
	JComboBox<Integer> sizeBox = new JComboBox<>();
	JSlider colorSlider;
	JCheckBox colorLock;
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	int W = dim.width; int H = dim.height;
	Graphics g;
	int rectSizeMin, rectSizeMax, nofColorsMin, nofColorsMax;
	boolean lockColors;
	public static int count;
	Color color1 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color2 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color3 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color4 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color5 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color6 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color7 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	Color color8 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
	
	public static void main(String[] args) {
		new MosaicMain();
	}
	
	public MosaicMain(){
		int size[] = {10,20,40,50,80,100};
		rectSizeMin = 10; rectSizeMax = 80;
		nofColorsMin = 2; nofColorsMax = 8;
		colorSlider = new JSlider(JSlider.VERTICAL, nofColorsMin, nofColorsMax, 6);
		colorSlider.setMajorTickSpacing(1);
		colorSlider.setPaintTicks(true);
		colorSlider.setPaintLabels(true);
		for(int i=0;i<size.length; i++){
			sizeBox.addItem(size[i]);
		}
		sizeBox.setSelectedItem(size[1]);
		colorLock = new JCheckBox("Lock Colors");
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		@SuppressWarnings("static-access")
		InputMap im = pnl.getInputMap(pnl.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Enter");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), "SpaceBar");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_L, 0), "L");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_2, 0), "2");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_3, 0), "3");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_4, 0), "4");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_5, 0), "5");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_6, 0), "6");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_7, 0), "7");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_8, 0), "8");
		ActionMap am = pnl.getActionMap();
	    am.put("Enter", new KeyPressedAction("Enter"));
	    am.put("SpaceBar", new KeyPressedAction("SpaceBar"));
	    am.put("L", new KeyPressedAction("L"));
	    am.put("2", new KeyPressedAction("2"));
	    am.put("3", new KeyPressedAction("3"));
	    am.put("4", new KeyPressedAction("4"));
	    am.put("5", new KeyPressedAction("5"));
	    am.put("6", new KeyPressedAction("6"));
	    am.put("7", new KeyPressedAction("7"));
	    am.put("8", new KeyPressedAction("8"));
	    
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setLocation(W/8, H/8);
		gbc.gridx=0; gbc.gridy=0; compPnl.add(sizeBox, gbc);
		gbc.gridx=1; gbc.gridy=0; compPnl.add(colorSlider, gbc);
		gbc.gridx=0; gbc.gridy=1; compPnl.add(colorLock, gbc); 
		gbc.gridx=1; gbc.gridy=1; compPnl.add(btnGo, gbc);
		pnl.add(compPnl); pnl.add(picPnl);
		frm.add(pnl);
		frm.setResizable(false); frm.setFocusable(true); frm.requestFocus();
		frm.pack();
		frm.setVisible(true);
		
		colorLock.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent ie){
				if(colorLock.isSelected()){ lockColors = true; }
				else { lockColors = false; }
			}
		});
		btnGo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(lockColors){ picPnl.repaint(); } else {
					color1 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color2 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color3 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color4 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color5 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color6 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color7 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					color8 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
					picPnl.repaint();}
			}
		});
	}
	public class Panel extends JPanel{
		public int rectSize(){
			return (int)sizeBox.getSelectedItem();
		}
		public int nofColors(){
			return colorSlider.getValue();
		}
		
		public Dimension getPreferredSize() {
	        return new Dimension(400,400);
	    }
		public Color randomColor(){
			int rn = (int)(Math.random()*nofColors())+1;
			switch(rn){
				case 1: return color1; 
				case 2: return color2; 
				case 3: return color3; 
				case 4: return color4; 
				case 5: return color5; 
				case 6: return color6; 
				case 7: return color7; 
				case 8: return color8;
				default: return color1;
			}
		}
		public void paint(Graphics g){
			super.paintComponent(g);
			for(int i=0; i<400; i+=2*rectSize()){
				for(int j=0; j<400; j+=2*rectSize()){
					g.setColor(randomColor());
					g.fillRect(i,j,rectSize(),rectSize());
					g.setColor(randomColor());
					g.fillRect(i+rectSize(),j,rectSize(),rectSize());
					g.setColor(randomColor());
					g.fillRect(i,j+rectSize(),rectSize(),rectSize());
					g.setColor(randomColor());
					g.fillRect(i+rectSize(),j+rectSize(),rectSize(),rectSize());
				}
			}
		}
	}
	public class KeyPressedAction extends AbstractAction{
		private String cmd;
		public KeyPressedAction(String cmd){ this.cmd = cmd; }		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(cmd.equals("Enter")){
				Timer timer = new Timer(10, new ActionListener(){
					public void actionPerformed(ActionEvent e){
						count++; 
						if(Math.floorMod(count, 200)==0){ 
							if(lockColors){ picPnl.repaint(); } else {
							color1 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color2 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color3 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color4 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color5 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color6 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color7 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							color8 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
							picPnl.repaint(); }
						}
					}
				}); timer.start();
			} 
			if(cmd.equals("SpaceBar")){
				if(lockColors){ picPnl.repaint(); } else {
				color1 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color2 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color3 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color4 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color5 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color6 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color7 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				color8 = new Color((int)(Math.random()*255)+1,(int)(Math.random()*255)+1,(int)(Math.random()*255)+1);
				picPnl.repaint();}
			}
			if(cmd.equals("L")){
				if(!colorLock.isSelected()){ colorLock.setSelected(true); }
				else if(colorLock.isSelected()){ colorLock.setSelected(false); }
			}
			if(cmd.equals("2")){ colorSlider.setValue(2); }
			if(cmd.equals("3")){ colorSlider.setValue(3); }
			if(cmd.equals("4")){ colorSlider.setValue(4); }
			if(cmd.equals("5")){ colorSlider.setValue(5); }
			if(cmd.equals("6")){ colorSlider.setValue(6); }
			if(cmd.equals("7")){ colorSlider.setValue(7); }
			if(cmd.equals("8")){ colorSlider.setValue(8); }
		}
	}
}
