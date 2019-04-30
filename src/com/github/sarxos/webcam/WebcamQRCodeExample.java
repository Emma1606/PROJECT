package com.github.sarxos.webcam;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import java.awt.FlowLayout; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.SwingConstants;
import java.awt.Font;

public class WebcamQRCodeExample extends JFrame implements Runnable, ThreadFactory {

	private static final long serialVersionUID = 6441489157408381878L;

	private Executor executor = Executors.newSingleThreadExecutor(this);

	private Webcam webcam = null;
	private WebcamPanel panel = null;
	private JTextArea textarea = null;

	public WebcamQRCodeExample() {
		super();

		setLayout(new FlowLayout());
		setTitle("Read QR / Bar Code With Webcam");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension size = WebcamResolution.QVGA.getSize();

		webcam = Webcam.getWebcams().get(0);
		webcam.setViewSize(size);		
		

		panel = new WebcamPanel(webcam);
		//panel.setPreferredSize();
		this.setPreferredSize(new Dimension(1400,600));

		textarea = new JTextArea();
		textarea.setEditable(false);
		textarea.setPreferredSize(size);		
		textarea.setPreferredSize(new Dimension(1000,500));
		
		class LabelFrame extends JFrame
		  {
		  private JLabel textLabel;      // JLabel de texte simplement
		  //private JLabel textImageLabel; // JLabel de texte et image
		 // private JLabel textIconLabel; // JLabel  de texte et ic�ne
		 
		  public LabelFrame()
		  {
		     setLayout( new FlowLayout() ); 
		     textLabel = new JLabel( "JLabel avec texte" );
		     textarea.setToolTipText( "JLabel avec texte" );
		     // Modifier la police, le style et la taille de police d'un texte
		     textarea.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,25));
		     
		 
		  } 
		 
		  }
		
		LabelFrame labelFrame = new LabelFrame();
		labelFrame.setSize(122,122);
		
		

		add(panel);
		add(textarea);

		pack();
		setVisible(true);

		executor.execute(this);
	}

	@Override
	public void run() {

		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) {

				if ((image = webcam.getImage()) == null) {
					continue;
				}

				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}

			if (result != null) {
				textarea.setText(result.getText());
			}

		} while (true);
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}

	public static void main(String[] args) {
		new WebcamQRCodeExample();
	}
}
