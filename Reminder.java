import java.awt.*;
import javax.swing.*;
import java.awt.Toolkit.*;
import java.awt.event.*;

class Reminder extends JPanel
{
	//static int width=400, height=300;
	static String imgDir="";
	static Image img=null;
	static Reminder remind=null;
	static int imgWidth=0, imgHeight=0;

	public static void main(String[]args)
	{
		Frame frame = new Frame("Happy Reminder");
		frame.addWindowListener(new Terminate());

		if(args!=null && args.length>0 && args[0]!=null)
		{
			imgDir=args[0];
			Reminder reminder = new Reminder();
			waitToLoadImage();

			frame.setSize(imgWidth>>1,(imgHeight>>1)+23);
			frame.add(reminder);
			frame.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()-(imgWidth>>1),-5);
			frame.setVisible(true);

			//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		}
		else
			System.out.println("Please dont forget the image");
	}

	static void waitToLoadImage()
	{
		long startTime=System.currentTimeMillis();
		do
		{
			try{Thread.sleep(1);}catch(Exception e){}

		}while(img.getWidth(remind)<0);
		imgWidth=img.getWidth(remind);
		imgHeight=img.getHeight(remind);

		System.out.println(System.currentTimeMillis()-startTime+"millisec. to load Image");
	}
	Reminder ()
	{
		remind=this;
		this.img=Toolkit.getDefaultToolkit().createImage(imgDir);
		try{Thread.sleep(1000);Thread.yield();}catch(Exception e){}
	}

	public void paint(Graphics g)
	{
		if(img!=null)
			g.drawImage(img,0,0,imgWidth>>1,imgHeight>>1,remind);
	}
}
class Terminate extends WindowAdapter{
  public void windowClosing(WindowEvent e){
    System.exit(0);
    }
}