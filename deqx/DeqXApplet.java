package deqx;

/*
  By Roman Andronov
*/

import javax.swing.JApplet;
import javax.swing.SwingUtilities;

public
class DeqXApplet
	extends JApplet
{
	public void
	init()
	{
		SwingUtilities.invokeLater( new Runnable()
		{
			public void
			run()
			{
				createAppletGui();
			}
		});
	}

	private void
	createAppletGui()
	{
		if ( deqxpnl == null )
		{
			deqxpnl = new DeqXPanel( this );
		}
	}

	private DeqXPanel		deqxpnl = null;
}
