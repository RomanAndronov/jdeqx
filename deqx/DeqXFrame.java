package deqx;

/*
  By Roman Andronov
 */

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;

/*
   This will execute deqx as a stand-alone
   Java program.

   To execute it as a Java applet consult the
   DeqApplet class in this package
*/

public
class DeqXFrame
	extends JFrame
{
	public
	DeqXFrame()
	{
		super();
		setTitle( "Digi Equus X, by Sabrina" );
		setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
	}

	public static void
	main( String[] args )
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void
			run()
			{
				DeqXFrame		deqxfrm = new DeqXFrame();

				deqxfrm.deqxpnl = new DeqXPanel( deqxfrm );
				deqxfrm.pack();
				deqxfrm.setLocationRelativeTo( null );
				deqxfrm.setVisible( true );
			}
		});

	}

	private DeqXPanel		deqxpnl = null;
}
