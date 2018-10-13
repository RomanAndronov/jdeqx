package deqx;

/*
  By Roman Andronov
 */

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

class SquareViewMouseHandler
	extends MouseInputAdapter
{
	public void
	mousePressed( MouseEvent me )
	{
		Object		o = me.getComponent();

		if ( o instanceof SquareView )
		{
			SquareView	sv = ( SquareView )o;
			sv.press( me );
		}
	}

	public void
	mouseReleased( MouseEvent me )
	{
		Object		o = me.getComponent();

		if ( o instanceof SquareView )
		{
			SquareView	sv = ( SquareView )o;
			sv.release( me );
		}
	}
}
