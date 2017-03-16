
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.WindowEvent;
import java.awt.event.*;


public class game extends JFrame {
	
	JMenuBar menubar;
	JMenu file,help;
	JMenuItem newgame,exit,howtoplay,about;
	public JLabel label,scrlabel,timelabel;
	public JButton right,wrong,startnewgame;
	public static int num1,num2,resultnum;
	public Timer timer;
	int k;
	
	
	public game(){
		setLayout(new FlowLayout());
		bgcolor();
		filemenu();
		label= new JLabel();
		
		gamepanel();
		
	}
	
	
	public void gamepanel(){
		
		numpanel();
		label.setVisible(true);
		label.setForeground(Color.black);
		label.setBackground(Color.white);
		label.setOpaque(true);
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		Border border = BorderFactory.createLineBorder(Color.black, 5);
		label.setBorder(BorderFactory.createCompoundBorder(border,paddingBorder));
		
		Font myFont = new Font("Serif",Font.BOLD,50);
        label.setFont(myFont);
		add(label);
		
		
		right= new JButton("right");
		right.setVisible(true);
		right.setPreferredSize(new Dimension(80,70));
		right.setHorizontalAlignment(SwingConstants.CENTER);
		wrong= new JButton("wrong");
		wrong.setVisible(true);
		wrong.setPreferredSize(new Dimension(80,70));
		wrong.setHorizontalAlignment(SwingConstants.CENTER);
		
		Image img=new ImageIcon(this.getClass().getResource("/check.png")).getImage();
		right.setIcon(new ImageIcon(img));
		Image imag=new ImageIcon(this.getClass().getResource("/cross.png")).getImage();
		wrong.setIcon(new ImageIcon(imag));
		  
		add(right);
		add(wrong);
		
		int i=0;
		scrlabel = new JLabel("Your Score :   "+i+" ");
		scrlabel.setVisible(true);
		scrlabel.setForeground(Color.black);
		scrlabel.setBackground(Color.white);
		scrlabel.setOpaque(true);
		Font font = new Font("Serif",Font.BOLD,22);
        scrlabel.setFont(font);
		add(scrlabel);
		
		
		
		k=5;
		timelabel=new JLabel("Time Left :  00:05 ");
		timelabel.setVisible(true);
		timelabel.setForeground(Color.black);
		timelabel.setBackground(Color.white);
		timelabel.setOpaque(true);
		Font font2 = new Font("Serif",Font.BOLD,22);
		timelabel.setFont(font2);
		add(timelabel);
		
		
		
		timer= new Timer(500,new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				
				k--;
				if(k!=0){
					timelabel.setText("Time Left :  00:0"+k+" ");
					
					
				}else{
					timer.stop();
					out();
				}
			}
		});
		timer.start();
		
		event1 e1 = new event1();
		right.addActionListener(e1);
		
		event2 e2 = new event2();
		wrong.addActionListener(e2);
		
		
	}
	
	
	
	static int count=0;
	
	public class event1 implements ActionListener{
		public void actionPerformed(ActionEvent e1){
			
			
			int sum=num1+num2;
			if(sum==resultnum){
				
				++count;
				scrlabel.setText("Your Score :   "+count+" ");
				timer.stop();
				k=6;
				numpanel();
				timer.start();
				
			}else{
				out();
				
			}
				
		}
	}
	
	
	public class event2 implements ActionListener{
		public void actionPerformed(ActionEvent e2){
			
			
			int sum=num1+num2;
			if(sum!=resultnum){
				
				++count;
				scrlabel.setText("Your Score :   "+count+" ");
				timer.stop();
				k=6;
				numpanel();
				timer.start();
				
			}else{
				
				out();
				
			}
			
			
		}
	}
	
	
	public void out(){
		
		timer.stop();
		right.setVisible(false);
		wrong.setVisible(false);
		timelabel.setVisible(false);
		startnewgame= new JButton("Start New Game");
		startnewgame.setVisible(true);
		startnewgame.setPreferredSize(new Dimension(200,50));
		Font fnt = new Font("Serif",Font.BOLD,22);
        startnewgame.setFont(fnt);
		add(startnewgame);
		
		event3 e3 = new event3();
		startnewgame.addActionListener(e3);
	}
	
	
	public class event3 implements ActionListener{
		public void actionPerformed(ActionEvent e3){
			reload();
			
		}
	}
	
	
	public void reload(){
		
		
		startnewgame.setVisible(false);
		scrlabel.setVisible(false);
		label.setVisible(false);
		right.setVisible(false);
		wrong.setVisible(false);
		timelabel.setVisible(false);
		count=0;
		
		gamepanel();
	}
	
	

	public void numpanel(){
		
		int rnum1=(int)(Math.random()*10+1);
		num1=rnum1;
		int rnum2=(int)(Math.random()*10+1);
		num2=rnum2;
		
		int lnum=(int)(Math.random()*10);
		
		if(lnum==2||lnum==5||lnum==9||lnum==0){
			
			int resnum= rnum1+rnum2;
			resultnum=resnum;
			label.setText(rnum1+" + "+rnum2+" = "+resnum);
			
		}else{
			
			int small= (int)(Math.min(rnum1, rnum2));
			int large= (int)(Math.max(rnum1, rnum2));
			int resnum= (large+1)+(int)(Math.random()*(small+2));
			resultnum=resnum;
			label.setText(rnum1+" + "+rnum2+" = "+resnum);
			
		}
		
	}
	
	
	
	public void filemenu(){
		
		menubar = new JMenuBar();
		
		file = new JMenu("File");

		newgame = new JMenuItem("New Game");
		exit = new JMenuItem("Exit");
		
		help = new JMenu("Help");

		howtoplay = new JMenuItem("How to play");
		about = new JMenuItem("About");
		
		
		
		setJMenuBar(menubar);
		menubar.add(file);
		file.add(newgame);
		file.add(exit);
		menubar.add(help);
		help.add(howtoplay);
		help.add(about);
		
	
	}

	
	
	public void bgcolor(){
		int dbcr,dbcb,dbcg;
		
		dbcr = (int)(Math.random()*255);
		dbcg = (int)(Math.random()*255);
		dbcb = (int)(Math.random()*255);
		
		Container c = this.getContentPane();
		c.setBackground(new Color(dbcr,dbcg,dbcb)); 
	}

	
	
	
	
	public static void main(String args[]){
		game obj= new game();
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setSize(300,360);
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setTitle("Math Game");
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = obj.getSize().width;
        int h = obj.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        obj.setLocation(x, y);
		
	}

}
